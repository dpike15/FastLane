package com.example.hertzfastlane;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.google.firebase.database.DatabaseReference;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    String TAG = "LoginActivity";
    String username;
    String password;

    public static Member getMember() {
        return user;
    }

    public void setMember(Member member) {
        this.user = member;
    }

    private static Member user;
    boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                 login = false;

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //Running CloudantDB instead of AWS
                        CloudantClient client;
                        try {
                            client = ClientBuilder.url(new URL("https://6a4c10ac-077f-4d8c-9ca3-53f0e84f3d5a-bluemix:e93cc83b1d85bd5c712886ba101bd9531ca36d464bc40d60f982ec46b3db8f5f@6a4c10ac-077f-4d8c-9ca3-53f0e84f3d5a-bluemix.cloudant.com"))
                                    .username("6a4c10ac-077f-4d8c-9ca3-53f0e84f3d5a-bluemix")
                                    .password("e93cc83b1d85bd5c712886ba101bd9531ca36d464bc40d60f982ec46b3db8f5f")
                                    .build();

                            //Accessing Cars Database
                            Database db = client.database("customers", false);

                            //Selecting Document using a JSON selector : VIN number
                            String selector = "\"selector\": {\"username\": \"" + username +"\"}";

                            List<Member> memberLookup = db.findByIndex(selector, Member.class);

                            user = memberLookup.get(0);

                            if(password.equals(user.getPassword())){
                                login = true;
                            }

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                };
        Thread thread = new Thread(runnable);
        thread.start();

        try{
            thread.join();
        }catch(Exception e){
            return;
        }

                //If login success proceed to application
                if(login){
                    Intent userActivityIntent = new Intent(LoginActivity.this, UserActivity.class);
                    LoginActivity.this.startActivity(userActivityIntent);
                }else{
                    builder.setMessage("Login Unsuccessful!");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });


    }

}