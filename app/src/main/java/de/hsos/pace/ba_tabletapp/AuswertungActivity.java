package de.hsos.pace.ba_tabletapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import de.hsos.pace.ba_tabletapp_respository.RepositoryTaetigkeit;

/**
 * Created by danielzens on 08.02.17.
 */

public class AuswertungActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auswertung);
        Intent intent = getIntent();
        String userid = intent.getStringExtra("userid");
        String busnummer = intent.getStringExtra("busnummer");
        String dienstid = intent.getStringExtra("dienstid");
        String dienstwochentag = intent.getStringExtra("dienstwochentag");
        RepositoryTaetigkeit.getTaetigkeitByDienstIdDienstwochentag(dienstid, dienstwochentag, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(new String(responseBody));
                    JSONArray arr = obj.getJSONArray("data");
                    Log.i("arr", String.valueOf(arr));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("onFailure", String.valueOf(statusCode));
            }
        });
    }
}
