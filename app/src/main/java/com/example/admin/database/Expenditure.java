package com.example.admin.database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Expenditure extends AppCompatActivity implements View.OnClickListener {


    EditText Rent;
    EditText Groceries;
    EditText Utilitybills;
    EditText Other;
    Button save;
    Button calculate;
    SQLiteDatabase db;
    TextView Result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);
        Rent=(EditText)findViewById(R.id.editText2);
        Groceries=(EditText)findViewById(R.id.groceries);
        Utilitybills=(EditText)findViewById(R.id.utility);
        Other=(EditText)findViewById(R.id.Other);
        Result=(TextView)findViewById(R.id.result);
        save=(Button)findViewById(R.id.button2);
        calculate=(Button)findViewById(R.id.button);

        db = openOrCreateDatabase("EmployeeDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Employee(Rent VARCHAR,Groceries VARCHAR,Utilitybills VARCHAR,Other VARCHAR);");

        save.setOnClickListener(this);
        calculate.setOnClickListener(this);






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:



            if (Rent.getText().toString().trim().length() == 0 ||
                    Groceries.getText().toString().trim().length() == 0 ||
                    Utilitybills.getText().toString().trim().length()== 0 ||
                    Other.getText().toString().trim().length()==0

                    ) {
                Toast.makeText(this, "Please enter all values", Toast.LENGTH_LONG);
                return;



            }
                db.execSQL("INSERT INTO Employee VALUES('"+Rent.getText()+"','"+Groceries.getText()+"','"
                        +Utilitybills.getText()+"','"+Other.getText()+
                        "');");
                Toast.makeText(this,"Success Record added",Toast.LENGTH_SHORT);


                Cursor c=db.rawQuery("SELECT * FROM Employee", null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("Rent: "+c.getString(0)+"\n");
                buffer.append("Groceries: "+c.getString(1)+"\n");
                buffer.append("Utilitybills: "+c.getString(2)+"\n");
                buffer.append("Other: "+c.getString(3)+"\n");


            }

            showMessage("Employee Details", buffer.toString());
            clearText();
                break;

            case R.id.button:

                float r = 0;
                float g = 0;
                float u= 0;
                float o=0;
                float result = 0;
                String oper = "";

                // check if the fields are empty
                if (TextUtils.isEmpty(Rent.getText().toString()) || TextUtils.isEmpty(Groceries.getText().toString()) ||TextUtils.isEmpty(Utilitybills.getText().toString()) ||TextUtils.isEmpty(Other.getText().toString()) )
                    return;

                // read EditText and fill variables with numbers
                r = Float.parseFloat(Rent.getText().toString());
                g= Float.parseFloat(Groceries.getText().toString());
                u = Float.parseFloat(Utilitybills.getText().toString());
                o= Float.parseFloat(Other.getText().toString());

                result=r+g+u+o;
                Result.setText( "Total expenditure is    "+String.valueOf(result));
                break;

        }

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
        Rent.setText("");
        Groceries.setText("");
        Utilitybills.setText("");
        Other.setText("");


    }

}








