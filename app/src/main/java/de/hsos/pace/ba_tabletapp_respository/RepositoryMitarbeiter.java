package de.hsos.pace.ba_tabletapp_respository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import de.hsos.pace.ba_tabletapp.StartscreenActivity;
import de.hsos.pace.de.hsos.pace.ba_tabletapp_model.User;

/**
 * Created by danielzens on 26.01.17.
 */

public class RepositoryMitarbeiter {
    private static final String conn_str = "http://131.173.111.8/ba/api/";

    public void getMitarbeiterIdByUsernamePassword(final User user, final Context appctx){
        String url = conn_str + "mitarbeiter/"+user.getUsername()+"/"+user.getPasswort();
        Log.i("URL: ", url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //log.i("id: ", response.toString());
                try {
                    if(response.getJSONArray("data").length() == 0){
                        Toast.makeText(appctx, "Benutzername oder Passwort falsch.", Toast.LENGTH_LONG).show();
                        user.setId(0);
                    }else{
                        JSONArray arr = response.getJSONArray("data");
                        int id = arr.getJSONObject(0).getInt("id");
                        Intent intent = new Intent(appctx, StartscreenActivity.class);
                        intent.putExtra("userid", String.valueOf(id));
                        appctx.startActivity(intent);
                        user.setId(id);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
