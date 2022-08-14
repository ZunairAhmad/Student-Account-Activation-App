package com.example.assignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText name , roll, regNo,sem,program,degree,fee,depNo,subject;
    Button btninsert, btnupdate,btndelete,btnview;
    DBForm dbf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.name);
        roll = findViewById(R.id.roll);
        regNo = findViewById(R.id.reg);
        sem = findViewById(R.id.sem);
        program= findViewById(R.id.program);
        degree = findViewById(R.id.degree);
        fee = findViewById(R.id.fee);
        depNo= findViewById(R.id.depositNo);
        subject = findViewById(R.id.subj);

        btninsert= findViewById(R.id.btninsert);
        btndelete = findViewById(R.id.btnDel);
        btnupdate = findViewById(R.id.btnUpdate);
        btnview=findViewById(R.id.btnView);
        dbf = new DBForm(this);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();
                String rollTxt = roll.getText().toString();
                String regNoTxt = regNo.getText().toString();
                String semTxt = sem.getText().toString();
                String prgmTxt = program.getText().toString();
                String degTxt = degree.getText().toString();
                String feeTxt= fee.getText().toString();
                String depTxt = depNo.getText().toString();
                String subjTxt= subject.getText().toString();

                Boolean checkinsertdata = dbf.insertuserdata(nameTxt,rollTxt,regNoTxt,semTxt,prgmTxt,degTxt,feeTxt,depTxt,subjTxt)  ;
                if (checkinsertdata == true){
                    Toast.makeText(HomeActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(HomeActivity.this,"Entry Not Inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt = name.getText().toString();
                String rollTxt = roll.getText().toString();
                String regNoTxt = regNo.getText().toString();
                String semTxt = sem.getText().toString();
                String prgmTxt = program.getText().toString();
                String degTxt = degree.getText().toString();
                String feeTxt= fee.getText().toString();
                String depTxt = depNo.getText().toString();
                String subjTxt= subject.getText().toString();
                Boolean checkupdatedata = dbf.updateuserdata(nameTxt, rollTxt, regNoTxt,semTxt,prgmTxt,degTxt,feeTxt,depTxt,subjTxt);
                if (checkupdatedata == true) {
                    Toast.makeText(HomeActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                }

            }

        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String nameTxt = name.getText().toString();


                 Boolean checkdeletedata = dbf.deleteuserdata(nameTxt);
                   if (checkdeletedata == true) {
                   Toast.makeText(HomeActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                 } else {
                   Toast.makeText(HomeActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                 }



            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbf.getdata();
                if (res.getCount() == 0)   {
                    Toast.makeText(HomeActivity.this,"No Data Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Roll No :"+res.getString(1)+"\n");
                    buffer.append("Subject :"+res.getString(8)+"\n");
               /*     buffer.append("Sem :"+res.getString(3)+"\n");
                    buffer.append("Program :"+res.getString(4));
                    buffer.append("Degree :"+res.getString(5)+"\n");
                    buffer.append("Fee :"+res.getString(6));
                    buffer.append("Deposit No :"+res.getString(7)+"\n");
                    buffer.append("Subject:"+res.getString(8)+"\n");*/
                }
                AlertDialog.Builder  builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries:") ;
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
