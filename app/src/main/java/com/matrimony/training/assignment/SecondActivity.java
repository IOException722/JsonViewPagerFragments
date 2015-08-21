package com.matrimony.training.assignment;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class SecondActivity extends AppCompatActivity implements Planet1Fragment.OnFragmentInteractionListener,
                                                        Planet2Fragment.OnFragmentInteractionListener{
/*    TextView pName,pMass,pVoulme,pGravity, pSarea;
    Button bNext,bPrev;
    ImageView pImg;*/

    String planetName;
    ArrayList<String> mFragmentTagList;
    HashMap<String, String> pDetail;
    ArrayList<String> pSatellites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Intent intent = getIntent();
        Integer imgId = intent.getIntExtra("imageid", -1);
        planetName = intent.getStringExtra("planetname");
        Log.v("idinsideoncreate", Integer.toString(identifier));

        mFragmentTagList = new ArrayList<String>();

        if(imgId == R.drawable.list_earth){
            Log.v("Hiiii", imgId.toString());
            Planet1Fragment fragment = Planet1Fragment.newInstance("Sample String 1","Sample String 2");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.parent, fragment, "Planet1");
            transaction.addToBackStack("planet1");
            mFragmentTagList.add("Planet1");
            transaction.commit();
        }



    }

    private String getJSON(int resourceId){

        char[] buffer = new char[2048];
        try{
            // Obtain an InputStream from the raw resource
            InputStream inputStream = getResources().openRawResource(resourceId);
            // Initialize a BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // Create a writer object
            Writer writer = new StringWriter();
            // loop through BufferedReader until it has read all characters
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

            // This is very important! Do not forget to close your BufferedReader
            reader.close();
            // This is again very important ! Do not forget to close InputStream
            inputStream.close();

            // Print JSON to output
            Log.v("JSON", writer.toString());

            return writer.toString();

        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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

    /*@Override
    public void onStart(){
        super.onStart();
        Log.v("ACTIVITY START","ACTIVITY ON START");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.v("ACTIVITY ON RESUME","ACTIVITY ON RESUME");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.v("ACTIVITY ON PAUSE","ACTIVITY ON PAUSE");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.v("ACTIVITY ON STOP","ACTIVITY ON STOP");
    }


    @Override
    public void onRestart(){
        super.onRestart();
        Log.v("ACTIVITY ON RESTART", "ACTIVITY ON RESTART");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.v("ACTIVITY ON DESTROY","ACTIVITY ON DESTROY");
    }*/

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void sendIndetifier2() {
        Log.v("Idinfuncitonsendid", Integer.toString(identifier));
        clearAllFragments();
        Planet2Fragment fragment = Planet2Fragment.newInstance("Sample String 1","Sample String 2");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.parent, fragment, "Planet2");
        transaction.addToBackStack("planet2");
        transaction.commit();
    }

    @Override
    public void changeMethod(String string) {


    }

    @Override
    public void sendIdentifer(int position) {

        clearAllFragments();
        Planet2Fragment fragment = Planet2Fragment.newInstance("Sample String 1","Sample String 2");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.parent, fragment, "Planet2");
        transaction.addToBackStack("planet2");
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
