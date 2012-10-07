package com.rackspace.rackcp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rackspace.rackcp.async_tasks.AuthTask;
import com.rackspace.rackcp.clients.AuthClient;
import com.rackspace.rackcp.domain.Auth;

public class Login extends Activity {
    /** Called when the activity is first created. */
    
    public static Auth authData;
    
    private EditText username;
    private EditText password;
    private Button signInButton;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Context context = this;
        username = (EditText)this.findViewById(R.id.UsernameET);
        password = (EditText)this.findViewById(R.id.PasswordET);
/*        username.setText("cloudqabrandon");
        password.setText("Pass1234");*/
        signInButton = (Button)this.findViewById(R.id.SignInButton);
        url = this.getResources().getText(R.string.auth_url).toString();
        signInButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                AuthClient auth = new AuthClient(url, user, pass);
                new AuthTask(context, auth).launchTask();
            }
        });

    }

}