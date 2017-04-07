package com.davydenko.core;

import static java.lang.System.out;

 class SportEquipment {

    private Category category;
    private String title;
    private String price;
    private int count;
    private int isRent;


     public SportEquipment() {}

     public SportEquipment(String _title, String _category, String _price, String _count) {
        this.title = _title;
        this.category = new Category(_category);
        this.price = _price;
        this.count = Integer.parseInt(_count);
    }


    public void showEquipment() {

        out.println("Name:" + this.title + ", Firm:" + this.category.getTitle() + ", Price:" + this.price + "$, Count:" + this.count);
    }


    public void showRentEquipment() {

        out.println("Name:" + title + ", Firm:" + category.getTitle() + ", Price:" + price + "$, Count:" + isRent);
    }


     public boolean findEquipment(String _item) {
         boolean check = false;
         if (_item.equals(title)) {
             check = true;
         }
         return check;
     }


     public String getCategory() {
         return category.getTitle();
     }

     public String getTitle() {
         return title;
     }

     public String getPrice() {
         return price;
     }

     public int getCount() {
         return count;
     }

     public void countMinus() {
         count--;
     }
 }
