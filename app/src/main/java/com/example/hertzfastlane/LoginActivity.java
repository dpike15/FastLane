package com.example.hertzfastlane;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    String TAG = "LoginActivity";
    String username;
    String password;
    boolean exist;
    private DatabaseReference mDatabase;
    private DynamoDBMapper mapperMembers;

    public static users getMember() {
        return member;
    }

    public void setMember(users member) {
        this.member = member;
    }

    private static users member;
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


                //Amazon Web Services Connection
                //Credentials for identity pools for Table users - AWS
                // Initialize the Amazon Cognito credentials provider for users Table
              // Initialize the Amazon Cognito credentials provider
                CognitoCachingCredentialsProvider credentialsProviderMembers = new CognitoCachingCredentialsProvider(
                     getApplicationContext(),
                        "us-east-1:f9decd8d-cbed-4d6f-a8c9-b4bd1ce2d35c", // Identity Pool ID
                     Regions.US_EAST_1 // Region
                );
                AmazonDynamoDBClient ddbClientMembers = new AmazonDynamoDBClient(credentialsProviderMembers);
                mapperMembers = new DynamoDBMapper(ddbClientMembers);


                //Getting login information for a user and chacking validity
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if(username.equals("") || password.equals("")) {
                            login = false;
                        }else{
                            member = mapperMembers.load(users.class, username);
                            if(member == null){
                                login = false;
                            }else{
                                if(password.equals(member.getPassword())){
                                    login = true;
                                }else{
                                    login = false;

                                }
                            }
                        }

                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();

                //Wait for thread to finish
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