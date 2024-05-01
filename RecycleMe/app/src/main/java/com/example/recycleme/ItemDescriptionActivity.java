package com.example.recycleme;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.HashSet;
import java.util.Set;


public class ItemDescriptionActivity extends BaseActivity {
    private TextView productNameTextView;
    private TextView descriptionView;

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