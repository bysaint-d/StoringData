package com.shaliyev.storingdatajava;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextAge;
    TextView textViewName, textViewAge;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextName = findViewById(R.id.editTextName);
        editTextAge  = findViewById(R.id.editTextAge);
        textViewName = findViewById(R.id.textViewName);
        textViewAge  = findViewById(R.id.textViewAge);


        sharedPreferences = this.getSharedPreferences("com.shaliyev.storingdatajava", Context.MODE_PRIVATE);
        int storedAge = sharedPreferences.getInt("storedAge",0);
        String storedName = sharedPreferences.getString("storedName","");
        textViewAge.setText("Your age"+ storedAge);
        textViewName.setText("Your name"+ storedName);
        if (storedAge==0) {
            textViewAge.setText("Your age: ");
        }    else{
            textViewAge.setText("Your age:"+ storedAge);
        }
    }

    public void save(View view) {

        if (!editTextName.getText().toString().matches("")) {
            String username = editTextName.getText().toString();
            textViewName.setText("Your Name: " + username);
            sharedPreferences.edit().putString("storedName",username).apply();

        }

        if (!editTextAge.getText().toString().matches("")) {
            int userAge = Integer.parseInt(editTextAge.getText().toString());
            textViewAge.setText("Your Age: " + userAge);
            sharedPreferences.edit().putInt("storedAge",userAge).apply();

        }


    }
    public void delete(View view) {
        int storedData = sharedPreferences.getInt("storedAge",0);
        if (storedData != 0) {
            sharedPreferences.edit().remove("storedAge").apply();
            textViewAge.setText("Your age: ");
        }

    }
}
