package com.example.neznamue.walletmanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TransactionsListActivity extends AppCompatActivity implements DeleteListener {

    ListView transactionsLv;
    Button addNewTransactionBtn;
    Cursor cursor;
    TransactionsAdapter adapter;
    Database db = new Database(this, Database.DB_NAME, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_list);

        transactionsLv = findViewById(R.id.lv_transactions);
        addNewTransactionBtn = findViewById(R.id.btn_add_new_transaction);
        addNewTransactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransactionsListActivity.this, AddTransactionActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getAllTransactions();
    }

    @Override
    public void onDeleteClicked(int id) {
        db.deleteTransactionById(id);
        this.getAllTransactions();
    }

    private void getAllTransactions() {
        cursor = db.getAllTransactions();
        adapter = new TransactionsAdapter(this, cursor);
        adapter.setListener(this);
        transactionsLv.setAdapter(adapter);
    }
}
