package com.rackspace.rackcp.clients;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

import com.rackspace.rackcp.domain.SimpleHttpResponse;
import com.rackspace.rackcp.clients.RestClient;

public class AuthClient {
    private String url;
    private String username;
    private String password;
    private RestClient rest;
    private ArrayList<Header> headers = new ArrayList();

    public AuthClient(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.rest = new RestClient();
        this.headers.add(new BasicHeader("Content-Type", "application/json"));
        this.headers.add(new BasicHeader("Accept", "application/json"));
    }

    public SimpleHttpResponse get_auth() {
        Header[] header_array = new Header[this.headers.size()];
        return this.rest.post(this.url + "/tokens",
                this.headers.toArray(header_array), this.buildBody());
    }

    private StringEntity buildBody() {
        String bodyStr = "{\"auth\":{\"passwordCredentials\":{\"username\":\""
                + this.username + "\",\"password\":\"" + this.password
                + "\"}}}";
        StringEntity bodyEntity = null;
        try {
            bodyEntity = new StringEntity(bodyStr);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bodyEntity;
    }
}
