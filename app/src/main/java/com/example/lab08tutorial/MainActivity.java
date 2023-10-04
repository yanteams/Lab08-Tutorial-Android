package com.example.lab08tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1;

    Button bt_read, bt_write;
    EditText et_input;

    EditText et_result;
    Button bt_saveDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_input = findViewById(R.id.editTextTextMultiLine);
        et_result = findViewById(R.id.editTextTextMultiLine2);
        bt_read = findViewById(R.id.buttonRead);
        bt_write = findViewById(R.id.buttonWrite);

        bt_write.setOnClickListener(view -> {
            String noteInput = et_input.getText().toString();
            SharedPreferences sharedPrefWrite = getSharedPreferences("MyPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefWrite.edit();
            editor.putString("note", noteInput);
            editor.apply(); //Update data don't return anything
            //editor.commit(); //Update data return:True/False

            // Write to internal storage
            File file = new File(getFilesDir(), "YourNote.txt");
            FileOutputStream fOut = null;
            try {
                fOut = new FileOutputStream(file);
                fOut.write(noteInput.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fOut != null) {
                    try {
                        fOut.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Write to external storage if SD card is available
            if (isExternalStorageWritable()) {
                File directory = Environment.getExternalStorageDirectory();
                File externalFile = new File(directory, "YourNote.txt");

                fOut = null;
                try {
                    fOut = new FileOutputStream(externalFile);
                    fOut.write(noteInput.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fOut != null) {
                        try {
                            fOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        bt_read.setOnClickListener(view -> {
            SharedPreferences sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
            String noteContent = sharedPref.getString("note", "Your note");
            et_result.setText(noteContent);
        });

        bt_saveDB = findViewById(R.id.buttonSaveDB); // Move the initialization here



        bt_saveDB.setOnClickListener(view -> {
            String noteInput = et_input.getText().toString();
            LabDatabase db = new LabDatabase(this);
            if (db.countRecord() == 0) {
                db.createNote("First note");
                db.createNote("Second note");
                db.createNote("Third note");
            } else {
                db.createNote(noteInput); // save your note
            }
            // After saving the note into the DB, show them in Result
            Cursor c = db.getAllNotes();
            c.moveToFirst();
            StringBuilder noteContent = new StringBuilder();
            do {
                noteContent.append(c.getString(0)).append(" ");
                noteContent.append(c.getString(1)).append("\n");
            } while (c.moveToNext());
            et_result.setText(noteContent.toString());
            db.close();
            c.close();
        });
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private void writeToFileOnExternalStorage(String note) {
        File directory = Environment.getExternalStorageDirectory();
        File file = new File(directory, "YourNote.txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(note.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                writeToFileOnExternalStorage("This is my note.");
            }
        }
    }
}
