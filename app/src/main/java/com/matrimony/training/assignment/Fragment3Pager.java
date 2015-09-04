package com.matrimony.training.assignment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment3Pager.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment3Pager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3Pager extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView mImageView;
    int planetNo;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment3Pager.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment3Pager newInstance(String param1, String param2) {
        Fragment3Pager fragment = new Fragment3Pager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment3Pager() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public Fragment3Pager(int planetNo)
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
        //int planetNo =  getArguments().getInt("pnlo");
        switch (planetNo)
        {
            case 1:
                mImageView.setImageResource(R.drawable.earth3);
                break;
            case 2:
                mImageView.setImageResource(R.drawable.jupiter3);
                break;
            case 3:
                mImageView.setImageResource(R.drawable.mars3);
                break;
            case 4:
                mImageView.setImageResource(R.drawable.mercury3);
                break;
            case 5:
                mImageView.setImageResource(R.drawable.neptune3);
                break;
            case 6:
                mImageView.setImageResource(R.drawable.saturn3);
                break;
            case 7:
                mImageView.setImageResource(R.drawable.sun3);
                break;
            case 8:
                mImageView.setImageResource(R.drawable.uranus3);
                break;
            default:
                mImageView.setImageResource(R.drawable.venus3);
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
