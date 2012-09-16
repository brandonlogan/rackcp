package com.rackspace.rackcp.domain;

import org.json.JSONArray;
import org.json.JSONException;

public class Auth {
    public String authToken;
    public String cloudServersAddress;
    
    public Auth(String authToken, String cloudServersAddress){
        this.authToken = authToken;
        this.cloudServersAddress = cloudServersAddress;
    }
    
    public static Auth deserialize(SimpleHttpResponse r){
        String authToken = "";
        try
        {
            authToken = r.jsonBody.getJSONObject("access").getJSONObject("token").getString("id");
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String cloudServersAddress = "";
        JSONArray serviceCatalog = null;
        JSONArray cloudServersEndpoints = null;
        try
        {
            serviceCatalog = r.jsonBody.getJSONObject("access").getJSONArray("serviceCatalog");
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int i = 0; i < serviceCatalog.length(); i++){
            String productName = "";
            try
            {
                productName = serviceCatalog.getJSONObject(i).getString("name");
            } catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(productName.equalsIgnoreCase("cloudServers")){
                try
                {
                    cloudServersAddress = serviceCatalog.getJSONObject(i).getJSONArray("endpoints").getJSONObject(0).getString("publicURL");
                } catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return new Auth(authToken, cloudServersAddress);
    }
}
