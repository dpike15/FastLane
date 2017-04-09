//package com.example.hertzfastlane;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import static com.android.volley.toolbox.Volley.newRequestQueue;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register); // tells RegisterActivity which xml file to use
//
//        final EditText etAge = (EditText) findViewById(R.id.etAge);
//        final EditText etName = (EditText) findViewById(R.id.etName);
//        final EditText etUserName = (EditText) findViewById(R.id.etUserName);
//        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
//
//        final Button bRegister = (Button) findViewById(R.id.bSignUp);
//
//        assert bRegister != null;
//        bRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String name = etName.getText().toString();
//                final String username = etUserName.getText().toString();
//                final String password = etPassword.getText().toString();
//                final int age = Integer.parseInt(etAge.getText().toString());
//
//                Response.Listener<String> responseListener = new Response.Listener<String>(){
//                    @Override
//                    public void onResponse(String response) {
//
//                        System.out.println("Respose: " + response);
//
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success");
//
//                            if(success){
//                                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                RegisterActivity.this.startActivity(loginIntent);
//                            }else {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//                                builder.setMessage("Register Failed")
//                                        .setNegativeButton("Retry",null)
//                                        .create()
//                                        .show();
//                            }
//                        } catch (JSONException e) {
//                            System.out.println("Error Here");
//                            e.printStackTrace();
//                        }
//                    }
//                };
//
//                RegisterRequest registerRequest = new RegisterRequest(name, username, password, age, responseListener);
//                RequestQueue queue = newRequestQueue(RegisterActivity.this);
//                queue.add(registerRequest);
//            }
//        });
//
//    }
//}
