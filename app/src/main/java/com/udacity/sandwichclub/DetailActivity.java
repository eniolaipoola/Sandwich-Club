package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView originTextView;
    TextView descriptionTextView;
    TextView ingredientTextView;
    TextView alsoKnownAsTextView;
    TextView placeOfOrigin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_placeholder)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        originTextView = findViewById(R.id.origin_tv);
        descriptionTextView = findViewById(R.id.description_tv);
        placeOfOrigin = findViewById(R.id.tv_place_of_origin);
        alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        ingredientTextView = findViewById(R.id.ingredients_tv);

        originTextView.append(sandwich.getMainName());
        descriptionTextView.append(sandwich.getDescription());
        placeOfOrigin.append(sandwich.getPlaceOfOrigin());

        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        alsoKnownAsTextView.append(listToString(alsoKnownAsList));

        List<String> ingredientList = sandwich.getIngredients();
        ingredientTextView.append(listToString(ingredientList));

    }

    public String listToString(List<String> list){
        StringBuilder builder = new StringBuilder();
        for(String otherNames : list){
            builder.append(otherNames + ",");
        }
        return builder.toString();
    }
}
