package com.davydenko;


import java.io.IOException;
import java.util.Scanner;

import com.davydenko.core.Shop;
import com.sun.security.ntlm.Client;

import static java.lang.System.out;


class Main {

    public static void main(String[] args) throws IOException {
//test
        boolean exit = true;

        Shop shop = new Shop();

        while (exit) {

            out.println("\nPress 1: Equipment in shop");
            out.println("Press 2: Equipment in rent");
            out.println("Press 3: Take an equipment");
            out.println("Press 4: Find an equipment");
            out.println("Press 5: Add client");
            out.println("Press 6: Exit");


            Scanner in = new Scanner(System.in);
            String input = in.next();

            switch (input) {
                case "1":
                    shop.ShowEquipment();
                    break;
                case "2":
                    shop.ShowRentEquipment();
                    break;
                case "3":
                    shop.TakeEquipment();
                    break;
                case "4":
                    shop.FindItem();
                    break;
                case "5":
                    shop.AddClient();
                    break;
                case "6":
                    shop.Close();
                    exit=false;
                    break;
            }

        }


    }

}
