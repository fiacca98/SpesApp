package com.example.luigi.spesapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                cursor=databaseManager.verifyInsert(editUsername.getText().toString());
                if (cursor != null) {
                    Log.d("cursoe", String.valueOf(cursor.moveToFirst()));
                }
                databaseManager.close();
            }
        });

    }
}
