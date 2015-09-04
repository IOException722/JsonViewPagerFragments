package com.matrimony.training.assignment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment1Pager.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment1Pager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1Pager extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView mImageView ;
    int planetNo;
    int plno;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1Pager.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1Pager newInstance(String param1, String param2) {
        Fragment1Pager fragment = new Fragment1Pager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment1Pager()
    {

    }
    @SuppressLint("ValidFragment")
    public Fragment1Pager(int planetNo)
    {
        this.planetNo = planetNo;
        Log.v("Planetno is", Integer.toString(planetNo));

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
       View view =  inflater.inflate(R.layout.fragment_pager,container,false);
        mImageView = (ImageView) view.findViewById(R.id.img);
     /*   int planetNo =  getArguments().getInt("pnlo");*/
        Log.v("Planetno is", Integer.toString(planetNo));
        Log.v("insidepager1", Integer.toString(planetNo));
        switch (planetNo)
        {
            case 1:
                mImageView.setImageResource(R.drawable.earth1);
                break;
            case 2:
                mImageView.setImageResource(R.drawable.jupiter1);
                break;
            case 3:
                mImageView.setImageResource(R.drawable.mars1);
                break;
            case 4:
                mImageView.setImageResource(R.drawable.mercury1);
                break;
            case 5:
                mImageView.setImageResource(R.drawable.neptune1);
                break;
            case 6:
                mImageView.setImageResource(R.drawable.saturn1);
                break;
            case 7:
                mImageView.setImageResource(R.drawable.sun1);
                break;
            case 8:
                mImageView.setImageResource(R.drawable.uranus1);
                break;
            default:
                mImageView.setImageResource(R.drawable.venus1);
                break;

        }
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
    }

}
