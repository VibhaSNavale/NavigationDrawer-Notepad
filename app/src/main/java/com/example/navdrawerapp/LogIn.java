package com.example.navdrawerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignupLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        setTitle("Log In");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if(keyCode == KeyEvent.KEYCODE_ENTER  //if the virtual keyboard is active and user clicks on enter button
                        && keyEvent.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnLoginActivity);

                }

                return false;
            }
        });

        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignupLoginActivity = findViewById(R.id.btnSignupLoginActivity);


        btnLoginActivity.setOnClickListener(this);
        btnSignupLoginActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){

            ParseUser.getCurrentUser().logOut();

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnLoginActivity:

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Logging in");
                progressDialog.show();

                ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null){

                            FancyToast.makeText(LogIn.this, user.getUsername() + " is logged in successfully.",
                                    Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                            transitionToHome();
                        }
                        else {
                            FancyToast.makeText(LogIn.this, e.getMessage(),
                                    Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                        progressDialog.dismiss();

                    }
                });
                break;

            case R.id.btnSignupLoginActivity:

                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
                break;

        }

    }

    private void transitionToHome()
    {
        Intent intent = new Intent(LogIn.this, MainActivity.class);
        startActivity(intent);
        finish();
        onDestroy();
    }

}