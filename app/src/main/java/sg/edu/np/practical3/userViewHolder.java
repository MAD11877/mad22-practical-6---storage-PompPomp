package sg.edu.np.practical3;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class userViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView desc;
    ImageView profilepic;
    View viewItem;

    public userViewHolder(View item){
        super(item);
        name = item.findViewById(R.id.name);
        desc = item.findViewById(R.id.desc);
        profilepic = item.findViewById(R.id.profilePic);
        viewItem = item;
    }

}
