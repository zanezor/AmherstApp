package com.example.zeinaamhaz.amherstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp;
    EditText editTextUserName, editTextPassword;
    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        editTextUserName = (EditText) findViewById(R.id.editTextUserNameToLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordToLogin);

        btnSignIn = (Button) findViewById(R.id.buttonSignIN);
        btnSignUp = (Button) findViewById(R.id.buttonSignUP);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intentSignUP = new Intent(getApplicationContext(),
                        SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);
                if (password.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this,
                            "Congrats: Login Successful", Toast.LENGTH_LONG)
                            .show();
                    Intent main = new Intent(MainActivity.this, Welcome.class);
                    startActivity(main);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this,
                            "User Name or Password does not match",
                            Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }

}
