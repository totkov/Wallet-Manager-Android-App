package com.example.neznamue.walletmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddTransactionActivity extends AppCompatActivity {

    RadioGroup transactionTypeRadioGroup;
    EditText transactionTitleEditText;
    EditText transactionAmountEditText;
    Button saveTransactionButton;
    Database db = new Database(this, Database.DB_NAME, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        transactionTypeRadioGroup = findViewById(R.id.rg_transaction_type);
        transactionTitleEditText = findViewById(R.id.et_transaction_title);
        transactionAmountEditText = findViewById(R.id.et_transaction_amount);
        saveTransactionButton = findViewById(R.id.btn_save_transaction);
        saveTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction t = new Transaction();
                t.setType(transactionTypeRadioGroup.getCheckedRadioButtonId() == R.id.rb_transaction_type_income ? 1 : 0);
                t.setTitle(transactionTitleEditText.getText().toString());
                t.setAmount(Double.parseDouble(transactionAmountEditText.getText().toString()));
                db.addTransaction(t);

                Toast.makeText(getApplicationContext(), "Transaction added successfully!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddTransactionActivity.this, TransactionsListActivity.class);
                startActivity(intent);
            }

        });
    }
}
