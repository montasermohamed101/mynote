package com.company.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    EditText edit1;
   TextView edit2;
    Button button;

    NoteDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        button=findViewById(R.id.btn);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        edit2.setText(currentDate);

        db = new NoteDataBase(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Note note = new Note(edit1.getText().toString());
                    db.addNote(note);
                    finish();

            }
        });

    }
}