package com.example.myapplication;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecipeContainer {
    private ArrayList<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe r){
        recipes.add(r);
    }

    public int getLength(){
        return recipes.size();
    }

    public Recipe getRecipe(int index){
        if (index >= recipes.size())
            throw new IndexOutOfBoundsException();
        return recipes.get(index);
    }

    public void setRecipes(ArrayList<Recipe> recipes){
        this.recipes = recipes;
    }
}
  