package com.example.kriti;

public class Item {

        private String itemHeading;
    private String itemDescription;

    public Item(String heading, String description){
            itemHeading=heading;
            itemDescription=description;
        }
        public Item(Item temp){
            itemHeading=temp.itemHeading;
            itemDescription=temp.itemDescription;
        }
        public String getHeading(){
            return itemHeading;

        }
    public String getDescription(){
        return itemDescription;

    }



}
