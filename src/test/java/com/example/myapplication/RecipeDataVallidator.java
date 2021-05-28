package com.example.myapplication;

import com.google.common.Truth.assertThat;
import org.junit.Test;

public class RecipeDataValidator{

    @Test
    public void bewRecipe_onClick_validatorIsCorrect(){
        assertThat(NewRecipeActivity.dataIsNotNull()).isTrue();
    }

}