package de.hsos.pace.ba_tabletapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;
import de.hsos.pace.ba_tabletapp_respository.RepositoryDienstlisteDienst;
import de.hsos.pace.ba_tabletapp_respository.RepositoryFahrzeug;
import de.hsos.pace.ba_tabletapp_respository.RepositoryTaetigkeit;

/**
 * Created by danielzens on 27.01.17.
 */

public class StartscreenActivity extends AppCompatActivity{
    //RepositoryDienstlisteDienst rdd = new RepositoryDienstlisteDienst();
    TextView dienstId;
    TextView dienstWochentag;
    TextView dienstBeginn;
    TextView dienstOrt;
    TextView dienstFahrzeug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);
        Intent intent = getIntent();
        String id = intent.getStringExtra("userid");
        final int mitarbeiterid = Integer.parseInt(id);

        //Holen der Dienstlistennummer
        RepositoryDienstlisteDienst.getDienstListeId(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject obj = new JSONObject(new String(responseBody));
                    JSONObject tmpobj = obj.getJSONObject("data");
                    int dienstlisteid = tmpobj.getInt("id");

                    //Holen der DienstID und des Dienstwochentags
                    RepositoryDienstlisteDienst.getDienstNummerByIdDatum(mitarbeiterid, dienstlisteid, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String dienstid = "";
                            String dienstwochentag = "";
                            try {
                                JSONObject obj = new JSONObject(new String(responseBody));
                                if(obj.getString("data").length() == 0){
                                    Log.i("Keine Daten", "Keine Daten");
                                }else{
                                    JSONArray tmparr = obj.getJSONArray("data");
                                    JSONObject newjson = tmparr.getJSONObject(0);
                                    dienstwochentag = newjson.getString("dienstwochentag");
                                    dienstid = newjson.getString("dienstid");
                                    dienstId = (TextView)findViewById(R.id.txtInputDienstnummer);
                                    dienstId.setText(dienstid);
                                    dienstWochentag = (TextView)findViewById(R.id.txtInputDienstWochentag);
                                    dienstWochentag.setText(dienstwochentag.trim());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            //Holen der Startzeit und des ersten Ortes der Haltestelle
                            RepositoryTaetigkeit.getTaetigkeitByDienstIdDienstwochentag(dienstid, dienstwochentag, new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    try {
                                        JSONObject obj = new JSONObject(new String(responseBody));
                                        JSONArray arr = obj.getJSONArray("data");
                                        JSONObject newobj = arr.getJSONObject(0);
                                        if(newobj.getString("ort_kuerzel").equals("       ")){
                                            newobj = arr.getJSONObject(1);
                                        }
                                        String startzeit = newobj.getString("startzeit");
                                        String ort = newobj.getString("ort_kuerzel");
                                        dienstBeginn = (TextView)findViewById(R.id.txtInputDienstbeginn);
                                        dienstBeginn.setText(startzeit);
                                        dienstOrt = (TextView)findViewById(R.id.txtInputOrt);
                                        dienstOrt.setText(ort);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                    Log.e("onFailure", String.valueOf(statusCode));
                                }
                            });

                            //Holen der Fahrzeugnummer für den Dienst
                            RepositoryFahrzeug.getFahrzeugNummer(dienstid, dienstwochentag, new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    try {
                                        JSONObject obj = new JSONObject(new String(responseBody));
                                        JSONArray arr = obj.getJSONArray("data");
                                        JSONObject tmpobj = arr.getJSONObject(0);
                                        String busnummer = tmpobj.getString("busnummer");
                                        dienstFahrzeug = (TextView)findViewById(R.id.txtInputBus);
                                        dienstFahrzeug.setText(busnummer);
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

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("onFailure", String.valueOf(statusCode));
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("onFailure", String.valueOf(statusCode));
            }


        });

        //Holen der DienstId und des DienstWochentags, zum Anzeigen auf dem Bildschirm

        //Log.i("dienstid", String.valueOf(dienstlisteid));
        /*dienstId = (TextView)findViewById(R.id.txtInputDienstnummer);
        dienstId.setText(String.valueOf(id));

        dienstWochentag = (TextView)findViewById(R.id.txtInputDienstWochentag);
        Intent intent = getIntent();
        String id = intent.getStringExtra("userid");
        int mitarbeiterid = Integer.parseInt(id);
        Log.i("Userid:",String.valueOf(mitarbeiterid));*/
        //rdd.getDienstlisteId(mitarbeiterid);
    }
}
