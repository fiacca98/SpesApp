package com.example.luigi.spesapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.String.valueOf;

/**
 * Created by luigi on 09/04/2018.
 */

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameGet = SharedPreferenceUtility.readUserFromSharedPreferences(getApplicationContext());
        if (usernameGet == null) {
            setContentView(R.layout.login);
            Button detailButton = (Button) findViewById(R.id.accedi);
            detailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText username = (EditText) findViewById(R.id.user);
                    String usernameString = valueOf(username.getText());
                    EditText password = (EditText) findViewById(R.id.password);
                    String passwordString = valueOf(password.getText());
                    UserDatabaseManager dbManager = new UserDatabaseManager(getApplicationContext());
                    dbManager.open();
                    Cursor cursor = dbManager.readUser(usernameString);
                    Log.d("cursor", String.valueOf(cursor.moveToFirst()));
                    if (!String.valueOf(cursor.moveToFirst()).equals("false")) {

                        SharedPreferenceUtility.setUserOnSharedPreferences(usernameString, cursor.getInt(cursor.getColumnIndex(dbManager.KEY_ID)), Login.this);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("username", usernameString);
                        startActivity(intent);
                        finish();
                    } else {
                        TextView notReg = (TextView) findViewById(R.id.notreg);
                        notReg.setVisibility(View.VISIBLE);
                        password.setText("");
                    }
                    dbManager.close();
                }
            });
            TextView register = (TextView) findViewById(R.id.goToRegister);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Register.class);
                    startActivity(intent);
                }
            });
        } else {
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra("username", usernameGet);
            startActivity(intent);
            finish();
        }
    }
}
