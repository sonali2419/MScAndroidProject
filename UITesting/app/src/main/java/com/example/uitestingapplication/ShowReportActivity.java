package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.repo.ReportRepo;

public class ShowReportActivity extends AppCompatActivity {

    MedicareAppDatabase db;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reports);
        db = Room.databaseBuilder(getApplicationContext(), MedicareAppDatabase.class,"medicareDB").allowMainThreadQueries().build();
        recyclerView =findViewById(R.id.reports_recycler_view);
        db.getReportRepo().getAllReports();
        ReportRecyclerView reportRecyclerView = new ReportRecyclerView(db.getReportRepo().getAllReports());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reportRecyclerView);    }
}
