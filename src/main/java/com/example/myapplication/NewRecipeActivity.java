package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NewRecipeActivity extends AppCompatActivity {

    private EditText setName;
    private EditText setCategory;
    private EditText setIngridients;
    private EditText setCookingTime;
    private EditText setMethod;
    private Button save_button;

    private static Recipe recipe = new Recipe();
    private static String name;
    private static String category;
    private static String ingridients;
    private static String cookingTime;
    private static String method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        setName = findViewById(R.id.setName);
        setCategory = findViewById(R.id.setCategory);
        setIngridients = findViewById(R.id.setIngridients);
        setCookingTime = findViewById(R.id.setCookingTime);
        setMethod = findViewById(R.id.setMethod);
        save_button = findViewById(R.id.save_button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setName.getText().toString().trim().equals("") ||
                   setCategory.getText().toString().trim().equals("") ||
                   setIngridients.getText().toString().trim().equals("") ||
                   setCookingTime.getText().toString().trim().equals("") ||
                   setMethod.getText().toString().trim().equals("")){
                    Toast.makeText(NewRecipeActivity.this, R.string.incorrect_input, Toast.LENGTH_LONG).show();
                }
                else {
                    recipe.setName(setName.getText().toString().trim());
                    recipe.setCategory(setCategory.getText().toString().trim());
                    recipe.setIngridients(setIngridients.getText().toString().trim());
                    recipe.setCookingTime(setCookingTime.getText().toString().trim());
                    recipe.setMethod(setMethod.getText().toString().trim());
                    MainActivity.recipes.addRecipe(recipe);
                    Intent intent = new Intent(NewRecipeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void write(View view){
        try(FileOutputStream fileOutput = openFileOutput(DataFile.FILE_NAME, MODE_PRIVATE)) {
            fileOutput.write(recipe.getName().getBytes());
            fileOutput.write(recipe.getCategory().getBytes());
            fileOutput.write(recipe.getIngridients().getBytes());
            fileOutput.write(recipe.getCookingTime().getBytes());
            fileOutput.write(recipe.getMethod().getBytes());
            Toast.makeText(NewRecipeActivity.this, "Your recipe saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(View view){
        try {
            FileInputStream fileInput = openFileInput(DataFile.FILE_NAME);
            InputStreamReader reader = new InputStreamReader(fileInput);
            BufferedReader buffer = new BufferedReader(reader);
            StringBuffer strBuffer = new StringBuffer();
            String lines;
            int i = 0;
            while ((lines = buffer.readLine()) != null){
                Recipe r = new Recipe();
                switch (i){
                    case 0: r.setName(lines);
                    case 1: r.setCategory(lines);
                    case 2: r.setIngridients(lines);
                    case 3: r.setCookingTime(lines);
                    case 4: r.setMethod(lines);
                }
                i++;
                if(i==4){
                    i=0;
                    MainActivity.recipes.addRecipe(r);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




















    public String[] getRecipeData(){
        String data[] = new String[5];
        data[0] = getName();
        data[1] = getCategory();
        data[2] = getIngridients();
        data[3] = getCookingTime();
        data[4] = getMethod();
        return data;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getIngridients() {
        return ingridients;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public String getMethod() {
        return method;
    }

    public static boolean dataIsNotNull(){
        return (name!=null && category!=null && ingridients!=null && cookingTime!=null && method!=null);
    }
}