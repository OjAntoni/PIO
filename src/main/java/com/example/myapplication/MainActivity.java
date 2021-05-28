package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    private ImageButton goToRecipe;
    private TextView plList;
    public static RecipeContainer recipes = new RecipeContainer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnGoToRecipeButton();
        plList = findViewById(R.id.recipesPanel);
        recipes.setRecipes(Reader.readData());
        for(int i = 0; i < recipes.getLength(); i++){
            Recipe currRecipe = recipes.getRecipe(i);
            plList.append(currRecipe.getName()+"\n");
            plList.append(currRecipe.getCategory()+"\n");
            plList.append(currRecipe.getIngridients()+"\n");
            plList.append(currRecipe.getCookingTime()+"\n");
            plList.append(currRecipe.getMethod());
            plList.append("\n");
            plList.append("\n");
        }

    }
    public void addListenerOnGoToRecipeButton() {
        goToRecipe = (ImageButton)findViewById(R.id.goToRecipe);

        goToRecipe.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, NewRecipeActivity.class);
                        startActivity(intent);
                    }
                }

        );
    }

    public void addListenerOnGoToCategoriesButton(){}


}



