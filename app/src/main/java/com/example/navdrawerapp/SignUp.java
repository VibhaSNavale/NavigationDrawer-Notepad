package com.example.navdrawerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText edtSignupEmail, edtSignupUsername, edtSignupPassword;
    private Button btnSignup, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);

        edtSignupEmail = findViewById(R.id.edtSignupEmail);
        edtSignupUsername = findViewById(R.id.edtSignupUsername);
        edtSignupPassword = findViewById(R.id.edtSignupPassword);

        edtSignupPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if(keyCode == KeyEvent.KEYCODE_ENTER  //if the virtual keyboard is active and user clicks on enter button
                        && keyEvent.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnSignup);

                }

                return false;
            }
        });


        btnSignup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null) {
            //ParseUser.getCurrentUser().logOut();
            transitionToHome();
        }

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btnSignup:

                if(edtSignupEmail.getText().toString().equals("")){

                    FancyToast.makeText(SignUp.this, "Email required.",
                            Toast.LENGTH_SHORT, FancyToast.INFO, false).show();

                }
                else if(edtSignupUsername.getText().toString().equals("")){

                    FancyToast.makeText(SignUp.this, "Username required.",
                            Toast.LENGTH_SHORT, FancyToast.INFO, false).show();

                }
                else if(edtSignupPassword.getText().toString().equals("")){

                    FancyToast.makeText(SignUp.this, "Password required.",
                            Toast.LENGTH_SHORT, FancyToast.INFO, false).show();

                }
                else {

                    final ParseUser appuser = new ParseUser();
                    appuser.setUsername(edtSignupUsername.getText().toString());
                    appuser.setPassword(edtSignupPassword.getText().toString());
                    appuser.setEmail(edtSignupEmail.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up " + edtSignupUsername.getText().toString());
                    progressDialog.show();

                    appuser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {

                                FancyToast.makeText(SignUp.this, appuser.getUsername() + " is signed up successfully.",
                                        Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();

                                transitionToHome();
                            }
                            else {
                                FancyToast.makeText(SignUp.this, "There was an error: " + e.getMessage(),
                                        Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                            }

                            progressDialog.dismiss();
                        }
                    });
                }

                break;

            case R.id.btnLogin:

                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);

                break;

        }

    }


    //executed when the root layout is tapped (to hide the virtual keyboard)
    public void rootLayoutTapped(View view) {
        try {

            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        }
        catch (Exception e)  {
            e.printStackTrace();
        }
    }

    private void transitionToHome()
    {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        finish(); //call this method as we shouldn't allow the sign up/log in page to show when back button is clicked
    }
}
