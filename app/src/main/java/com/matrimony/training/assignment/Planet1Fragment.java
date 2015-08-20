package com.matrimony.training.assignment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Planet1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Planet1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Planet1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    TextView pName,plName,pMass,pVoulme,pGravity, pSarea, pSat;
    Button bNext,bPrev;
    ImageView pImg, iMg1, iMg2, iMg3;
    HashMap<String, String> pDetail;
    ArrayList<String> pSatellites;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Planet1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Planet1Fragment newInstance(String param1, String param2) {
        Planet1Fragment fragment = new Planet1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Planet1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_planet1, container, false);
        pName = (TextView)view.findViewById(R.id.planetname);
        plName = (TextView) view.findViewById(R.id.pname);
        pMass = (TextView) view.findViewById(R.id.pmass);
        pVoulme = (TextView)view.findViewById(R.id.pvolume);
        pSarea =(TextView)view.findViewById(R.id.psarea);
        pGravity = (TextView)view. findViewById(R.id.pgravity);
        pImg= (ImageView)view.findViewById(R.id.planetimage);
        bNext = (Button)view.findViewById(R.id.bnext);
        bPrev = (Button)view.findViewById(R.id.bprev);
        pSat = (TextView) view.findViewById(R.id.psatelite);
        iMg1 = (ImageView) view.findViewById(R.id.img1);
        iMg2 = (ImageView) view.findViewById(R.id.img2);
        iMg3 = (ImageView) view.findViewById(R.id.img3);
        pSatellites = new ArrayList<String>();
        pDetail = new HashMap<String, String>();
        String output2 = getJSON(R.raw.solar_system_data);
        processOutput2(output2);

        Log.v("PDis", pDetail.toString());
        Log.v("satellites", pSatellites.toString());
        pImg.setImageResource(R.drawable.list_earth);
        pName.setText("Earth");
        plName.setText("Planet : Earth ");
        for(String str:pDetail.keySet()) {
            Log.v("Values", str);
            if(str.equals("mass"))
            {
                pMass.setText("Mass : " + pDetail.get(str));
            }
            else if(str.equals("volume"))
            {
                pVoulme.setText("Volume : "+ pDetail.get(str));
            }
            else if(str.equals("gravity"))
            {
                pGravity.setText("Gravity : "+ pDetail.get(str));
            }
            else
                pSarea.setText("Surface Area : " + pDetail.get(str));
        }

        String sat = "Satellites : ";
        for(int i=0;i<pSatellites.size();i++)
        {
            if(i>=1)
            {
                sat = sat+",";
            }
            sat=sat+pSatellites.get(i);
        }
        pSat.setText(sat);

        iMg1.setImageResource(R.drawable.list_earth);
        iMg2.setImageResource(R.drawable.list_earth);
        iMg3.setImageResource(R.drawable.list_earth);
       /* TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);*/

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                mListener.sendIdentifer(1);
           /*     Intent in = new Intent(getActivity(),SecondActivity.class);
                startActivity(in);*/
            }
        });

      /*  bPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.sendIdentifer(0);
            }
        });*/
        return view;
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

    // Study the underlying JSON well before using this method
    private void processOutput2(String output){
        try {
            // Since the JSON output begins with curly-braces this implies that it
            // is a JSONObject, hence use the JSONObject class
            JSONObject jsonObject = new JSONObject(output);
            int length = jsonObject.length();
            HashMap<String,String> map = new HashMap<String,String>();
            // Iterating through a JSON Object requires an iterator mapped to its keys
            Iterator<String> iterator = jsonObject.keys();
            while(iterator.hasNext()){
                String key = iterator.next();
                Log.v("kyees", key);
                // Obtain values from keys
                Log.v("upp",key.toUpperCase());
                if(key.toUpperCase().equals("EARTH"))
                {
                    Log.v("iiikeya", key);
                    JSONObject jsonObject1 = new JSONObject(jsonObject.getString(key));
                    Log.v("iiikey", key);
                    Iterator<String> iterator1 = jsonObject1.keys();
                    int lenght1 = jsonObject1.length();
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
                                Log.v("insideequals", jsonArray.get(index).toString());
                            }
                        }
                        else
                            pDetail.put(key1,jsonObject1.getString(key1));
                    }
                }

                Log.v("pdetail", pDetail.toString());
                Log.v("pSatellites", pSatellites.toString());
            }

            Log.v("MAP",map.toString());
        }
        // Has to be implemented, otherwise compilation will fail - remove the try and catch-blocks
        catch(JSONException jsonException){

            jsonException.printStackTrace();
        }
        catch(Exception exception){

            exception.printStackTrace();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
        public void changeMethod(String string);
        public void sendIdentifer(int position);

    }

}
