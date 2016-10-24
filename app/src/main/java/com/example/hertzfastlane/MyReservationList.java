package com.example.hertzfastlane;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyReservationList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation_list);
        String dateArray [] = {"Oct 12, 2016",
                                "Nov 15, 2016",
                                "Dec 8, 2016"};
        ListView dateView = (ListView) findViewById(R.id.reservationList);
        ArrayAdapter<String>  listViewAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,dateArray);
        dateView.setAdapter(listViewAdapter);

        dateView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                Intent reservationIntent = new Intent(MyReservationList.this, MyReservationActivity.class);
                MyReservationList.this.startActivity(reservationIntent);

            }
        });




    }

    private void Onclick(View v)
    {

    }

}
