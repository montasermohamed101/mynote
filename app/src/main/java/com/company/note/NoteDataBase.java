package com.company.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NoteDataBase extends SQLiteOpenHelper {

    private static  final  String DB_NAME="MyDataBase";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME="notes";

    private static  final String KEY_ID = "id";
    private static  final String KEY_NAME = "name";
    private static  final String KEY_DATE = "date";


    public NoteDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String Create_QUERY="Create TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY ,"+KEY_NAME+" VARCHAR(50) ,"+KEY_DATE
                +" VARCHAR(50))";
        sqLiteDatabase.execSQL(Create_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addNote(Note note){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,note.getNoteText());
        contentValues.put(KEY_DATE,note.getNoteDate());
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public ArrayList<Note> getNote(){
        ArrayList<Note> notes = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM "+TABLE_NAME+"";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_QUERY,null);
        if(cursor.moveToFirst()){
            do {
                String noteText=cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String date=cursor.getString(cursor.getColumnIndex(KEY_DATE));
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                Note note = new Note(id,noteText,date);
                notes.add(note);

            }while (cursor.moveToNext());
        }
        return notes;
    }

    public void updateNote(Note note){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,note.getNoteText());
        contentValues.put(KEY_DATE,note.getNoteDate());

        sqLiteDatabase.update(TABLE_NAME,contentValues,"id=?",new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
    }


}

