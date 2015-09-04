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
                                                        Planet2Fragment.OnFragmentInteractionListener,
                                                        Planet3Fragment.OnFragmentInteractionListener,
                                                        Planet4Fragment.OnFragmentInteractionListener,
                                                        Planet5Fragment.OnFragmentInteractionListener,
                                                        Planet6Fragment.OnFragmentInteractionListener,
                                                        Planet7Fragment.OnFragmentInteractionListener,
                                                        Planet8Fragment.OnFragmentInteractionListener,
                                                        Planet9Fragment.OnFragmentInteractionListener


    {

    String planetName, output;
    ArrayList<String> mFragmentTagList;
    HashMap<String, String> pDetail;
    ArrayList<String> pSatellites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Intent intent = getIntent();
     //   Integer imgId = intent.getIntExtra("imageid", -1);
        planetName = intent.getStringExtra("planetname");
        Log.v("planetname", planetName);

        pDetail = new HashMap<String, String>();
        pSatellites = new ArrayList<String>();
        mFragmentTagList = new ArrayList<String>();
        output = getJSON(R.raw.solar_system_data);
        if(planetName.toUpperCase().equals("EARTH")){
            Planet1Fragment fragment = Planet1Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output, fragment, "Planet1");
        }
        else if(planetName.toUpperCase().equals("JUPITER")){
            Planet2Fragment fragment = Planet2Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output, fragment, "Planet2");
        }
       else if(planetName.toUpperCase().equals("MARS")){
            Planet3Fragment fragment = Planet3Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output, fragment, "Planet3");
        }
        else if(planetName.toUpperCase().equals("MERCURY")){

            Planet4Fragment fragment = Planet4Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output, fragment, "Planet4");
        }
        else if(planetName.toUpperCase().equals("NEPTUNE")){
            Planet5Fragment fragment = Planet5Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output, fragment, "Planet5");
        }
        else if(planetName.toUpperCase().equals("SATURN")){
            Planet6Fragment fragment = Planet6Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output, fragment, "Planet6");
        }
        else if(planetName.toUpperCase().equals("SUN")){
            Planet7Fragment fragment = Planet7Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output, fragment, "Planet7");
        }
        else if(planetName.toUpperCase().equals("URANUS")){
            Planet8Fragment fragment = Planet8Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output, fragment, "Planet8");
        }
        else if(planetName.toUpperCase().equals("VENUS")) {
            Planet9Fragment fragment = Planet9Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output, fragment, "Planet9");
        }
    }

    public void callFragment(String output, Fragment fragment, String tag)
    {
        pDetail.clear();
        pSatellites.clear();
        clearAllFragments();
        Log.v("ohyeah", planetName);
        processOutput(output, planetName);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.parent, fragment, tag);
        transaction.addToBackStack(null);
        mFragmentTagList.add(tag);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("mSatellites", pSatellites);
        bundle.putSerializable("pDetail",pDetail);
        fragment.setArguments(bundle);
        transaction.commit();
    }
    private String getJSON(int resourceId){
        char[] buffer = new char[2048];
        try{
            InputStream inputStream = getResources().openRawResource(resourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Writer writer = new StringWriter();
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            reader.close();
            inputStream.close();
            Log.v("JSON", writer.toString());

            return writer.toString();

        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
    private void processOutput(String output, String planetName){
        try {
            JSONObject jsonObject = new JSONObject(output);
            HashMap<String,String> map = new HashMap<String,String>();
            Iterator<String> iterator = jsonObject.keys();
            while(iterator.hasNext()){
                String key = iterator.next();
                if(key.toUpperCase().equals(planetName.toUpperCase()))
                {
                    JSONObject jsonObject1 = new JSONObject(jsonObject.getString(key));
                    Iterator<String> iterator1 = jsonObject1.keys();
                    while (iterator1.hasNext())
                    {
                        String key1 = iterator1.next();
                        if(key1.equals("satellites"))
                        {
                            JSONArray jsonArray = jsonObject1.getJSONArray(key1);
                            int length2 = jsonArray.length();
                            for(int index=0; index<length2; index++)
                            {
                                pSatellites.add(jsonArray.get(index).toString());
                            }
                        }
                        else
                            pDetail.put(key1,jsonObject1.getString(key1));
                    }
                }
            }
        }
        catch(JSONException jsonException){

            jsonException.printStackTrace();
        }
        catch(Exception exception){

            exception.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void sendIdentifier3(boolean position) {

        if(position)
        {
            Planet4Fragment fragment = Planet4Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet4");
        }
        else
        {
            Planet2Fragment fragment = Planet2Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet2");
        }

    }

    @Override
    public void sendIdentifier5(boolean position) {

        if(position)
        {
            Planet6Fragment fragment = Planet6Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet6");
        }
        else
        {
            Planet4Fragment fragment = Planet4Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet4");
        }


    }

    @Override
    public void sendIdentifier9(boolean position) {

        if(position)
        {

        }
        else
        {
            Planet8Fragment fragment = Planet8Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet8");
        }
    }

    @Override
    public void sendIdentifier7(boolean position) {

        if(position)
        {
            Planet8Fragment fragment = Planet8Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet8");
        }
        else
        {
            Planet6Fragment fragment = Planet6Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet6");
        }
    }

    @Override
    public void sendIdentifier8(boolean position) {
        if(position)
        {
            Planet9Fragment fragment = Planet9Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet9");
        }
        else
        {
            Planet7Fragment fragment = Planet7Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet7");
        }


    }

    @Override
    public void sendIdentifier6(boolean position) {

        if(position)
        {
            Planet7Fragment fragment = Planet7Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet7");
        }
        else
        {
            Planet5Fragment fragment = Planet5Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet5");
        }

    }

    @Override
    public void sendIdentifier4(boolean position) {
        if(position)
        {
            Planet5Fragment fragment = Planet5Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet5");
        }
        else
        {
            Planet3Fragment fragment = Planet3Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet3");
        }

    }

    @Override
    public void sendIdentifier2(boolean position) {
        if(position)
        {
            Planet3Fragment fragment = Planet3Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet3");
        }
        else
        {
            Planet1Fragment fragment = Planet1Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet1");
        }

    }


    @Override
    public void sendIdentifer(boolean position)
    {
        if(position)
        {
            Planet2Fragment fragment = Planet2Fragment.newInstance("Sample String 1","Sample String 2");
            callFragment(output,fragment,"Planet2");
        }
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
