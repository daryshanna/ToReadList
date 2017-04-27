package com.project.my.mynewapplication;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class Activity2 extends AppCompatActivity {
    Toolbar mToolbar;
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        pic = (ImageView) findViewById(R.id.imageView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mToolbar.setTitle(bundle.getString("BookName"));
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Invisible Monsters")) {
                pic.setImageDrawable(ContextCompat.getDrawable(Activity2.this, R.drawable.pic1));
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Veronica decides to die")) {
                pic.setImageDrawable(ContextCompat.getDrawable(Activity2.this, R.drawable.pic2));
            }
        }
    }
}
