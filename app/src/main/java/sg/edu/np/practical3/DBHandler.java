package sg.edu.np.practical3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context c){
        super(c, "Practical5.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE User(Name TEXT, Description TEXT, ID TEXT, Followed TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Message");
        onCreate(db);

        switch(oldVersion){
            case 1:
                //add datetime
            case 2:
                //add reaction

        }
    }


    public ArrayList<User> getUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM User", null);
        while(cursor.moveToNext()){
            User m = new User();
            //int index = cursor.getColumnIndex("name");
            m.Name = cursor.getString(0);
            m.Description = cursor.getString(1);
            m.ID = cursor.getInt(2);
            m.Followed = cursor.getString(3).equals("true");

            list.add(m);
        }
        return list;
    }

    public void insertUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO User VALUES(\"" + u.Name + "\", \"" + u.Description + "\", \"" + u.ID + "\", \"" + u.Followed + "\")");
    }
    public void updateUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE User SET Followed = \"" + u.Followed + "\" WHERE ID LIKE \"" + u.ID + "\"");
        Log.d("Inserted", String.valueOf(u.Followed));
    }
//    public void updateUser(User u){
//        ContentValues values = new ContentValues();
//        values.put("Followed", u.Followed);
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.insert("User", null, values);
//        Log.d("Inserted", String.valueOf(u.Followed));
//        db.close();
//    }
}
