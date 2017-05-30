package com.example.a127107.p6taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddTask extends AppCompatActivity {
    Button btnAdd,btnCancel;
    EditText etName,etDesc,etTime;
    int reqCode = 123;
    Task content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btnAdd = (Button)findViewById(R.id.btnAddTask);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        etName = (EditText)findViewById(R.id.etName);
        etDesc = (EditText)findViewById(R.id.etDesc);
        etTime = (EditText)findViewById(R.id.etTime);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(AddTask.this);
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();

                content = new Task(0, name, desc);
                db.insertTask(content);


                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND,Integer.parseInt(etTime.getText().toString()));
                Intent intent = new Intent(AddTask.this, ScheduledNotificationReceiver.class);
                intent.putExtra("name", name);
                intent.putExtra("desc", desc);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddTask.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                Intent i  = new Intent();

                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(RESULT_OK,i);
                finish();
            }
        });


    }
}
