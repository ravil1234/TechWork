package com.example.techwork.WorkShopData;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.techwork.Data.Contract;
import com.example.techwork.R;
import com.example.techwork.Upcoming_Workshop;

public class CursorAdapter extends android.widget.CursorAdapter {

    public CursorAdapter(Context context, Cursor c) {
        super( context,c, 0 );
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }
    @Override
      public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView dateTextView = (TextView) view.findViewById(R.id.date);

        int nameColumnIndex = cursor.getColumnIndex(Contract.WorkEntry.COLUMN_WORKSHOP_NAME);
        int dateColumnIndex = cursor.getColumnIndex(Contract.WorkEntry.COLUMN_DATE);
        String Name = cursor.getString(nameColumnIndex);
        String date = cursor.getString(dateColumnIndex);
        nameTextView.setText(Name);
        dateTextView.setText(date);
    }
}
