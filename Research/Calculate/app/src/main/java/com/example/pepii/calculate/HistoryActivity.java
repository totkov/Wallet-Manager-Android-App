package com.example.pepii.calculate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity implements DeleteListener {

    ListView historyLv;
    Button clearAllBtn;
    Cursor cursor;
    HistoryAdapter adapter;
    Database db = new Database(this, Database.DB_NAME, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori);

        historyLv = findViewById(R.id.hisLv);

        clearAllBtn = findViewById(R.id.clearAllBtn);
        clearAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAllSurfaces();
                getAllSurfaces();
                Toast.makeText(getApplicationContext(), "All results are deleted!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getAllSurfaces();
    }

    private void getAllSurfaces() {
        cursor = db.getAllSurfaces();
        adapter = new HistoryAdapter(this, cursor);
        adapter.setListener(this);
        historyLv.setAdapter(adapter);
    }

    @Override
    public void onDeleteClicked(int id) {
        db.deleteSurface(id);
        this.getAllSurfaces();
    }
}
