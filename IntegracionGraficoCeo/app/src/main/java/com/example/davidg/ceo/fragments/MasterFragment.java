package com.example.davidg.ceo.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.davidg.ceo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;
    ArrayAdapter<String>adapter;

    public interface OnItemSelectedList{
        public void  onItemSelectedList(int pos);
    }

    public interface ListMasterFragment{
        public void  listMasterFragment(ListView listView);
    }

    OnItemSelectedList onItemSelectedList;
    ListMasterFragment listMasterFragment;



    public MasterFragment() {}


    @Override
    public void onAttach(Context context) {
        onItemSelectedList= (OnItemSelectedList) context;
        listMasterFragment= (ListMasterFragment) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v= inflater.inflate(R.layout.fragment_master, container, false);
        list= (ListView) v.findViewById(R.id.list);
        listMasterFragment.listMasterFragment(list);
        list.setOnItemClickListener(this);


        return v;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onItemSelectedList.onItemSelectedList(position);
    }
}
