package com.example.luigi.spesapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by corsista on 18/04/2018.
 */

public class ProfileActivity extends AppCompatActivity {
    TextView name, email;
    TextView nameOfUser;
    String nome,utente,mail;
    UserDatabaseManager userDatabaseManager;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_profilo);
        Intent fromList=getIntent();
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.email);
        nameOfUser=(TextView)findViewById(R.id.username);

        String username = SharedPreferenceUtility.readUserFromSharedPreferences(ProfileActivity.this);
        userDatabaseManager = new UserDatabaseManager(ProfileActivity.this);
        userDatabaseManager.open();
        Cursor userCursor = userDatabaseManager.readUser(username);
        userCursor.moveToFirst();

        this.nome=userCursor.getString(userCursor.getColumnIndex(userDatabaseManager.KEY_NAME));
        this.utente=userCursor.getString(userCursor.getColumnIndex(userDatabaseManager.KEY_USERNAME));
        this.mail=userCursor.getString(userCursor.getColumnIndex(userDatabaseManager.KEY_MAIL));

        nameOfUser.setText(utente);
        name.setText(nome);
        email.setText(mail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(utente);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity.super.onBackPressed();
            }
        });

    }
}
