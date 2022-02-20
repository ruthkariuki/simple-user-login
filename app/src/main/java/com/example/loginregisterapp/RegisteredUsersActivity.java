package com.example.loginregisterapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisteredUsersActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisteredUsersActivity extends AppCompatActivity {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisteredUsersActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisteredUsers.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisteredUsersActivity newInstance(String param1, String param2) {
        RegisteredUsersActivity fragment = new RegisteredUsersActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registered_users);

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

        String[] usernames = new String[userList.size()];

        int i = 0;
        for (User user : userList) {
            usernames[i++] = user.getUsername();
        }

        ListView listView = findViewById(R.id.list);
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, usernames);
        listView.setAdapter(arr);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registered_users, container, false);
    }
}