package de.hsos.pace.ba_tabletapp_respository;

import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.Response;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by danielzens on 26.01.17.
 */

public class RepositoryMitarbeiter {
    private static final String conn_str = "http://131.173.111.8/ba/api/";

    private static int id = 0;

    public int getId(){
        //Log.i("getID: ", String.valueOf(this.id));
        return this.id;
    }

    public void setId(int id){
        this.id = id;
        //Log.i("setID: ", String.valueOf(this.id));
    }

    public int getMitarbeiterIdByUsernamePassword(String username, String password){
        String url = conn_str + "mitarbeiter/" + username +"/" + password;
        Log.i("URL: ", url);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //log.i("id: ", response.toString());
                try {
                    if(response.getJSONArray("data").length() == 0){
                        //log.i("fehler: ", "fehler");
                        setId(0);
                    }else{
                        log.i("hier", "hier");
                        JSONArray arr = response.getJSONArray("data");
                        int id = arr.getJSONObject(0).getInt("id");
                        log.i("idtoset: ", String.valueOf(id));
                        setId(id);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return getId();
    }
}
