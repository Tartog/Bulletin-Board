package com.example;

import java.io.File;
import java.io.IOException;

public class DataBase {
    public static final String filePath = "C:\\Program Files\\Apache Software Foundation\\" +
            "Tomcat 9.0\\bin\\DataBase.txt";
    public synchronized void fileCheck(){
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("Файл создан");
            } else {
                System.out.println("Файл уже существует");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
