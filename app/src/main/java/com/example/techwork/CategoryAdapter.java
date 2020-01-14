package com.example.techwork;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

@SuppressWarnings("ALL")
public class CategoryAdapter  extends FragmentPagerAdapter {
    Context context;
    public CategoryAdapter(@NonNull FragmentManager fm, Context c) {
        super(fm);
        context=c;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if(position==0)
        return new Upcoming_Workshop();
        else
            return new Archives();
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        if(position==0)
        {
            return context.getString(R.string.upcoming_work);
        }
        else
        {
            return context.getString(R.string.archives);
        }
    }
}

