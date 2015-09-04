package com.matrimony.training.assignment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;


public class ViewPagerActivity extends AppCompatActivity implements Fragment1Pager.OnFragmentInteractionListener,
                                                                    Fragment2Pager.OnFragmentInteractionListener,
                                                                    Fragment3Pager.OnFragmentInteractionListener

{

    ViewPager mViewPager;
    MyPagerAdapter mAdapter;
    ArrayList<String> mFragmentTagList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        mFragmentTagList = new ArrayList<String>();
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        Log.v("uooo", "Yoactivity sgarted");
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        Intent intent = getIntent();
        int planetNo = intent.getIntExtra("plno", -1);

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

        }
        @Override
        public Fragment getItem(int i) {
            Log.v("Planetnois", Integer.toString(planetNo));
            if(i == 0) {
              /*  Log.v("insideviewpager", Integer.toString(planetNo));
                Fragment1Pager fragment = Fragment1Pager.newInstance("New Instance 1", "New Instance 2");
                clearAllFragments();
                callFragment(planetNo, fragment, "Pager1");*/
               /* Bundle bundle = new Bundle();
                bundle.putInt("plno", planetNo);*/
               /* fragment.setArguments(bundle);*/
                return new Fragment1Pager(planetNo);
            }
            else if(i == 1)
            {
                /*Fragment2Pager fragment = Fragment2Pager.newInstance("New Instance 1", "New Instance 2");
                clearAllFragments();
                callFragment(planetNo, fragment, "Pager2");*/
                return new Fragment2Pager(planetNo);
            }
            else if(i == 2)
            {
                /*Fragment3Pager fragment = Fragment3Pager.newInstance("New Instance 1", "New Instance 2");
                clearAllFragments();
                callFragment(planetNo, fragment, "Pager3");*/
                return new Fragment3Pager(planetNo);
            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


    public void callFragment(int planetNo, Fragment fragment, String tag)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.view_pager, fragment, tag);
        transaction.addToBackStack(tag);
        mFragmentTagList.add(tag);
        Bundle bundle = new Bundle();
        bundle.putInt("plno", planetNo);
        fragment.setArguments(bundle);
        transaction.commit();
    }

    private void clearAllFragments(){
        // declare an iterator
        Iterator<String> iterator = mFragmentTagList.iterator();
        while(iterator.hasNext()){

            String tag = iterator.next();

            FragmentManager manager = getSupportFragmentManager();

            Fragment fragment = manager.findFragmentByTag(tag);
            // check if returned fragment exists

            if(fragment != null){

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.remove(fragment);
                transaction.commit();
            }
            // remove tag from arraylist
            iterator.remove();
        }// end of while

        // check if all fragment tags have been removed, else clear the list
        if(mFragmentTagList.size() != 0){
            mFragmentTagList.clear();
        }
    }
}
