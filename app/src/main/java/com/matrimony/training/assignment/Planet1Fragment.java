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
import java.util.ArrayList;
import java.util.HashMap;

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

        View view = inflater.inflate(R.layout.fragment_planet1, container, false);
        pDetail = new HashMap<String, String>();
        pSatellites = new ArrayList<String>();
        pDetail = (HashMap<String, String>) getArguments().getSerializable("pDetail");
        pSatellites = (ArrayList<String>)getArguments().getStringArrayList("mSatellites");
        pName = (TextView) view.findViewById(R.id.planetname);
        plName = (TextView) view.findViewById(R.id.pname);
        pMass = (TextView) view.findViewById(R.id.pmass);
        pVoulme = (TextView) view.findViewById(R.id.pvolume);
        pSarea = (TextView) view.findViewById(R.id.psarea);
        pGravity = (TextView) view.findViewById(R.id.pgravity);
        pImg = (ImageView) view.findViewById(R.id.planetimage);
        bNext = (Button) view.findViewById(R.id.bnext);
        bPrev = (Button) view.findViewById(R.id.bprev);
        pSat = (TextView) view.findViewById(R.id.psatelite);
        iMg1 = (ImageView) view.findViewById(R.id.img1);
        iMg2 = (ImageView) view.findViewById(R.id.img2);
        iMg3 = (ImageView) view.findViewById(R.id.img3);

        pImg.setImageResource(R.drawable.list_earth);
        pImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent  = new Intent(getActivity(),ViewPagerActivity.class);
                    intent.putExtra("plno", 1);
                    startActivity(intent);
            }
        });
        pName.setText("Earth");
        plName.setText("Planet : Earth ");
        for (String str : pDetail.keySet()) {
            Log.v("Values", str);
            if (str.equals("mass")) {
                pMass.setText("Mass : " + pDetail.get(str));
            } else if (str.equals("volume")) {
                pVoulme.setText("Volume : " + pDetail.get(str));
            } else if (str.equals("gravity")) {
                pGravity.setText("Gravity : " + pDetail.get(str));
            } else
                pSarea.setText("Surface Area : " + pDetail.get(str));
        }

        String sat = "Satellites : ";
        for (int i = 0; i < pSatellites.size(); i++) {
            if (i >= 1) {
                sat = sat + ",";
            }
            sat = sat + pSatellites.get(i);
        }
        pSat.setText(sat);

        iMg1.setImageResource(R.drawable.earth1);
        iMg2.setImageResource(R.drawable.earth2);
        iMg3.setImageResource(R.drawable.earth3);

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.sendIdentifer(true);
            }
        });

        bPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.sendIdentifer(false);
            }
        });
        return view;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
        public void sendIdentifer(boolean position);

    }
}
