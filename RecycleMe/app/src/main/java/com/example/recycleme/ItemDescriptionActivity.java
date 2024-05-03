package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.HashSet;
import java.util.Set;


public class ItemDescriptionActivity extends BaseActivity {
    private TextView productNameTextView;
    private TextView descriptionView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_item_description, contentFrameLayout);
        initView();

    }

    private void initView() {
        String productName = getIntent().getStringExtra("PRODUCT_NAME");
        productNameTextView = findViewById(R.id.des_product_name);
        productNameTextView.setText(productName);


        String brandName = getIntent().getStringExtra("BRAND_NAME");
        String material = getIntent().getStringExtra("MATERIAL_TEXT");
        String value = getIntent().getStringExtra("VALUE");



        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("This is ");
        stringBuilder.append(getArticleDeterminer(productName));
        stringBuilder.append(productName);
        stringBuilder.append(" by ");
        stringBuilder.append(brandName);
        stringBuilder.append(".\n");
        stringBuilder.append("It is made of ");
        stringBuilder.append(material);
        stringBuilder.append(" and has a value of ");
        stringBuilder.append(value);
        stringBuilder.append(".");

        descriptionView = findViewById(R.id.descriptionPara);
        descriptionView.setText(stringBuilder.toString());

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

    }

    private String getArticleDeterminer(String noun) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('u');
        vowels.add('e');
        vowels.add('o');
        vowels.add('a');
        vowels.add('i');
        Character firstChar = Character.toLowerCase(noun.charAt(0));
        if (vowels.contains(firstChar)) {
            return "an ";
        }
        return "a ";
    }


}