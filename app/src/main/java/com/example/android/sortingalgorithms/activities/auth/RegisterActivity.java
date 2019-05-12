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
import com.example.android.sortingalgorithms.models.User;
import com.example.android.sortingalgorithms.network.UsersApi;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText txtUserName;
    EditText txtEmail;
    EditText txtPassword;
    Button registerBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //layout views
        txtUserName = findViewById(R.id.txtUserName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        registerBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.progressBar);
    }

    public void ventanaLogin(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void register(View view){
        createNewAccount();
    }

    public void createNewAccount(){
        String username = txtUserName.getText().toString();
        final String email = txtEmail.getText().toString();
        final String password = txtPassword.getText().toString();

        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            progressBar.setVisibility(View.VISIBLE);
            registerBtn.setVisibility(View.GONE);

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", email);
                jsonObject.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("JASON", jsonObject.toString());

            AndroidNetworking.post(UsersApi.usersUrl()+"signup")
                    .addJSONObjectBody(jsonObject)
                    .build()
                    .getAsOkHttpResponse(new OkHttpResponseListener() {
                        @Override
                        public void onResponse(Response response) {
                            if (response.isSuccessful()) {
                                Log.i("RESPONSE", response.message());

                                User user = new User(email, password);
                                user.save();
                                Long id = user.getId();
                                user = User.findById(
                                        User.class, id);
                                Log.d("USUARIO",
                                        "User id: " + user.getId().toString() +
                                                ", email: " + user.getEmail() +
                                                ", password: " + user.getPassword());
                                progressBar.setVisibility(View.GONE);
                                registerBtn.setVisibility(View.VISIBLE);
                                action();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.d("ERROR", anError.getMessage());
                            Toast.makeText(getApplicationContext(),"Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            registerBtn.setVisibility(View.VISIBLE);
                        }
                    });
        }
    }

    public void action(){
        startActivity(new Intent(this, LoginActivity.class));
    }

}
