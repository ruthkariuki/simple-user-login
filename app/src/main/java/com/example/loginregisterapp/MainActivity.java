package com.example.loginregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button registerButton;
    private Button loginButton;
    private Button registeredUsersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);
        registeredUsersButton = findViewById(R.id.registered_users_button);

        registeredUsersButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //link to the next activity
                Intent intent = new Intent(getApplicationContext(), RegisteredUsersActivity.class);
                //start the next activity
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameET = (EditText) findViewById(R.id.editLoginTextUsername);
                String username = usernameET.getText().toString();

                EditText passwordET = (EditText) findViewById(R.id.editLoginTextPassword);
                String password = passwordET.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("ke.co.ba", MODE_PRIVATE);

                String users = sharedPreferences.getString("users", "");

                Gson gson = new Gson();
                if (null == users || "".equals(users)) {
                    List<User> userList = new ArrayList<>();

                    users = gson.toJson(userList);
                }

                Type type = new TypeToken<List<User>>() {
                }.getType();
                List<User> userList = gson.fromJson(users, type);

                boolean authenticated = false;
                for (User user : userList) {
                    if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                        authenticated = true;
                        break;
                    }
                }

                if (!authenticated) {
                    // Display that the username and password is incorrect
                    Toast toast=Toast.makeText(getApplicationContext(),"Invalid username or password",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                    return;
                }

                //link to the next activity
                Intent intent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                intent.putExtra("username",username);
                //start the next activity
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("ke.co.ba", MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("message", "Hello olekevuato");
                editor.commit();

                //link to the next activity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                //start the next activity
                startActivity(intent);
            }
        });
    }
}