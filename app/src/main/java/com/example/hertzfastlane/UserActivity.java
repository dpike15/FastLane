package com.example.hertzfastlane;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;

import java.lang.reflect.Field;

public class UserActivity extends AppCompatActivity {
    //public Button button;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final Button bMyReservation = (Button) findViewById(R.id.bMyReservation);
        final Button bScanner = (Button) findViewById(R.id.bScanner);
        final Button bMap = (Button) findViewById(R.id.bMap);
        final Button bHelp = (Button) findViewById(R.id.bHelp);


        //final String
        //Intent intent = getIntent();
        //String name = intent.getStringExtra("name");
        //etName.setText(name);

        new AlertDialog.Builder(context).setCancelable(false)
                .setTitle("Airport Location")
                .setMessage("Choose Your location")
                .setPositiveButton("Miami", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        bMap.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                       Intent mapIntent = new Intent(UserActivity.this, MapActivity.class);
                                      UserActivity.this.startActivity(mapIntent);
                                 }
                        });
                    }
                })
                .setNegativeButton("Tampa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        bMap.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent mapIntent = new Intent(UserActivity.this, MapActivity2.class);
                                UserActivity.this.startActivity(mapIntent);
                            }
                        });
                    }
                })
                .setIcon(android.R.drawable.alert_dark_frame).setInverseBackgroundForced(true)
                .show();



        bMyReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reservationIntent = new Intent(UserActivity.this, MyReservationActivity.class);
                UserActivity.this.startActivity(reservationIntent);
            }
        });

        bScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(UserActivity.this, QrScanner.class);
                UserActivity.this.startActivity(registerIntent);
            }
        });

      /*    bMap.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

               Intent mapIntent = new Intent(UserActivity.this, MapActivity.class);
                UserActivity.this.startActivity(mapIntent);
            }
        });
*/
        bHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(UserActivity.this, HelpActivity.class);
                UserActivity.this.startActivity(helpIntent);
            }
        });
    }
}