package sg.edu.np.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginPage extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("mad");
        TextView username = findViewById(R.id.usernameField);
        TextView password = findViewById(R.id.passField);

        Button login = findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                TextView errorBox = findViewById(R.id.errorMsg);
                if(TextUtils.isEmpty(username.getText().toString())){
                    errorBox.setText("Enter your username");
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    errorBox.setText("Enter your password");
                    return;
                }
                if(password.length() < 3){
                    errorBox.setText("Password must be > 3 characters");
                }
                Log.d("Test", "Pass");
                // Read from the database
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                            Log.d("keyValue", keyNode.getValue().toString());
                            if(username.getText().toString().equals(keyNode.getValue())){
                                Intent peoplePage = new Intent(LoginPage.this, ListActivity.class);
                                startActivity(peoplePage);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("ValueFail", "Failed to read value.", error.toException());
                    }
                });
            }

        });




    }
}