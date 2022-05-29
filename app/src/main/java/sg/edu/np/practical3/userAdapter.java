package sg.edu.np.practical3;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userViewHolder> {

    public static User selectedUser;
    public Context c;
    ArrayList<User> data;
    public userAdapter(Context c, ArrayList<User> data){
        this.c = c;
        this.data = data;
    }

    public int getItemViewType(int position){
        selectedUser = data.get(position);
        if (selectedUser.Name.substring(selectedUser.Name.length() - 1).equals("7")){
            return 1;
        }
        return 0;
    }
    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, null, false);

        if (viewType == 1){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.large_pp, parent, false);

        }
        return new userViewHolder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        User selectedUser = data.get(position);
        userAdapter.selectedUser = selectedUser;
        Log.d("position", String.valueOf(position));
        holder.name.setText(selectedUser.Name);
        holder.desc.setText(selectedUser.Description);
        holder.profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);

                builder.setTitle("Profile");
                builder.setMessage(selectedUser.Name);
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(c, MainActivity.class);
                        intent.putExtra("user", selectedUser);
                        c.startActivity(intent);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static User getUser(){
        return selectedUser;
    }
}
