package com.rackspace.rackcp.fragments;

import com.rackspace.rackcp.R;
import com.rackspace.rackcp.array_adapters.ServerArrayAdapter;
import com.rackspace.rackcp.domain.Server;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ServersFragment extends Fragment{
    
    public static final String TITLE = "Servers";
    public static final int POSITION = 2;
    
    ListView list;
    Server[] data;
    
    public ServersFragment(Server[] data){
        this.data = data;
    }
    
    @Override 
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.product_servers, container, false);
        list = (ListView)view.findViewById(R.id.serverList);
        ServerArrayAdapter adapter = 
                new ServerArrayAdapter(this.getActivity(),
                                        R.layout.product_servers_row,
                                        this.data);
        list.setAdapter(adapter);
        return view;
    }
}
