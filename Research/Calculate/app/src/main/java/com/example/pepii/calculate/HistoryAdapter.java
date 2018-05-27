package com.example.pepii.calculate;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by pepii on 20-May-18.
 */

public class HistoryAdapter extends CursorAdapter {

    private Database db;
    private Cursor cursor;
    private Context context;
    private DeleteListener listener;

    public void setListener(DeleteListener listener) {
        this.listener = listener;
    }

    public HistoryAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);

        this.db = new Database(context, Database.DB_NAME, null, 1);
        this.cursor = cursor;
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(this.context)
                .inflate(R.layout.triangle_cell, viewGroup, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView sideTv = view.findViewById(R.id.sideTvInfo);
        TextView heightTv = view.findViewById(R.id.heightTvInfo);
        TextView surfaceTv = view.findViewById(R.id.surfaceTvInfo);
        View delButton = view.findViewById(R.id.delBt);

        sideTv.setText("a = " + cursor.getDouble(cursor.getColumnIndex(Database.HISTORY_SIDE)));
        heightTv.setText("ha = " + cursor.getDouble(cursor.getColumnIndex(Database.HISTORY_HEIGHT)));
        surfaceTv.setText("S = " + cursor.getDouble(cursor.getColumnIndex(Database.HISTORY_SURFACE)));

        final int id = cursor.getInt(cursor.getColumnIndex(Database.HISTORY_ID));
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDeleteClicked(id);
                }
            }
        });
    }
}
