package com.example.techwork;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.techwork.Data.Contract;
import com.example.techwork.WorkShopData.CursorAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import static com.example.techwork.MainActivity.count;

@SuppressWarnings("ALL")
public class Upcoming_Workshop extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private  static  final int PET_LOADER=0;
    CursorAdapter mCursorAdapter;
    public Upcoming_Workshop()
    {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_upcoming__workshop, container, false);
        ListView petListView = (ListView) view.findViewById(R.id.list);
        mCursorAdapter=new CursorAdapter(getActivity(),null);
        petListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(PET_LOADER,null, this);
        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Register_Workshop.class);
                Uri curentpeturi= ContentUris.withAppendedId(Contract.WorkEntry.CONTENT_URI,id);
                intent.setData(curentpeturi);
                startActivity(intent);
            }
        });
        return view;
    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        String projection[]={Contract.WorkEntry._ID,
                Contract.WorkEntry.COLUMN_WORKSHOP_NAME,
                Contract.WorkEntry.COLUMN_DATE};
        return new CursorLoader(getActivity(),Contract.WorkEntry.CONTENT_URI,projection
                ,null,null,null);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
