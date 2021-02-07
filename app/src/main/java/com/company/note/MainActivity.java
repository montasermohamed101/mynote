package com.company.note;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnNoteListner  {

    RecyclerView recyclerView;
    ArrayList<Note> notes;
    FloatingActionButton button;
    NoteDataBase db;

    //
    EditText editText1;
    TextView editText2;
    int id;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.floating_action_button);

        notes=new ArrayList<>();
        recyclerView=findViewById(R.id.list);
        //
        editText1=findViewById(R.id.edit1);
        editText2=findViewById(R.id.edit2);


        //
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        db=new NoteDataBase(this);


        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);

            }

        });
        */
        Note note = new Note();
        Intent intent = new Intent();
        intent.putExtra("note",note.getNoteText());
        intent.putExtra("date",note.getNoteDate());
        intent.putExtra("id",note.getId());

        notes =db.getNote();
        NoteAdapter adapter = new NoteAdapter(MainActivity.this,notes,MainActivity.this);
        recyclerView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View v = getLayoutInflater().inflate(R.layout.activity_add, (ViewGroup) findViewById(R.id.add));
                final EditText editText = v.findViewById(R.id.edit1);

                builder.setView(v)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Note note = new Note(id,editText.getText().toString());
                                db.addNote(note);
                                notes =db.getNote();
                                NoteAdapter adapter = new NoteAdapter(MainActivity.this,notes,MainActivity.this);
                                recyclerView.setAdapter(adapter);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
   
    @Override
    public void onNoteClick(int position, final Note note) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.activity_add, (ViewGroup) findViewById(R.id.add));
        final EditText editText = v.findViewById(R.id.edit1);

        editText.setText(note.getNoteText());
        builder.setView(v)
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Note note1=new Note(note.getId(),editText.getText().toString().trim());
                        db.updateNote(note1);
                        notes =db.getNote();
                        NoteAdapter adapter = new NoteAdapter(MainActivity.this,notes,MainActivity.this);
                        recyclerView.setAdapter(adapter);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();




    }

    public void onDeleteClicked(final int id){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.activity_add, (ViewGroup) findViewById(R.id.add));
        final EditText editText = v.findViewById(R.id.edit1);

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
                        notes =db.getNote();
                        NoteAdapter adapter = new NoteAdapter(MainActivity.this,notes,MainActivity.this);
                        recyclerView.setAdapter(adapter);

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    protected void onResume() {
        notes =db.getNote();
        NoteAdapter adapter = new NoteAdapter(this,notes,this);
        recyclerView.setAdapter(adapter);
        super.onResume();
    }


}


/*
 Note note = notes.get(position);
        Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
        intent.putExtra("note",note.getNoteText());
        intent.putExtra("date",note.getNoteDate());
        intent.putExtra("id",note.getId());
        startActivity(intent);
 */





/*
public class MainActivity extends AppCompatActivity implements NoteAdapter.OnNoteListner  {

    RecyclerView recyclerView;
    ArrayList<Note> notes;
    FloatingActionButton button;
    NoteDataBase db;

    //
    EditText editText1;
    TextView editText2;
    int id;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.floating_action_button);

        notes=new ArrayList<>();

        recyclerView=findViewById(R.id.list);



        //
        editText1=findViewById(R.id.edit1);
       editText2=findViewById(R.id.edit2);


        //


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        db=new NoteDataBase(this);


        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);

            }

        });


        notes =db.getNote();
                NoteAdapter adapter = new NoteAdapter(MainActivity.this,notes,MainActivity.this);
                recyclerView.setAdapter(adapter);
                button.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.activity_add, (ViewGroup) findViewById(R.id.add));
final EditText editText = v.findViewById(R.id.edit1);

        builder.setView(v)
        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialogInterface, int i) {
        Note note = new Note(id,editText.getText().toString());
        db.addNote(note);

        notes =db.getNote();
        NoteAdapter adapter = new NoteAdapter(MainActivity.this,notes,MainActivity.this);
        recyclerView.setAdapter(adapter);

        }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialogInterface, int i) {

        }
        });
        AlertDialog dialog = builder.create();
        dialog.show();




        }
        });


        }



@Override
protected void onResume() {

        notes =db.getNote();
        NoteAdapter adapter = new NoteAdapter(this,notes,this);
        recyclerView.setAdapter(adapter);
        super.onResume();


        }


@Override
public void onNoteClick(int position) {
        //notes.get(position);
        Note note = notes.get(position);
        Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
        intent.putExtra("note",note.getNoteText());
        intent.putExtra("date",note.getNoteDate());
        intent.putExtra("id",note.getId());
        startActivity(intent);

        }

        }

 */


/*
   //notes.get(position);



        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.activity_update, (ViewGroup) findViewById(R.id.update));
        final EditText editText = v.findViewById(R.id.edit1);
        builder.setView(v)

                .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db=new NoteDataBase(MainActivity.this);
                        Note note=new Note(id);
                        db.updateNote(note);





                   //   onCreateOptionsMenu(R.menu.menu_options,menu);



                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
 */