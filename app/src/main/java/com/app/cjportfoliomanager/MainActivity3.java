package com.app.cjportfoliomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity3 extends AppCompatActivity {
    public String password2="dr";
    EditText P2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        P2 = findViewById(R.id.editTextTextPassword1);
    }

    public void pass2(View view) {
        if (P2.getText().toString().length()==0 || !P2.getText().toString().equals(password2)){
            Snackbar.make(view, "Please Enter Correct Password", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else{
            Intent intent3 = new Intent(this, MainActivity4.class);
            startActivity(intent3);
        }
    }
}