package com.rackspace.rackcp.array_adapters;



import java.util.ArrayList;

import com.rackspace.rackcp.R;
import com.rackspace.rackcp.domain.Server;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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
            holder.statusColor = (TextView)row.findViewById(R.id.statusColor);
            holder.settingsList = (ListView)row.findViewById(R.id.settingsList);
            row.setTag(holder);
        }
        else{
            holder = (ServerHolder)row.getTag();
        }
        
        final Server server = data[position];
        holder.serverName.setText(server.name);
        final ListView temp = holder.settingsList;
        holder.serverName.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                AlertDialog ad = new AlertDialog.Builder(ServerArrayAdapter.this.context).create();
                ad.setTitle("Show " + server.name + " Details");
                ad.setMessage("Coming Soon");
                ad.show();
            }
            
        });
        
        holder.serverActions.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder b = new Builder(ServerArrayAdapter.this.context);
                b.setTitle("Options for " + server.name);
                String[] options = new String[]{"Rename", "Reboot", "Rebuild"};
                b.setItems(options, new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                       AlertDialog ad = new AlertDialog.Builder(ServerArrayAdapter.this.context).create();
                       ad.setTitle(server.name + " Action");
                       switch(which){
                       case 0:
                           ad.setMessage("Renaming...");
                           break;
                       case 1:
                           ad.setMessage("Rebooting...");
                           break;
                       case 2:
                           ad.setMessage("Rebuilding...");
                           break;
                       }
                       ad.show();
                    }
                });
                b.show();
            }
            
        });
        return row;
    }
    
    static class ServerHolder
    {
        ImageButton serverActions;
        TextView serverName;
        TextView statusColor;
        ListView settingsList;
    }

}
