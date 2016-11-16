package com.example.hertzfastlane;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.hertzfastlane.R.id.b_Login;
import static com.example.hertzfastlane.R.id.et_Password;
import static com.example.hertzfastlane.R.id.et_Username;


public class LoginActivity extends AppCompatActivity {

    String TAG = "LoginActivity";
    String username;
    String password;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static Member getMember() {
        return user;
    }

    public void setMember(Member member) {
        this.user = member;
    }

    private static Member user;
    boolean login;
    private ProgressBar spinner;
    private static final String URL= "http://169.46.154.154:8080/members";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(et_Username);
        final EditText etPassword = (EditText) findViewById(et_Password);
        final Button bLogin = (Button) findViewById(b_Login);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        spinner =(ProgressBar)findViewById(R.id.progress_loader);
        spinner.setVisibility(View.GONE);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                login = false;

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        HttpClient httpclient = new DefaultHttpClient();


                        HttpGet request = new HttpGet(URL + "/" + username);

                        HttpResponse response;

                        try {
                            response = httpclient.execute(request);
                            HttpEntity entity = response.getEntity();
                            InputStream instream = entity.getContent();
                            String result = convertStreamToString(instream);
/*
                            Log.d("result",result);


                            HttpGet request1 = new HttpGet("http://169.46.154.154:8080/members" + "/" + result);

                            HttpResponse response1;

                            response1 = httpclient.execute(request1);
                            HttpEntity entity1 = response1.getEntity();
                            InputStream instream1 = entity1.getContent();
                            String result1 = convertStreamToString(instream1);

                            Log.d("result1", result1);
*/
                            ObjectMapper mapper = new ObjectMapper();

                            user = mapper.readValue(result, Member.class);

                            instream.close();

                           if(password.equals(user.getPassword())) {
                               login = true;

                           }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();

                try {
                    thread.join();
                } catch (Exception e) {
                    return;
                }

                //If login success proceed to application
                if (login) {
                    Intent userActivityIntent = new Intent(LoginActivity.this, UserActivity.class);
                    LoginActivity.this.startActivity(userActivityIntent);
                } else {
                    builder.setMessage("Login Unsuccessful!");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        spinner.setVisibility(View.GONE);
    }

    private static String convertStreamToString(InputStream is) {
	    /*
	     * To convert the InputStream to String we use the BufferedReader.readLine()
	     * method. We iterate until the BufferedReader return null which means
	     * there's no more data to read. Each line will appended to a StringBuilder
	     * and returned as String.
	     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}