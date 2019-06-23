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

            JSONObject sandwichName =  jsonObject.optJSONObject(SandwichConstants.SANCWICH_NAME);
            String mainName = sandwichName.optString(SandwichConstants.SANCWICH_MAIN_NAME);

            JSONArray alsoKnownAsArray = sandwichName.optJSONArray(SandwichConstants.SANCWICH_ALSO_KNOWN_AS);
            List<String> alsoKnownAsList = new ArrayList<String>();
            if(alsoKnownAsArray  != null){
                for(int i = 0; i < alsoKnownAsArray.length(); i++){
                    alsoKnownAsList.add(alsoKnownAsArray.optString(i));
                }
            }


            String placeOfOrigin = jsonObject.optString(SandwichConstants.SANCWICH_PLACE_OF_ORIGIN);
            String description = jsonObject.optString(SandwichConstants.SANCWICH_DESCRIPTION);
            String imageUrl = jsonObject.optString(SandwichConstants.SANCWICH_IMAGE);


            JSONArray ingredientsArray = jsonObject.optJSONArray(SandwichConstants.SANCWICH_INGREDIENTS);
            List<String> ingredientsList = new ArrayList<String>();
            if(ingredientsArray  != null){
                for(int i = 0; i < ingredientsArray.length(); i++){
                    ingredientsList.add(ingredientsArray.optString(i));
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
