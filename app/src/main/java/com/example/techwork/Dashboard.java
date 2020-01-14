package com.example.techwork;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.techwork.Data.Contract;
import com.google.android.material.tabs.TabLayout;
import static com.example.techwork.MainActivity.count;
public class Dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager(),Dashboard.this);
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (count==0) {
            MenuItem menuItem = menu.findItem(R.id.action_save);
            menuItem.setVisible(false);
                }

        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_add:
                insertdata();
                return true;
            case R.id.action_save:

                Intent i = new Intent(this, saved_workshop.class);
                startActivity(i);
                return true;
              default:
                  return true;
        }
    }
     String w[]={"Python Basics","Java Core","Android  Development","Programming in C++"};
    String d[]={"12-01-2020","13-01-2020","15-01-2020","16-01-2020"};
    int c=0;
        public void insertdata()
        {
            if(c==4)
            {
                c=0;
            }
            ContentValues values = new ContentValues();
            values.put(Contract.WorkEntry.COLUMN_WORKSHOP_NAME,w[c]);
            values.put(Contract.WorkEntry.COLUMN_DATE, d[c]);
            Uri newUri =getContentResolver().insert(Contract.WorkEntry.CONTENT_URI, values);
            c++;
        }
    }