package edu.self.simplesavingsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private EditText e1;
    private EditText e2;
    private String name;
    private String loc;
    private Boolean color;
    // initiate a Switch
    private Switch switch1;


    private SharedPreferences mPreferences;

    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        get();

        this.e1 = (EditText) findViewById(R.id.editText);
        this.e2 = (EditText) findViewById(R.id.editText2);
        e1.setText(name);
        e2.setText(loc);

        switch1 = (Switch) findViewById(R.id.switch1);
        if(color) {
            switch1.setChecked(true);
            getWindow().getDecorView().setBackgroundColor(Color.GRAY);
        }
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch1.isChecked()) {
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                }
                else {
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
            }
        });

    }

    private void get(){
        String sharedPrefFile = "com.example.simplesavingsapp";
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        name = mPreferences.getString("name", "n");
        loc = mPreferences.getString("loc", "l");
        color = mPreferences.getBoolean("color", false);
    }

    private void set() {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("name", e1.getText().toString());
        preferencesEditor.putString("loc", e2.getText().toString());
        preferencesEditor.putBoolean("color", switch1.isChecked());
        preferencesEditor.apply();
    }


    @Override
    protected void onPause() {
        super.onPause();
        set();
    }

    @Override
    protected void onResume() {
        super.onResume();
        get();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        set();
    }

    public void clearInfo (View view){
        e1.setText("");
        e2.setText("");
        switch1.setChecked(false);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void close(View view){
        finishAndRemoveTask();
    }



}
