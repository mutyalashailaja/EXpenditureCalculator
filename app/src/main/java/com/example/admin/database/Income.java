package com.example.admin.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Income extends AppCompatActivity implements View.OnClickListener {
    EditText Income;
    EditText Month;
    SQLiteDatabase db;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Income = (EditText) findViewById(R.id.editText);
        Month = (EditText) findViewById(R.id.editText5);
        save = (Button) findViewById(R.id.save);

        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(Income VARCHAR,Month VARCHAR);");
       save.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


            if (Income.getText().toString().trim().length() == 0 ||
                    Month.getText().toString().trim().length() == 0
                    ) {
                Toast.makeText(this, "Please enter all values", Toast.LENGTH_LONG);
                return;
            }


        db.execSQL("INSERT INTO student VALUES('"+Income.getText()+"','"+Month.getText()+
               "');");
        Toast.makeText(this,"Success Record added",Toast.LENGTH_SHORT);



        Cursor c=db.rawQuery("SELECT * FROM student", null);
        if(c.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append("Income: "+c.getString(0)+"\n");
            buffer.append("Month: "+c.getString(1)+"\n");

        }
        showMessage("Student Details", buffer.toString());
            clearText();

    }



    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        Income.setText("");
        Month.setText("");


    }



}


