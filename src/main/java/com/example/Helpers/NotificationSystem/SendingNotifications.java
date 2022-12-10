package com.example.Helpers.NotificationSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SendingNotifications {

    public static void sendingNotifications(String fileName,String text){
        try {
            File file = new File(fileName);
            if(file.createNewFile()){
                System.out.println("The file is created successfully!");
                FileWriter myWriter = new FileWriter(fileName,true);
                myWriter.write(text + "\n");
                myWriter.close();
            }
            else{
                System.out.println("Successfully wrote to the file.");
                FileWriter myWriter = new FileWriter(fileName,true);
                myWriter.write(text + "\n");
                myWriter.close();
            }
            System.out.println("Successfully wrote to the file.");


        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(e);
        }
    }
}
