package com.example.luigi.spesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.String.valueOf;

/**
 * Created by luigi on 09/04/2018.
 */

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameGet = SharedPreferenceUtility.readUserFromSharedPreferences(getApplicationContext());
        if( usernameGet == null) {
            setContentView(R.layout.login);
            Button detailButton = (Button) findViewById(R.id.accedi);
            detailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText username = (EditText) findViewById(R.id.user);
                    String usernameString = valueOf(username.getText());
                    EditText password = (EditText) findViewById(R.id.password);
                    String passwordString = valueOf(password.getText());
                    SharedPreferenceUtility.setUserOnSharedPreferences(usernameString,passwordString,Login.this);
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    intent.putExtra("username", usernameString);
                    startActivity(intent);
                }
            });
        }
        else {
            Intent intent = new Intent(Login.this,MainActivity.class);
            intent.putExtra("username", usernameGet);
            startActivity(intent);
        }
    }
}
