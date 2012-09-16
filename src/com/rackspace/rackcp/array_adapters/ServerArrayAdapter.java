package com.rackspace.rackcp.array_adapters;



import com.rackspace.rackcp.R;
import com.rackspace.rackcp.domain.Server;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ServerArrayAdapter extends ArrayAdapter<Server> {

    Context context; 
    int layoutResourceId;
    Server data[] = null;
    
    public ServerArrayAdapter(Context context, int layoutResourceId,
            Server[] data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
    
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ServerHolder holder = null;
        LayoutInflater inflater = null;
        if (row == null){
            inflater = ((Activity)this.context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ServerHolder();
            holder.serverActions = (ImageButton)row.findViewById(R.id.serverActions);
            holder.serverName = (TextView)row.findViewById(R.id.serverName);
            row.setTag(holder);
        }
        else{
            holder = (ServerHolder)row.getTag();
        }
        
        Server server = data[position];
        holder.serverName.setText(server.name);
        holder.serverName.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                AlertDialog ad = new AlertDialog.Builder(ServerArrayAdapter.this.context).create();
                ad.setTitle("Show Item Details");
                ad.setMessage("Coming Soon");
                ad.show();
            }
            
        });
        
        holder.serverActions.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
               AlertDialog ad = new AlertDialog.Builder(ServerArrayAdapter.this.context).create();
               ad.setTitle("Item Actions");
               ad.setMessage("Coming Soon");
               ad.show();
            }
            
        });
        return row;
    }
    
    static class ServerHolder
    {
        ImageButton serverActions;
        TextView serverName;
    }

}
