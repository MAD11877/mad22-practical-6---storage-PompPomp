package sg.edu.np.practical3;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String Name;
    public String Description;
    public int ID;
    public boolean Followed;

    public User(String n, String d, int id, boolean f){
        Name = n;
        Description = d;
        ID = id;
        Followed = f;
    }
    public User(){}

    protected User(Parcel in) {
        Name = in.readString();
        Description = in.readString();
        ID = in.readInt();
        Followed = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Description);
        parcel.writeInt(ID);
        parcel.writeByte((byte) (Followed ? 1 : 0));
    }
}
