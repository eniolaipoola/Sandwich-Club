package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try{
            JSONObject jsonObject = new JSONObject(json);

            JSONObject sandwichName =  jsonObject.getJSONObject("name");
            String mainName = sandwichName.getString("mainName");

            JSONArray alsoKnownAsArray = sandwichName.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<String>();
            if(alsoKnownAsArray  != null){
                for(int i = 0; i < alsoKnownAsArray.length(); i++){
                    alsoKnownAsList.add(alsoKnownAsArray.getString(i));
                }
            }


            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String imageUrl = jsonObject.getString("image");


            JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<String>();
            if(ingredientsArray  != null){
                for(int i = 0; i < ingredientsArray.length(); i++){
                    alsoKnownAsList.add(ingredientsArray.getString(i));
                }
            }

            Sandwich sandwich = new Sandwich();
            sandwich.setMainName(mainName);
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setDescription(description);
            sandwich.setIngredients(ingredientsList);
            sandwich.setImage(imageUrl);

            return sandwich;

        } catch (JSONException exception){
            exception.printStackTrace();
        }

        return null;
    }
}
