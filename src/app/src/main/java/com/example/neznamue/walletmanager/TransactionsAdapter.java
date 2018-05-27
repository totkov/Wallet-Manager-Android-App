package com.example.neznamue.walletmanager;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by nezna on 5/27/2018.
 */

public class TransactionsAdapter extends CursorAdapter {

    private Database db;
    private Cursor cursor;
    private Context context;
    private DeleteListener listener;

    public TransactionsAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);

        this.db = new Database(context, Database.DB_NAME, null, 1);
        this.cursor = cursor;
        this.context = context;
    }

    public void setListener(DeleteListener listener) {
        this.listener = listener;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(this.context)
                .inflate(R.layout.item_transaction, viewGroup, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleTv = view.findViewById(R.id.tv_item_transaction_title);
        TextView amountTv = view.findViewById(R.id.tv_item_transaction_amount);
        Button deleteTransactionButton = view.findViewById(R.id.btn_delete_transaction);

        int type = cursor.getInt(cursor.getColumnIndex(Database.TRANSACTIONS_TYPE));
        String amountStr;
        if (type == 1) {
            amountStr = "+" + cursor.getDouble(cursor.getColumnIndex(Database.TRANSACTIONS_AMOUNT));
            amountTv.setTextColor(ContextCompat.getColor(context, R.color.colorAmountGreen));
        } else {
            amountStr = "-" + cursor.getDouble(cursor.getColumnIndex(Database.TRANSACTIONS_AMOUNT));
            amountTv.setTextColor(ContextCompat.getColor(context, R.color.colorAmountRed));
        }

        titleTv.setText(cursor.getString(cursor.getColumnIndex(Database.TRANSACTIONS_TITLE)));
        amountTv.setText(amountStr);

        final int id = cursor.getInt(cursor.getColumnIndex(Database.TRANSACTIONS_ID));
        deleteTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDeleteClicked(id);
                }
            }
        });
    }
}
