Tutorial How to learn Data Storage – SQLite Database – Lab08
Target:
- Understand basic Data Storage, Shared preferences.
References:
https://vncoder.vn/bai-viet/android-tim-hieu-ve-internal-storage-external-storage-va-scoped-storage-trong-android
https://viblo.asia/p/su-dung-sqlite-database-trong-ung-dung-android-wjAM7alevmWe
Data storage:
Storage options in Android:
+ Shared Preferences: Save basic data in key-value pairs (color, option, …).
+ Internal Storage: Store private data on device memory.
+ External Storage: Store public data on shared external storage.
+ Sqlite Database: Store structured data in a private database.
+ Network Connection: Store data on the web with a network server.
MODE - File open mode (open the file for what, grant permission):
MODE_PRIVATE: Create or replace an existing file with the same name and use this file only for
the program.
MODE_APPEND: Create a new file to write to or append to if the file exists and has data.
MODE_WORLD_READABLE: Allows other programs to read data from the file.
MODE_WORLD_WRITEABLE: Allows other programs to write data to the file.
Firstly, create a project and click Choose Empty Views Activity. After that give the name Project “Lab08” Choose language Java and click Finish.
 
 
Enter activity_main.xml on the toolbar/title after that click Code right side.
Next is update code:
Script:
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
xmlns:android="http://schemas.android.com/apk/res/android"
 xmlns:app="http://schemas.android.com/apk/res-auto"
 xmlns:tools="http://schemas.android.com/tools"
 android:layout_width="match_parent"
 android:layout_height="match_parent"
 tools:context=".MainActivity">
 <TextView
 android:id="@+id/textView"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:layout_marginTop="15dp"
 android:text="@string/hello_world"
 app:layout_constraintStart_toStartOf="parent"
 app:layout_constraintTop_toTopOf="parent" />
 <EditText
 android:id="@+id/editTextTextMultiLine"
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:layout_marginTop="48dp"
 android:autofillHints=""
 android:ems="10"
 android:gravity="start|top"
 android:hint="@string/your_content"
 android:inputType="textMultiLine"
 android:lines="10"
 android:maxLines="150"
 android:minHeight="48dp"
 android:textColorHint="#757575"
 app:layout_constraintStart_toStartOf="@+id/textView"
 app:layout_constraintTop_toTopOf="@+id/textView" />
 <Button
 android:id="@+id/buttonRead"
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:layout_marginTop="20dp"
 android:text="@string/read"
 app:layout_constraintStart_toStartOf="parent"
 app:layout_constraintTop_toBottomOf="@+id/editTextTextMultiLine" />
 <Button
 android:id="@+id/buttonWrite"
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:layout_marginTop="15dp"
 android:text="@string/write"
 app:layout_constraintStart_toStartOf="parent"
 app:layout_constraintTop_toBottomOf="@+id/buttonRead" />
 <EditText
 android:id="@+id/editTextTextMultiLine2"
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:layout_marginBottom="20dp"
 android:autofillHints=""
 android:ems="10"
 android:gravity="start|top"
 android:hint="@string/result"
 android:inputType="textMultiLine"
 android:lines="3"
 app:layout_constraintBottom_toBottomOf="parent"
 app:layout_constraintStart_toStartOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>






Next, click on Project Manager Choose folder app/src/main/res/values/strings.xml
Script:
<resources>
 <string name="app_name">LabEight</string>
 <string name="hello_world">Hello World! LabEight</string>
 <string name="your_content">Type your content here</string>
 <string name="read">Read</string>
 <string name="write">Write</string>
 <string name="result">result here</string>
</resources>

In MainActivity.java, please update the code as follows:
Script:
package com.example.lab08tutorial;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
 Button bt_read,bt_write;
 EditText et_input;
 EditText et_result;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);
 et_input=(EditText)findViewById(R.id.editTextTextMultiLine);
 et_result=(EditText)findViewById(R.id.editTextTextMultiLine2);
 bt_read=(Button)findViewById(R.id.buttonRead);
 bt_write=(Button)findViewById(R.id.buttonWrite);
 bt_write.setOnClickListener(view -> {
 String noteInput=et_input.getText().toString();
 SharedPreferences sharedPrefWrite= getSharedPreferences("MyPreferences",
MODE_PRIVATE);
 SharedPreferences.Editor editor=sharedPrefWrite.edit();
 editor.putString("note", noteInput);
 editor.apply(); //Update data don't return anything
 //editor.commit(); //Update data return:True/False
 });
 bt_read.setOnClickListener(view -> {
 SharedPreferences sharedPref= getSharedPreferences("MyPreferences",
MODE_PRIVATE);
 String noteContent=sharedPref.getString("note", "Your note");
 et_result.setText(noteContent);
 });
 }

