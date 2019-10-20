package com.example.kriti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddReading extends AppCompatActivity  {

    DatabaseReference databaseReference;
    StorageReference storageReference;
    String dept;
    EditText NameF,descF;
    Button uploadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reading);
        NameF =findViewById(R.id.BookName);
        descF =findViewById(R.id.Desc);
        uploadBtn =findViewById(R.id.upButton);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        Spinner spinner = findViewById(R.id.DeptSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Departments,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dept = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent= new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select PDF File"),1);*/
                selectPDFFile();
            }
        });



    }

    private void selectPDFFile(){
        Intent intent= new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File"),1);
    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==1 && resultCode ==RESULT_OK&& data!=null && data.getData()!=null){
            uploadPDF(data.getData());
        }
    }

    private  void uploadPDF(Uri data){
        StorageReference reference = storageReference.child(NameF.getText().toString()+".pdf");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uri =taskSnapshot.getStorage().getDownloadUrl();
                while(!uri.isComplete());
                    Uri url = uri.getResult();
                    String name = NameF.getText().toString().trim();
                    Books book = new Books(descF.getText().toString(),url.toString());
                    databaseReference.child(dept).child("Reading").child(name).setValue(book);
                    progressDialog.dismiss();
                    Toast.makeText(AddReading.this,"Uploaded Successfully",Toast.LENGTH_LONG).show();
                    finish();
                    Intent i= new Intent(AddReading.this,FeedActivity.class);
                    startActivity(i);
            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddReading.this,FeedActivity.class));
    }
}
