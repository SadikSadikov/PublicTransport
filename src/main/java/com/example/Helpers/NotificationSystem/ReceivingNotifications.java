package com.example.Helpers.NotificationSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReceivingNotifications {

    public static List<String> receivingNotifications(String fileName){
        File file = new File(fileName);
        if(file.length() == 0){
            System.out.println("File is empty ...");
            return new ArrayList<>();
        }
        else{
            List<String> stringList = new ArrayList<>();
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    stringList.add(line);
                }

                reader.close();
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringList;
        }

    }
}
