package com.example.pepii.calculate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public TextView sideTv;
    public TextView heightTv;
    public TextView resTv;
    public Button calcBtn;
    public Button resBtn;
    public Button hisBtn;

    Triangle triangle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sideTv = (TextView)findViewById(R.id.sideTv);
        heightTv = (TextView)findViewById(R.id.heightTv);
        resTv = (TextView)findViewById(R.id.resTv);
        calcBtn = (Button)findViewById(R.id.calcBtn);
        resBtn = (Button)findViewById(R.id.resBtn);
        hisBtn = (Button)findViewById(R.id.hisBtn);

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                double a = 0;
                double ha = 0;
                try {
                    a = Double.parseDouble(sideTv.getText().toString());
                    ha = Double.parseDouble(heightTv.getText().toString());
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }

                double res = (a * ha) / 2;
                resTv.setText(res + "");

                    Database db = new Database(MainActivity.this, Database.DB_NAME, null, 1);

                    Triangle triangle = new Triangle(a, ha, res);
                    db.InsertTriangle(triangle);
            }
        });

        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sideTv.setText(null);
                heightTv.setText(null);
                resTv.setText(null);
            }
        });
        hisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(i);

            }
        });

    }

}
