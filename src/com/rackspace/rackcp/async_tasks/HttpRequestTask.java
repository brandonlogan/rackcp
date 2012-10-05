package com.rackspace.rackcp.async_tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.rackspace.rackcp.domain.SimpleHttpResponse;

public class HttpRequestTask extends AsyncTask<String, String, SimpleHttpResponse>{

    Context mContext;
    ProgressDialog progressBar;
    private IAsyncTaskCompleteListener<SimpleHttpResponse> callback;
    String message;
    boolean showProgress;
    
    public HttpRequestTask(Context context, 
                              IAsyncTaskCompleteListener<SimpleHttpResponse> cb,
                              String m){
        super();
        this.mContext = context;
        this.callback = cb;
        this.message = m;
        this.showProgress = false;
    }
    
    public HttpRequestTask(Context context, 
                              IAsyncTaskCompleteListener<SimpleHttpResponse> cb,
                              String m, boolean showProgress){
        super();
        this.mContext = context;
        this.callback = cb;
        this.message = m;
        this.showProgress = showProgress;
    }

    protected void onPreExecute(){
        if (!showProgress){
            return;
        }
        progressBar = new ProgressDialog(mContext);
        progressBar.setCancelable(true);
        progressBar.setMessage(this.message);
        progressBar.show();
        
    }
    
    @Override
    protected SimpleHttpResponse doInBackground(String... s) {
        SimpleHttpResponse response = callback.doTask();
        return response;
    }
    
    protected void onProgressUpdate(String... progress){
        
    }
    
    protected void onPostExecute(SimpleHttpResponse result){
        if (showProgress){
            if(progressBar.isShowing()){
                progressBar.hide();
            }
        }
        callback.onTaskComplete(result);
    }

}
