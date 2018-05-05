package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String INGREDIENTS = "ingredients";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";

    public static Sandwich parseSandwichJson(String json)
    {

        String mainName;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = new ArrayList<>();


        try {

            // Create base JSONObject
            JSONObject baseJson = new JSONObject(json);

            // Create sandwich JSONObject
            JSONObject nameJsonObject = baseJson.getJSONObject(NAME);
            mainName = nameJsonObject.optString(MAIN_NAME);

            // Retrieve also know as array
            JSONArray alsoKnownAsArray = nameJsonObject.getJSONArray(ALSO_KNOWN_AS);
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                if (alsoKnownAsArray != null) {
                    alsoKnownAs.add(alsoKnownAsArray.optString(i));
                }

            }

            placeOfOrigin = baseJson.optString(PLACE_OF_ORIGIN);
            description = baseJson.optString(DESCRIPTION);
            image = baseJson.optString(IMAGE);

            JSONArray ingredientsArray = baseJson.getJSONArray(INGREDIENTS);
            for (int i = 0; i < ingredientsArray.length(); i++) {
                String ingredientsString = ingredientsArray.optString(i);
                ingredients.add(ingredientsString);
            }

            Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            return sandwich;


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("JsonUtils", "Problem parsing JSON results", e);
        }

        return null;
    }
}
