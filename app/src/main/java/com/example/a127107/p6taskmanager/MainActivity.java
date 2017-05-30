package com.example.a127107.p6taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    ArrayList<String> al;
    ArrayAdapter aa;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button)findViewById(R.id.button);
        DBHelper db = new DBHelper(MainActivity.this);
        lv = (ListView)findViewById(R.id.lv);

        al = new ArrayList<String>();
        al.addAll(db.getAllNotes());
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        AddTask.class);

                startActivityForResult(i,9);
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper  dbh = new DBHelper(MainActivity.this);
            al.clear();
            al.addAll(dbh.getAllNotes());
            lv.setAdapter(aa);
            aa.notifyDataSetChanged();
        }
    }

}
