package com.example.loginregisterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register extends AppCompatActivity {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment login.
     */
    // TODO: Rename and change types and number of parameters
    public static Register newInstance(String param1, String param2) {
        Register fragment = new Register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        Button registerButton = (Button) findViewById(R.id.register_btn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText firstNameET = (EditText) findViewById(R.id.editTextTextFirstName);
                String firstname = firstNameET.getText().toString();

                EditText lastNameET = (EditText) findViewById(R.id.editTextTextLastName);
                String lastname = lastNameET.getText().toString();

                EditText idNumberET = (EditText) findViewById(R.id.editTextTextIDNumber);
                String idNumber = idNumberET.getText().toString();

                EditText usernameET = (EditText) findViewById(R.id.editTextTextUsername);
                String username = usernameET.getText().toString();

                EditText passwordET = (EditText) findViewById(R.id.editTextTextPassword);
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

                userList.add(new User(username, password, firstname, lastname, idNumber));

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("users", gson.toJson(userList));
                editor.commit();

                System.out.println("*************************************");
                System.out.println(sharedPreferences.getString("users", ""));
                System.out.println("*************************************");

                //link to the next activity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //start the next activity
                startActivity(intent);
            }
        });
    }

//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}