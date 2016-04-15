package com.example.administrator.chatandroid.LocalStorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.chatandroid.Model.Message;

import java.util.ArrayList;

/**
 * Created by Administrator on 4/12/2016.
 */

public class MessageStorage extends SQLiteOpenHelper {
    public static final String dbName = "Chat.db";
    public static final String messagesTable = "Messages_table";
    public static final String idCol = "mId";
    public static final String fromCol = "mFrom";
    public static final String toCol = "mTo";
    public static final String textCol = "mText";
    public static final String dateCol = "mDate";

    public MessageStorage(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + messagesTable + " ("+idCol+" INTEGER PRIMARY KEY autoincrement,"+fromCol+" integer,"+toCol+" integer,"+textCol+" text,"+dateCol+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+messagesTable);
        onCreate(db);
    }

    public boolean insertMessage(int from, int to, String message,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newRow = new ContentValues();
        newRow.put(fromCol,from);
        newRow.put(toCol, to);
        newRow.put(textCol, message);
        newRow.put(dateCol,date);
        if (db.insert(messagesTable,null,newRow) == -1)
            return false;
        else
            return true ;
    }

    public ArrayList<Message> getUserMessages(int from, int to){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor Messages = db.rawQuery("select * from " + messagesTable + " where "+fromCol+"= ? and "+toCol+"= ?", new String[]{String.valueOf(from), String.valueOf(to)});
        ArrayList<Message> StoredMessages = new ArrayList<Message>();
        if(Messages.getCount() > 0){
            while (Messages.moveToNext()) {
                StoredMessages.add(new Message(Integer.valueOf(Messages.getString(0)), Integer.valueOf(Messages.getString(1)), Integer.valueOf(Messages.getString(2)), Messages.getString(3),Messages.getString(4)));
                Messages.moveToNext();
            }
        }
        return StoredMessages;
    }
}
