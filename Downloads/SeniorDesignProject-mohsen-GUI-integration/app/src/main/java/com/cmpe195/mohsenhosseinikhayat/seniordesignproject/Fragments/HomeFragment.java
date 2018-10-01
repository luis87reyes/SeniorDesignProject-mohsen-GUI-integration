package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends ListFragment {


    String [] players = {"Cheese Burger", "Lasagna", "Baked Mushrooms", "Potatoes","Salmon","Scallops", "Shrimps"};
    int[] images = {R.mipmap.hamburger, R.mipmap.lasagna, R.mipmap.mushrooms,R.mipmap.potattoes,
            R.mipmap.salmon,R.mipmap.scallops,R.mipmap.shrimp2};

    ArrayList< HashMap<String,String>> data=  new ArrayList<HashMap<String,String>>();
    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        //MAP
        HashMap<String,String> map = new HashMap<String,String>();


        //FILL
        for (int i=0; i<players.length; i++){

            map = new HashMap<String,String>();
            map.put("Player",players[i]);
            map.put ("Image", Integer.toString (images[i]));

            data.add(map);
        }

        //KEYs in MAP

        String [] from = {"Player", "Image"};


        //IDS of VIEWS
        int [] to = {R.id.buckysText, R.id.buckysImage};


        //ADAPTER
        adapter = new SimpleAdapter(getActivity(), data, R.layout.custom_row,from,to);
        setListAdapter(adapter);






        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {

                Toast.makeText(getActivity(), data.get(pos).get("Player"),Toast.LENGTH_SHORT).show();

            }
        });
    }

}
