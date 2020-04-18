package com.mgkhnyldz.todoapp.AddToDo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mgkhnyldz.todoapp.Home.MainActivity;
import com.mgkhnyldz.todoapp.R;
import com.mgkhnyldz.todoapp.utils.DataModel;

import java.util.Calendar;

public class AddToDoActivity extends AppCompatActivity {

    private Button btnDate, btnTime, switchBtn;
    private FloatingActionButton sendFAB;
    private EditText etDate, etTime, etHeader;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        init();
        datePickerSelect();
        timePickerSelect();
        sendFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("kontrol", true);
                intent.putExtra("header", etHeader.getText().toString());
                intent.putExtra("date", mDay + "/" + mMonth + "/" + mYear);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    private void init() {
        btnDate = findViewById(R.id.btnDatePicker);
        btnTime = findViewById(R.id.btnTimePicker);
        switchBtn = findViewById(R.id.switchBtn);
        etDate = findViewById(R.id.editText);
        etTime = findViewById(R.id.editText2);
        sendFAB = findViewById(R.id.sendFAB);
        etHeader = findViewById(R.id.etHeader);
    }


    private void datePickerSelect() {

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog pickerDialog = new DatePickerDialog(AddToDoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText(year + "/" + month + "/" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);

                pickerDialog.show();
            }
        });

    }

    private void timePickerSelect() {
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddToDoActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                etTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });

    }
}
