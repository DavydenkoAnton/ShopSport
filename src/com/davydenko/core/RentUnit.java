package com.davydenko.core;

import java.io.FileWriter;
import java.io.IOException;

class RentUnit {

    private SportEquipment[] units;
    private int countOfItems;


    public RentUnit() {

        units = new SportEquipment[1000];
        countOfItems = 0;
    }



    public void setUnit(String _category, String _title, String _price,String _count) {

        units[countOfItems] = new SportEquipment(_category, _title, _price,_count);
        countOfItems++;
    }



    public void showRentEquipment() {

        int indexOfArray;

        for (indexOfArray = 0; indexOfArray < countOfItems; indexOfArray++) {
            units[indexOfArray].showRentEquipment();
        }
    }


    public void Close() {

        try (FileWriter writer = new FileWriter("rent_equipment.txt", false)) {

            for (int i=0;i<countOfItems;i++) {

                writer.append(units[i].getCategory()+" ");
                writer.append(units[i].getTitle()+" ");
                writer.append(units[i].getPrice()+" ");
                writer.append(units[i].getCount()+" ");
                writer.append(System.lineSeparator());
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
