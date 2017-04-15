package com.example.admin.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button income;
    Button expenditure;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        income=(Button)findViewById(R.id.income);
        expenditure=(Button)findViewById(R.id.exxpenditure);
        income.setOnClickListener(this);
        expenditure.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.income:
                Intent i=new Intent(this,Income.class);
                startActivity(i);
                break;
            case R.id.exxpenditure:
                Intent j=new Intent(this,Expenditure.class);
                startActivity(j);

        }

    }
}
