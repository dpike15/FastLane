//package com.example.hertzfastlane;
//
//import com.android.volley.Response;
//import com.android.volley.toolbox.StringRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by rtrev on 9/10/2016.
// */
//public class RegisterRequest  extends StringRequest {
//
//    private static final String REGISTER_REQUEST_URL = "http://testapp.comuf.com/Register.php";
//    private HashMap<String, String> params;
//
//    public RegisterRequest(String name, String username, String password, int age, Response.Listener<String> listener) {
//        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
//        params = new HashMap<>();
//        params.put("name", name);
//        params.put("username", username);
//        params.put("password", password);
//        params.put("age", age + "");
//    }
//
//    @Override
//    public Map<String, String> getParams() {
//        return params;
//    }
//
//}