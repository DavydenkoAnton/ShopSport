package com.davydenko.core;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


import static java.lang.System.out;


public class Shop {

    private Map<SportEquipment, Integer> goods = new HashMap<SportEquipment, Integer>();
    private Client client;
    private RentUnit rentUnits;
    private List<String> lines;
    private Scanner inSc = new Scanner(System.in);
    private String inScStr;


    public Shop() {

        this.rentUnits = new RentUnit();
        this.client = new Client();
        getEquipmentFromFile();

    }


    public void ShowEquipment() {

        out.println("\nEquipment in shop:\n");

        for (SportEquipment equip : goods.keySet()) {
            equip.showEquipment();
        }
    }


    public void ShowRentEquipment() {

        out.println("\nEquipment in rent:\n");

        rentUnits.showRentEquipment();

    }


    public void FindItem() {

        boolean check = false;

        out.print("You looking for: ");

        inScStr = inSc.next();

        for (SportEquipment equip : goods.keySet()) {
            if (equip.findEquipment(inScStr)) {
                equip.showEquipment();
            }
        }

    }


    private void getEquipmentFromFile() {

        try {
            lines = Files.readAllLines(Paths.get("shop_equipment.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        setEquipment();

        try {
            lines = Files.readAllLines(Paths.get("rent_equipment.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        setRentEquipment();

    }


    private void setEquipment() {

        StringTokenizer strTok;

        for (String unit : lines) {

            strTok = new StringTokenizer(unit);

            goods.put(new SportEquipment(strTok.nextToken(), strTok.nextToken(), strTok.nextToken(), strTok.nextToken()), 1);

        }

    }


    private void setRentEquipment() {

        StringTokenizer strTok;

        for (String unit : lines) {

            strTok = new StringTokenizer(unit);

            rentUnits.setUnit(strTok.nextToken(), strTok.nextToken(), strTok.nextToken(), strTok.nextToken());

        }

    }


    public void TakeEquipment() {

        String _firstName;
        String _lastName;

        out.print("Print your firstname: ");
        _firstName = inSc.next();
        //_firstName = "bob";

        out.print("Print your lastname: ");
        _lastName = inSc.next();
        //_lastName = "dylan";

        if (client.isClient(_firstName, _lastName) && client.checkClientItem(_firstName, _lastName)) {
            client.getEquipment(_firstName, _lastName, goods,rentUnits);
        }
    }


    public void ShowClients() {

    }


    public void AddClient() {
        String _firstName;
        String _lastName;

        out.print("Print your firstname: ");
        _firstName = inSc.next();

        out.print("Print your lastname: ");
        _lastName = inSc.next();

        client.addClient(_firstName,_lastName);
    }


    public void Close() {

        client.Close();
        rentUnits.Close();

        try (FileWriter writer = new FileWriter("shop_equipment.txt", false)) {

            for (SportEquipment equip : goods.keySet()) {
                writer.append(equip.getTitle()+" ");
                writer.append(equip.getCategory()+" ");
                writer.append(equip.getPrice()+" ");
                writer.append(equip.getCount()+" ");
                writer.append(System.lineSeparator());
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}


