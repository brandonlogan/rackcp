package com.rackspace.rackcp.async_tasks;

import com.rackspace.rackcp.MainActivity;
import com.rackspace.rackcp.clients.AuthClient;
import com.rackspace.rackcp.domain.Auth;
import com.rackspace.rackcp.domain.SimpleHttpResponse;

import android.content.Context;
import android.content.Intent;

public class AuthTask implements 
                                AsyncTaskCompleteListener<SimpleHttpResponse>{

    private Context mContext;
    private AuthClient mClient;
    
    public AuthTask(Context context, AuthClient client){
        this.mContext = context;
        this.mClient = client;
    }
    
    @Override
    public SimpleHttpResponse doTask(){
        SimpleHttpResponse response = mClient.get_auth();
        return response;
    }
    
    @Override
    public void onTaskComplete(SimpleHttpResponse result) {
        Intent intent = new Intent(mContext, MainActivity.class);
        Auth data = Auth.deserialize(result);
        intent.putExtra("auth_token", data.authToken);
        intent.putExtra("cloud_servers_address", data.cloudServersAddress);
        mContext.startActivity(intent);
        
    }
    
    public void launchTask(){
        HttpRequestTask task = new HttpRequestTask(mContext, this, "Authenticating...", true);
        task.execute();
    }

}
