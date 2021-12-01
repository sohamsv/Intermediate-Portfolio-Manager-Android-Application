package com.app.cjportfoliomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {
    public String password="dr";
    EditText P1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        P1 = findViewById(R.id.editTextTextPassword1);
    }


    public void clientreg(View view) {
            if (P1.getText().toString().length()==0 || !P1.getText().toString().equals(password)){
                Snackbar.make(view, "Please Enter Correct Password", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }else{
                Intent intent2 = new Intent(this, ScrollingActivity.class);
                startActivity(intent2);
            }
        }

}