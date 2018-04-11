package com.example.luigi.spesapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Gabriele on 09/04/2018.
 */

public class Register extends AppCompatActivity {

    private UserDatabaseManager userDatabaseManager;
    private Cursor cursor;
    private ImageView photo;
    private final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE=1;
    private final int REQUEST_PHOTO_FROM_GALLERY=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (SharedPreferenceUtility.readUserFromSharedPreferences(getApplicationContext()) != null) {
            Intent intent = new Intent(Register.this, MainActivity.class);
            intent.putExtra("username", SharedPreferenceUtility.readUserFromSharedPreferences(getApplicationContext()));
            startActivity(intent);
        }
        setContentView(R.layout.register_layout);

        final EditText editUsername = (EditText) findViewById(R.id.username);
        final EditText editName = (EditText) findViewById(R.id.name);
        final EditText editMail = (EditText) findViewById(R.id.mail);
        final EditText editPassword = (EditText) findViewById(R.id.password);
        final ImageView photo = (ImageView) findViewById(R.id.photo);

        Button register = (Button) findViewById(R.id.register);

        userDatabaseManager = new UserDatabaseManager(this);
        userDatabaseManager.open();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String use = editUsername.getText().toString();
                userDatabaseManager.createUser(editUsername.getText().toString(), editName.getText().toString(), editMail.getText().toString(), editPassword.getText().toString(), false);
                cursor = userDatabaseManager.readUser(editUsername.getText().toString());
                if (String.valueOf(cursor.moveToFirst()).equals("true")) {
                    SharedPreferenceUtility.setUserOnSharedPreferences(editUsername.getText().toString(), Register.this);
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    intent.putExtra("username", editUsername.getText().toString());
                    startActivity(intent);
                }
                userDatabaseManager.close();
            }
        });

        TextView accedi = (TextView) findViewById(R.id.accediora);

        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    dispatchGetPictureFromGallery();
                } else {
                    ActivityCompat.requestPermissions(Register.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_READ_EXTERNAL_STORAGE: {
// If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchGetPictureFromGallery();
                } else {
// permission denied
                }
                return;
            }
        }
    }

    protected void dispatchGetPictureFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        this.startActivityForResult(intent, REQUEST_PHOTO_FROM_GALLERY);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final ImageView photo=(ImageView)findViewById(R.id.photo);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PHOTO_FROM_GALLERY) {
                Uri photoUri = data.getData();
                String currentPhotoPath = photoUri.getPath();
                photo.setImageURI(photoUri);
//SALVA PATH DA URI con getStringFromUri
            } else {
                Log.i(Register.class.getName(), "Result code != OK -> request code: " +
                        requestCode);
            }
        }
    }

    public String getStringFromUri(Uri uri) {
        String stringUri;
        stringUri = uri.toString();
        return stringUri;
    }

    public Uri getUriFromString(String stringUri) {
        Uri uri = Uri.parse(stringUri);
        return uri;
    }
}
