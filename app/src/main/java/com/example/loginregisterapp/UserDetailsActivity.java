package com.example.loginregisterapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserDetailsActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDetailsActivity extends AppCompatActivity {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserDetailsActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static UserDetailsActivity newInstance(String param1, String param2) {
        UserDetailsActivity fragment = new UserDetailsActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_details);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");

        SharedPreferences sharedPreferences = getSharedPreferences("ke.co.ba", MODE_PRIVATE);

        String users = sharedPreferences.getString("users", "");

        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> userList = gson.fromJson(users, type);

        User loggedInUser = null;
        for(User user: userList) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                loggedInUser = user;
                break;
            }
        }

        TextView firstNameTV = (TextView) findViewById(R.id.firstNameTxt);
        firstNameTV.setText(loggedInUser.getFirstname());

        TextView lastNameTV = (TextView) findViewById(R.id.lastNameTxt);
        lastNameTV.setText(loggedInUser.getLastname());

        TextView idNumberTV = (TextView) findViewById(R.id.idNumberTxt);
        idNumberTV.setText(loggedInUser.getIdnumber());

        TextView usernameTV = (TextView) findViewById(R.id.usernameTxt);
        usernameTV.setText(loggedInUser.getUsername());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }
}