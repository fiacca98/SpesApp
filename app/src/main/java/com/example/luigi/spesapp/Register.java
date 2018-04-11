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

/**
 * Created by Gabriele on 09/04/2018.
 */

public class Register extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private Cursor cursor;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        if(SharedPreferenceUtility.readUserFromSharedPreferences(getApplicationContext()) != null){
            Intent intent = new Intent(Register.this, MainActivity.class);
            intent.putExtra("username", SharedPreferenceUtility.readUserFromSharedPreferences(getApplicationContext()));
            startActivity(intent);
        }
        setContentView(R.layout.register_layout);

        final EditText editUsername=(EditText)findViewById(R.id.username);
        final EditText editName=(EditText)findViewById(R.id.name);
        final EditText editMail=(EditText)findViewById(R.id.email);
        final EditText editPassword=(EditText)findViewById(R.id.password);

        Button register = (Button) findViewById(R.id.register);

        databaseManager=new DatabaseManager(this);
        databaseManager.open();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String use=editUsername.getText().toString();
                databaseManager.createUser(editUsername.getText().toString(),editName.getText().toString(),editMail.getText().toString(),editPassword.getText().toString(), false);
                cursor=databaseManager.readUser(editUsername.getText().toString());
                if (String.valueOf(cursor.moveToFirst()).equals("true")) {
                    SharedPreferenceUtility.setUserOnSharedPreferences(editUsername.getText().toString(), Register.this);
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    intent.putExtra("username", editUsername.getText().toString());
                    startActivity(intent);
                }
                databaseManager.close();
            }
        });

        TextView accedi = (TextView) findViewById(R.id.accediora);

        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
