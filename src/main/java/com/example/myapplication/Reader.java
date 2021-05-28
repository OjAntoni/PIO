package com.example.myapplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

    public static ArrayList<Recipe> readData(){
        ArrayList<Recipe> recipeData = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(DataFile.FILE_NAME))) {
            String[] data = new String[5];
            String singleData;
            Recipe r = new Recipe();
            int i = 0;
            while ((singleData=br.readLine())!=null){
                if(i==4){
                    i = 0;
                    r.setName(data[0]);
                    r.setCategory(data[1]);
                    r.setIngridients(data[2]);
                    r.setCookingTime(data[3]);
                    r.setMethod(data[4]);
                    recipeData.add(r);
                    r = new Recipe();
                }
                data[i] = singleData;
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return recipeData;
        }

    }
}