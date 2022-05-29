package sg.edu.np.practical3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBHandler db = new DBHandler(this);
        for (int i = 0; i < 20; i++){
            User newUser = new User();
            int randomInt = new Random().nextInt((999999999 + 999999999) + 1) - 999999999;
            int randomInt2 = new Random().nextInt((999999999 + 999999999) + 1) - 999999999;
            int randomInt3 = new Random().nextInt((1) + 1);
            newUser.Name = "Name" + randomInt;
            newUser.ID = i;
            newUser.Description = "Description " + randomInt2;
            newUser.Followed = randomInt3 == 1;
            db.insertUser(newUser);
        }

        RecyclerView rv = findViewById(R.id.recyclerView);
        userAdapter adapter = new userAdapter(ListActivity.this, db.getUser());
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);

        rv.setAdapter(adapter);
        rv.setLayoutManager(layout);


    }

}