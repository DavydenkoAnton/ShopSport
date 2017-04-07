package com.davydenko.core;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.out;

class Client {

    private String firstName;
    private String lastName;
    private int countItems;
    private List<String> lines;
    private ArrayList<Client> client;
    private ArrayList<SportEquipment> sportEquip;
    private SportEquipment se;


    public Client(String[] _item, Integer _countTokens) {

        sportEquip=new ArrayList<SportEquipment>();

        this.countItems = Integer.parseInt(_item[0]);
        this.firstName = _item[1];
        this.lastName = _item[2];

        _countTokens--;

        for (int i = 3; i < _countTokens; i += 3) {
            sportEquip.add(new SportEquipment(_item[i], _item[i + 1], _item[i + 2], "0"));
        }


    }


    public Client() {

        client = new ArrayList<Client>();

        getClientsFromFile();
    }


    public Client(String _firstname,String _lastname) {

        this.firstName=_firstname;
        this.lastName=_lastname;
        countItems=0;
    }




    private void getClientsFromFile() {

        StringTokenizer strTok;

        try {
            lines = Files.readAllLines(Paths.get("clients.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String _client : lines) {

            strTok = new StringTokenizer(_client);

            int countTokens = strTok.countTokens();
            String[] _items = new String[countTokens + 1];

            for (int i = 0; i < countTokens; i++) {
                _items[i] = strTok.nextToken();
            }

            client.add(new Client(_items, countTokens));
        }
    }



    public boolean checkClient(String _firstName, String _lastName) {

        boolean check = false;

        for (Client _cl : client) {
            if (_cl.firstName.equals(_firstName) && _cl.lastName.equals(_lastName) && _cl.countItems < 3) {
                out.println(_firstName + " " + _lastName + " have " + _cl.countItems + " item(s)");
                check = true;
            } else {
                out.println(firstName + " " + lastName + " have " + countItems + " item(s)");
            }
        }

        return check;
    }


    public void addClient(String _firstname, String _lastname) {

        //_firstname="tony";
        //_lastname="stark";

        boolean check=true;

        for(Client cl:client){
            if(cl.firstName.equals(_firstname)&&cl.lastName.equals(_lastname)){
                check=false;
            }
        }

        if(check){
            client.add(new Client(_firstname,_lastname));
        }else {
            out.println("Alredy have "+_firstname+" "+_lastname);
        }
    }




    public boolean isClient(String _firstname, String _lastname) {

        boolean check = false;

        for (Client _client : client) {
            if (_client.firstName.equals(_firstname) && _client.lastName.equals(_lastname)) {
                //out.println("\n" + firstName + " " + lastName + " have " + _client.countItems + " item(s)");
                check = true;
            }
        }
        return check;
    }





    public boolean checkClientItem(String _firstname, String _lastname) {

        boolean check = false;

        for (Client _client : client) {
            if (_client.firstName.equals(_firstname) && _client.lastName.equals(_lastname) && _client.countItems < 3) {
                //out.println("\n" + firstName + " " + lastName + " have " + _client.countItems + " item(s)");
                check = true;
            }
        }
        return check;
    }





    public void getEquipment(String _firstname, String _lastname, Map<SportEquipment, Integer> _goods, RentUnit _rentUnits) {

        Scanner _scanner = new Scanner(System.in);
        String _item;
        boolean check = true;

        for (Client _client : client) {
            if (_client.firstName.equals(_firstname) && _client.lastName.equals(_lastname) && _client.countItems < 3) {

                while (check) {

                    out.print("Choose product: ");

                    _item = _scanner.next();
                    //_item = "Adidas";
                    for (SportEquipment equip : _goods.keySet()) {
                        if (equip.findEquipment(_item) && equip.getCount() > 0) {

                            Integer rentCountItem=equip.getCount();

                            _rentUnits.setUnit(equip.getCategory(),equip.getTitle(),equip.getPrice(),rentCountItem.toString());

                            equip.countMinus();

                            _client.sportEquip.add(new SportEquipment(equip.getCategory(), equip.getTitle(), equip.getPrice(), "0"));

                            _client.countItems++;

                            check = false;
                        }
                    }
                }
            }
        }
    }




    public void Close() {

        try (FileWriter writer = new FileWriter("clients.txt", false)) {

            for (Client _cl : client) {
                writer.append(_cl.countItems + " ");
                writer.append(_cl.firstName + " ");
                writer.append(_cl.lastName + " ");
                if(_cl.countItems!=0) {
                    for (SportEquipment se : _cl.sportEquip) {
                        writer.append(se.getTitle() + " ");
                        writer.append(se.getCategory() + " ");
                        writer.append(se.getPrice() + " ");
                    }
                }

                writer.append(System.lineSeparator());

            }

            writer.flush();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }




}
