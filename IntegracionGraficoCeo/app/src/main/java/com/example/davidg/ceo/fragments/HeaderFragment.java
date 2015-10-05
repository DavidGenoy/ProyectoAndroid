package com.example.davidg.ceo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidg.ceo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeaderFragment extends Fragment {

    public HeaderFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View v =inflater.inflate(R.layout.fragment_header, container, false);
        return v;
    }


}
