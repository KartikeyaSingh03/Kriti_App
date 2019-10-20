package com.example.kriti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PDFViewer extends AppCompatActivity {
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        Bundle bundle =getIntent().getExtras();
        if(bundle!=null){
            url =bundle.getString("URL");
        }
        pdfView= (PDFView)findViewById(R.id.pdfView);
        new RetrievePDFStream().execute(url);
    }

    class RetrievePDFStream extends AsyncTask<String,Void, InputStream>
    {
        @Override
        protected InputStream doInBackground(String... strings){
            InputStream inputStream=null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).defaultPage(pageNumber)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableAnnotationRendering(true)
                    .load();
        }
    }

}
