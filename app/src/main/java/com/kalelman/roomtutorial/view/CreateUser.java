package com.kalelman.roomtutorial.view;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.kalelman.roomtutorial.R;
import com.kalelman.roomtutorial.model.AppDatabase;
import com.kalelman.roomtutorial.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateUser extends AppCompatActivity {

    @BindView(R.id.tietFirstName)
    TextInputEditText tietFirstName;
    @BindView(R.id.tilFirstName)
    TextInputLayout tilFirstName;
    @BindView(R.id.tietLastName)
    TextInputEditText tietLastName;
    @BindView(R.id.tilLastName)
    TextInputLayout tilLastName;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.tietEmail)
    TextView tietEmail;
    @BindView(R.id.btnAddUser)
    Button btnAddUser;

    private final static String TAG = "CreateUser";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAddUser)
    public void onViewClicked() {
        final AppDatabase db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class,"production").allowMainThreadQueries()
                .build();
        // Save to DataBase
        User user = new User(tietFirstName.getText().toString(), tietLastName.getText().toString(), tietEmail.getText().toString());
        db.userDao().insertAll(user);
        startActivity(new Intent(CreateUser.this, MainActivity.class));
    }
}
