package com.matrimony.training.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    LayoutInflater mInflater;
    MyAdapter myAdapter;
    ArrayList<Integer> mPlanetImages;
    ArrayList<String> mPlanetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView= (ListView) findViewById(R.id.list_view_1);
        mPlanetImages = new ArrayList<Integer>(Arrays.asList(DataClass.imageData));
        mInflater = getLayoutInflater();
        mPlanetName = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.planet_name)));
        callAdapter();
    }

    public void callAdapter()
    {
        myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return DataClass.ARRAY_LENGTH;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView == null){

                Log.v("inside", parent.toString());
                convertView = mInflater.inflate(R.layout.planetlist,parent,false);
            }


            ImageView imageView = (ImageView)convertView.findViewById(R.id.planetimage);
            imageView.setImageResource(mPlanetImages.get(position));

            TextView fooditem = (TextView) convertView.findViewById(R.id.planetname);
            fooditem.setText(mPlanetName.get(position));

            Log.v("outsidetextview", mPlanetName.get(position).toString());
            Log.v("outsidetextviewarray", mPlanetName.toString());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent in = new Intent(getApplicationContext(),SecondActivity.class);
                    in.putExtra("imageid",mPlanetImages.get(position));
                    in.putExtra("planetname", mPlanetName.get(position));
                    startActivity(in);
                }
            });
            return convertView;
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
