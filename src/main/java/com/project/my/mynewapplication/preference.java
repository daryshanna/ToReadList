package com.project.my.mynewapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;



public class preference extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Manager.getTheme());
        setContentView(R.layout.settings_screen);

        RadioButton colordefault = (RadioButton) findViewById(R.id.color_def);
        RadioButton colorRed = (RadioButton) findViewById(R.id.color_red);
        RadioButton colorGreen = (RadioButton) findViewById(R.id.color_green);
        RadioButton colorBlue = (RadioButton) findViewById(R.id.color_blue);

        if (2131230886 == Manager.getTheme()) {
            colorRed.setChecked(true);
        }
        if (2131230885 == Manager.getTheme()) {
            colorGreen.setChecked(true);
        }
        if (2131230884 == Manager.getTheme()) {
            colorBlue.setChecked(true);
        }
        if (2131230883 == Manager.getTheme()) {
            colordefault.setChecked(true);
        }
    }

    public void onRadioButtonClicked (View view){
        boolean checked = ((RadioButton)view).isChecked();

        switch (view.getId()){
            case R.id.color_def:
                if (checked) {
                    Manager.setTheme(R.style.AppTheme);
                } break;
            case R.id.color_red:
                if (checked) {
                    Manager.setTheme(R.style.AppTheme_Red);
                } break;
            case R.id.color_green:
                if (checked) {
                    Manager.setTheme(R.style.AppTheme_Green);
                } break;
            case R.id.color_blue:
                if (checked) {
                    Manager.setTheme(R.style.AppTheme_Blue);
                } break;
        }
        preference.this.recreate();
    }
}