Note that package com.example.lab08tutorial; is your name project. Right now your let Run Project will see the template as follows:

 

Please operate as shown in the picture
 
Afterward, in the right corner click Device Manger choose folder data/data/ com.example.lab08tutorial

If the operation completed not found shared_prefs then restart the project or Crl F5
 
 


a. Write a program to create a File and write it to Internal Storage. Check File in “File Explorer”.
Write your Note into YourNote.txt file.
Tips:
+ Using FileOutputStream with MODE_PRIVATE
+ Using write() close() to store data and close stream.
+ Check file in “File Explorer” with your package name.

FileOutputStream fOut = openFileOutput("YourNote.txt", Context.MODE_PRIVATE);

fOut.write("This is my note!".getBytes());

fOut.close();

b. Write a program to create a File and Read/write it to External Storage (SD Card). Check File in
“File Explorer”.
Write your Note into YourNote.txt file if your SDCard available
Tips:
+ Create function that check your SDCard status. Using
Environment.getExternalStorageState()
+ Check permission. Register in AndroidManifest file:
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
+ Using FileOutputStream. Using read() write() close() to working with data and close
stream.
+ Get file directory:
Environment.getExternalStorageDirectory()
+ Check file in “File Explorer” with your package name.
+ 
Script:
String state = Environment.getExternalStorageState();
if (Environment.MEDIA_MOUNTED.equals(state)) {


  FileOutputStream fOut = new FileOutputStream(Environment.getExternalStorageDirectory() + "/YourNote.txt");

  fOut.write("This is my note!".getBytes());

  fOut.close();
}

Update AndroidManifest.xml

 

 

Script:
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


After, fix add import class all 
 

The ones that will be added are listed as follows:

import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

In this part, it will error because REQUEST_CODE_WRITE_EXTERNAL_STORAGE has not been defined.
 

To do fix it, do the following:
 

private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1;

Đoạn mã bạn đã cung cấp là một phần của một ứng dụng Android để ghi dữ liệu vào bộ nhớ ngoài khi được cấp quyền.
Hàm isExternalStorageWritable() kiểm tra xem bộ nhớ ngoài có khả ghi hay không, tương tự như đã giải thích ở trên.
Hàm writeToFileOnExternalStorage(String note) được sử dụng để ghi dữ liệu vào bộ nhớ ngoài. Đầu tiên, nó lấy đường dẫn của thư mục bộ nhớ ngoài thông qua Environment.getExternalStorageDirectory(). Sau đó, nó tạo một đối tượng File với tên tệp là "YourNote.txt" trong thư mục đó. Tiếp theo, nó sử dụng FileOutputStream để ghi dữ liệu vào tệp. Trong trường hợp này, nội dung được ghi là chuỗi note được chuyển đổi thành mảng byte thông qua getBytes(). Cuối cùng, fileOutputStream.close() được gọi để đóng luồng ghi.
Phương thức onRequestPermissionsResult() được gọi khi người dùng đáp ứng yêu cầu cấp quyền. Trong trường hợp này, nếu yêu cầu có mã REQUEST_CODE_WRITE_EXTERNAL_STORAGE và quyền được cấp (PackageManager.PERMISSION_GRANTED), thì hàm writeToFileOnExternalStorage() sẽ được gọi để ghi dữ liệu vào bộ nhớ ngoài.
Đoạn mã này giúp bạn kiểm tra tính khả ghi của bộ nhớ ngoài, yêu cầu quyền ghi từ người dùng nếu cần, và ghi dữ liệu vào bộ nhớ ngoài khi quyền được cấp.


Next, you will have to add the following line of code to perform part b. The lines in red are libraries that have not been added yet, so import the class using Show Content Action

 

Script:
String note = "This is my note.";

  if (isExternalStorageWritable()) {
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
          writeToFileOnExternalStorage(note);
      } else {
          ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
      }
  }

Import class

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

To fix this, you must import AndroidManifest.xml

 

import android.Manifest;

Đoạn mã trên là một đoạn mã Java trong Android để kiểm tra tính khả ghi của bộ nhớ ngoài và ghi dữ liệu vào bộ nhớ ngoài nếu có quyền truy cập.

