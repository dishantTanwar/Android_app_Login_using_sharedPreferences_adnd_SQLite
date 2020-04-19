package com.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import com.example.atry.R;
import com.utils.PreferenceUtils;

import java.util.Calendar;


public class UsersActivity extends AppCompatActivity {

    private TextView textViewName;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toast.makeText(this, "Log in successful", Toast.LENGTH_SHORT).show();
        setBackground_for_activity();
        textViewName = (TextView) findViewById(R.id.text1);
        Intent intent = getIntent();

        String name = PreferenceUtils.getName(this);
        if (intent.hasExtra("EMAIL")){
            String nameFromIntent = getIntent().getStringExtra("EMAIL");
            String x = "Welcome, " +name+"\nYour Email : " +nameFromIntent;
            textViewName.setText(x);
        }else{
            String email = PreferenceUtils.getEmail(this);
            String x = "Welcome, " +name+"\nYour Email : " +email;
            textViewName.setText(x);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.log_out:
                PreferenceUtils.savePassword(null, this);
                PreferenceUtils.saveEmail(null, this);
                PreferenceUtils.saveName(null, this);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void setBackground_for_activity(){

        LinearLayout nestedScrollView = findViewById(R.id.nestedScrollView);

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
