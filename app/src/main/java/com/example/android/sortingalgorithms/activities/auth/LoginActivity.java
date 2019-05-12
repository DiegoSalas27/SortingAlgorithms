package com.example.android.sortingalgorithms.activities.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.example.android.sortingalgorithms.R;
import com.example.android.sortingalgorithms.activities.MainActivity;
import com.example.android.sortingalgorithms.network.UsersApi;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    EditText txtUserName;
    EditText txtPassword;
    ProgressBar progressBar;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        progressBar = findViewById(R.id.progressBar);
        loginBtn = findViewById(R.id.login);

    }

    public void ventanaRegister(View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void login(View view){
        loginUser();
    }

    public void loginUser(){
        final String email = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            progressBar.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", email);
                jsonObject.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("JASON", jsonObject.toString());

            AndroidNetworking.post(UsersApi.usersUrl()+"login")
                    .addJSONObjectBody(jsonObject)
                    .build()
                    .getAsOkHttpResponse(new OkHttpResponseListener() {
                        @Override
                        public void onResponse(Response response) {
                            if (response.isSuccessful()) {
                                Log.i("RESPONSE", response.message());

                                //insertarmos el usuario acutal a las preferencias
                                SharedPreferences preferencias =
                                        getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = preferencias.edit();
                                editor.putString("email", email);
                                editor.commit();

                                progressBar.setVisibility(View.GONE);
                                loginBtn.setVisibility(View.VISIBLE);
                                action();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.d("ERROR", anError.getMessage());
                            Toast.makeText(getApplicationContext(),"Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            loginBtn.setVisibility(View.VISIBLE);
                        }
                    });
        }
    }

    public void action(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
