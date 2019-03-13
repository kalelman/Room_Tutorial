package com.kalelman.roomtutorial.view;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kalelman.roomtutorial.R;
import com.kalelman.roomtutorial.model.AppDatabase;
import com.kalelman.roomtutorial.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private UsersAdapter usersAdapter;
    //ArrayList<User> users;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /*users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            //users.add("User # " + i);
            User user = new User("Erick # " + i, "Rojas Perez", "erick_rojas_perez@hotmail.com");
            users.add(user);
        }*/

        final AppDatabase db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class,"production")
                .allowMainThreadQueries()
                .build();

        List<User> users = db.userDao().getAllUsers();

        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersAdapter = new UsersAdapter(users);
        myRecyclerView.setAdapter(usersAdapter);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Log.i(TAG, "FAB PRESSED");
        startActivity(new Intent(MainActivity.this, CreateUser.class));

    }
}
