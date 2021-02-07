package com.company.note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.text.DateFormat;
import java.util.Calendar;


public class Note {


    private String noteText;


    private int id;

    Calendar calendar =Calendar.getInstance();
    private String noteDate = DateFormat.getDateInstance().format(calendar.getTime());

    public Note(int id) {
        this.id = id;
    }

    public Note(int id, String noteText) {
        this.noteText = noteText;
        this.id = id;
    }

    public Note(String noteText) {
        this.noteText = noteText;
    }

    public Note() {
    }

    public Note(int id, String noteText, String noteDate) {
        this.noteText = noteText;
        this.id = id;
        this.noteDate = noteDate;
    }

    public Note(String noteText, String noteDate) {
        this.noteText = noteText;
        this.noteDate = noteDate;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
/*


 <androidx.recyclerview.widget.RecyclerView
        android:layout_width="match_parent"
        android:id="@+id/list"
        android:layout_height="match_parent"></androidx.recyclerview.widget.RecyclerView>





 <com.google.android.material.floatingactionbutton.FloatingActionButton
            android:id="@+id/floating_action_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom|right"
            android:layout_marginTop="650dp"
            android:layout_marginRight="20dp"

            ></com.google.android.material.floatingactionbutton.FloatingActionButton>
 */
