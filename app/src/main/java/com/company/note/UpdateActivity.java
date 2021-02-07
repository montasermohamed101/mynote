package com.company.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {
    EditText editText1;
    TextView editText2;
    Button button;

    int id;
    NoteDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editText1=findViewById(R.id.edit1);
        editText2=findViewById(R.id.edit2);
        button=findViewById(R.id.btn);

        id=getIntent().getIntExtra("id",0);
        editText1.setText(getIntent().getStringExtra("note"));
        editText2.setText(getIntent().getStringExtra("date"));

        db=new NoteDataBase(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note=new Note(id,editText1.getText().toString().trim());
                db.updateNote(note);
                finish();
            }
        });


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                AlertDialog.Builder builder =new AlertDialog.Builder(UpdateActivity.this);
                builder.setTitle("Confirmation")
                        .setMessage("Are You Sure You Want To Delete This Note ? ")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.deleteNote(id);
                                finish();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();



        }
        return super.onOptionsItemSelected(item);
    }
}