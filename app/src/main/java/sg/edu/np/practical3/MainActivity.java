package sg.edu.np.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView title = findViewById(R.id.title);

        TextView desc = findViewById(R.id.homedesc);

        User user = userAdapter.getUser();
        Button followButton = findViewById(R.id.fbutton);

        if (user.Followed) {
            followButton.setText("UnFollow");
        } else {
            followButton.setText("Follow");
        }
        title.setText(user.Name);
        desc.setText(user.Description);

        DBHandler db = new DBHandler(this);

        followButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (followButton.getText() == "Follow") {
                    followButton.setText("UnFollow");
                    user.Followed = true;
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                    db.updateUser(user);
                } else {
                    followButton.setText("Follow");
                    user.Followed = false;
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                    db.updateUser(user);
                }
            }
        });

        Button messageButton = findViewById(R.id.mbutton);

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent groupChat = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(groupChat);
            }
        });

    }
}
//}
//        Button NGButton = findViewById(R.id.NGButton);
//
//        NGButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent numGenerator = new Intent(MainActivity.this, ListActivity.class );
//                numGenerator.putExtra("test", 100);
//                startActivityForResult(numGenerator, 1);
//            }
//        });


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        TextView title = findViewById(R.id.title);
//        if (requestCode == 1) {
//            if(resultCode == RESULT_OK) {
//                int ranInt = getIntent().getIntExtra("ranInt", 1);
//                if (ranInt != 1){
//                    title.setText(newUser.Name + " " + String.valueOf(ranInt));
//                }
//            }
//        }
//    }
//}