Đầu tiên, điều kiện if (isExternalStorageWritable()) được sử dụng để kiểm tra xem bộ nhớ ngoài có khả ghi hay không. Hàm isExternalStorageWritable() có thể được triển khai như sau:

public boolean isExternalStorageWritable() {
    String state = Environment.getExternalStorageState();
    return Environment.MEDIA_MOUNTED.equals(state);
}

Hàm isExternalStorageWritable() kiểm tra trạng thái của bộ nhớ ngoài thông qua Environment.getExternalStorageState(). Nếu trạng thái là Environment.MEDIA_MOUNTED, tức là bộ nhớ ngoài có thể ghi.

Tiếp theo, trong điều kiện if, kiểm tra xem ứng dụng đã được cấp quyền ghi vào bộ nhớ ngoài chưa bằng cách sử dụng ContextCompat.checkSelfPermission(). Nếu quyền đã được cấp (PackageManager.PERMISSION_GRANTED), hàm writeToFileOnExternalStorage(note) sẽ được gọi để ghi dữ liệu vào bộ nhớ ngoài.

Nếu quyền chưa được cấp, ActivityCompat.requestPermissions() được sử dụng để yêu cầu cấp quyền ghi vào bộ nhớ ngoài từ người dùng. Yêu cầu này sẽ hiển thị một hộp thoại yêu cầu quyền và người dùng có thể chấp nhận hoặc từ chối.

Cần lưu ý rằng để sử dụng quyền Manifest.permission.WRITE_EXTERNAL_STORAGE, bạn cần thêm quyền này vào tệp AndroidManifest.xml của ứng dụng:

<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

Điều này cho phép ứng dụng của bạn yêu cầu quyền ghi vào bộ nhớ ngoài từ người dùng.

Now, We upgrade your current project with SQLite Databases

- Extends SQLiteOpenHelper to create/update tables. Pass Database name and version into super()
function.
- We need to override onCreate() and onUpgrade(). onCreate() to be called if database does not
exist. Using onUpgrade() if database version changes.
- Access the database in read or write mode by using getReadableDatabase() and
getWriteableDatabase()

Update AndroidMainfest.xml

<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

 
Script:
android:requestLegacyExternalStorage="true"


Update activity_main.xml layout code

 
Script:
<Button
 android:id="@+id/buttonSaveDB"
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:layout_marginTop="15dp"
 android:text="@string/button_savedb"
 app:layout_constraintStart_toStartOf="parent"
 app:layout_constraintTop_toBottomOf="@+id/buttonWrite" />

When you see red text that means the string is missing, add the following line to fix it

 
Script:
<string name="button_savedb">Save database</string>


Next, Find the following path /app/src/main/java/com.example.lab08tutorial and create a file named “LabDatabase”

 

 

Then you add the following code to create Sql
Script:
package com.example.lab08tutorial;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class LabDatabase extends SQLiteOpenHelper {
    //Define Database
    private static final String DB_NAME="labdb.db";
    private static final int DB_VERSION=1;
    private static final String Table_NAME="notes";
    private static final String ClM_ID="_id";
    private static final String CLM_CONTENT="content";
    public LabDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
 /*create table user
 ( _id integer primary key autoincrement,
 content text
 ) */
        String sql="create table " + Table_NAME +
                "(" +
                ClM_ID +" integer primary key autoincrement," +
                CLM_CONTENT+" text" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int
            newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + Table_NAME);
        onCreate(sqLiteDatabase);
    }
    public void createNote(String content)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues myData=new ContentValues();
        myData.put(CLM_CONTENT,content);
        String nullcolumnhack=null;
        sqLiteDatabase.insert(Table_NAME, nullcolumnhack, myData);
        sqLiteDatabase.close();
    }
    public Cursor getAllNotes()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from " + Table_NAME,null);
        return c;
    }
    public int countRecord()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select count(*) from " + Table_NAME,null);
        c.moveToFirst();
        int x;
        do
        {
            x=Integer.parseInt(c.getString(0));
        }while(c.moveToNext());
        return x;
    }
}

Next, go to MainActivity.java to add the following lines in place
 
Script:
Button bt_read, bt_write, bt_saveDB;

 
Script:
bt_saveDB = findViewById(R.id.buttonSaveDB);

 

When you see red text that means the string is missing, add the following line to fix it
Script:
import android.database.Cursor;
Script:
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

After doing everything, restart the project and you will see the following result (note: turn off the device)

 
 

 
 	
