package com.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;


import com.example.atry.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.helper.InputValidation;
import com.sql.DatabaseHelper;

import java.util.Calendar;


public class ForgotPassword extends AppCompatActivity {

    private TextInputEditText textInputEditTextEmail;
    private TextInputLayout textInputLayoutEmail;

    private AppCompatButton appCompatButtonConfirm;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    private NestedScrollView nestedScrollView;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        getSupportActionBar().hide();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();
        setBackground_for_activity();
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        appCompatButtonConfirm = (AppCompatButton) findViewById(R.id.appCompatButtonConfirm);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);

        setTitle("Recover password");
        appCompatButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyFromSQLite();
            }
        });

    }

    private void verifyFromSQLite(){

        if (textInputEditTextEmail.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill your email", Toast.LENGTH_SHORT).show();
            return;
        }


        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
            Intent accountsIntent = new Intent(this, ConfirmPassword.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText("");
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void setBackground_for_activity(){

        NestedScrollView nestedScrollView = findViewById(R.id.nestedScrollView);

        int timeOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if(timeOfDay < 12){
            //morning
            nestedScrollView.setBackground(getDrawable(R.drawable.good_morning_img));
        } else if (timeOfDay < 16){
            //afternoon
            nestedScrollView.setBackground(getDrawable(R.drawable.good_morning_img));

        } else if(timeOfDay < 20){
            //evening
            nestedScrollView.setBackground(getDrawable(R.drawable.good_night_img));

        } else {
            //night
            nestedScrollView.setBackground(getDrawable(R.drawable.good_night_img));
        }
    }
}
