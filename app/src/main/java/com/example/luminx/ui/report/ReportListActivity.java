package com.example.luminx.ui.report;

import android.os.Bundle;

import com.example.luminx.R;

import androidx.appcompat.app.AppCompatActivity;

public class ReportListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        getSupportActionBar().hide();
    }
}