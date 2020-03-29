package com.example.provectus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.provectus.MainActivity.EXTRA_CELL;
import static com.example.provectus.MainActivity.EXTRA_EMAIL;
import static com.example.provectus.MainActivity.EXTRA_GENDER;
import static com.example.provectus.MainActivity.EXTRA_NAME;
import static com.example.provectus.MainActivity.EXTRA_PASSWORD;
import static com.example.provectus.MainActivity.EXTRA_URL;
import static com.example.provectus.MainActivity.EXTRA_USERNAME;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageDetail;
    private TextView nameDetail, usernameDetail, cellDetail, genderDetail, emailDetail, passwordDetail;
    private AnimationDrawable animationDrawable;
    RelativeLayout layout;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        layout = (RelativeLayout)findViewById(R.id.mLayout);
        animationDrawable = (AnimationDrawable)layout.getBackground();

        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

        imageDetail = (ImageView)findViewById(R.id.imageDetail);
        nameDetail = (TextView)findViewById(R.id.textViewNameDetail);
    usernameDetail = (TextView)findViewById(R.id.textViewUsernameDetail);
    cellDetail = (TextView)findViewById(R.id.textViewCellDetail);
    genderDetail = (TextView)findViewById(R.id.textViewGenderDetail);
    emailDetail = (TextView)findViewById(R.id.textViewEmailDetail);
    passwordDetail = (TextView)findViewById(R.id.textViewPasswordDetail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String cell = intent.getStringExtra(EXTRA_CELL);
        String username = intent.getStringExtra(EXTRA_USERNAME);
        String password = intent.getStringExtra(EXTRA_PASSWORD);
        String email = intent.getStringExtra(EXTRA_EMAIL);
        String gender = intent.getStringExtra(EXTRA_GENDER);
        String name = intent.getStringExtra(EXTRA_NAME);

    Picasso.with(this).load(imageUrl).into(imageDetail);
    nameDetail.setText(name);
    usernameDetail.setText(username);
    cellDetail.setText(cell);
    genderDetail.setText(gender);
    emailDetail.setText(email);
    passwordDetail.setText(password);
    }
}
