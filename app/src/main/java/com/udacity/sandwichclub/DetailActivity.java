package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView mainNameTextView;
    TextView originTextView;
    TextView descriptionTextView;
    TextView ingredientTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            Log.d("Debug", "check, is intent null?: ");
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        Log.d("Debug", "position: " +  position);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent

            Log.d("Debug", "position isn't default position, it shouldn't come here: ");
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Log.d("Debug", "json data is " + json);
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        Log.d("Debug", "Log the value of sandwich: " + sandwich);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        originTextView = findViewById(R.id.origin_tv);
        descriptionTextView = findViewById(R.id.tv_description);

        if(sandwich != null){
            originTextView.append(sandwich.getPlaceOfOrigin());
            descriptionTextView.append(sandwich.getDescription());
        }
    }
}
