package de.hsos.pace.ba_tabletapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import de.hsos.pace.ba_tabletapp_respository.RepositoryDienstlisteDienst;
import de.hsos.pace.ba_tabletapp_respository.RepositoryFahrzeug;
import de.hsos.pace.ba_tabletapp_respository.RepositoryTaetigkeit;

/**
 * Created by danielzens on 27.01.17.
 */

public class StartscreenActivity extends AppCompatActivity{
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
        final String[] busnummer = new String[1];
        final String[] dienstid = {""};
        final String[] dienstwochentag = {""};

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
                            try {
                                JSONObject obj = new JSONObject(new String(responseBody));
                                if(obj.getString("data").length() == 0){
                                    Log.i("Keine Daten", "Keine Daten");
                                }else{
                                    JSONArray tmparr = obj.getJSONArray("data");
                                    JSONObject newjson = tmparr.getJSONObject(0);
                                    dienstwochentag[0] = newjson.getString("dienstwochentag");
                                    dienstid[0] = newjson.getString("dienstid");
                                    dienstId = (TextView)findViewById(R.id.txtInputDienstnummer);
                                    dienstId.setText(dienstid[0]);
                                    dienstWochentag = (TextView)findViewById(R.id.txtInputDienstWochentag);
                                    dienstWochentag.setText(dienstwochentag[0].trim());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            //Holen der Startzeit und des ersten Ortes der Haltestelle
                            RepositoryTaetigkeit.getTaetigkeitByDienstIdDienstwochentag(dienstid[0], dienstwochentag[0], new AsyncHttpResponseHandler() {
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
                            RepositoryFahrzeug.getFahrzeugNummer(dienstid[0], dienstwochentag[0], new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    try {
                                        JSONObject obj = new JSONObject(new String(responseBody));
                                        JSONArray arr = obj.getJSONArray("data");
                                        JSONObject tmpobj = arr.getJSONObject(0);
                                        busnummer[0] = tmpobj.getString("busnummer");
                                        dienstFahrzeug = (TextView)findViewById(R.id.txtInputBus);
                                        dienstFahrzeug.setText(busnummer[0]);
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

        //Verzweigen ins Menü, mit Intent der Busnummer und der Mitarbeiterid
        final Button button = (Button)findViewById(R.id.btnWeiter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartscreenActivity.this, MenuscreenActivity.class);
                intent.putExtra("userid", String.valueOf(mitarbeiterid));
                intent.putExtra("busnummer", String.valueOf(busnummer[0]));
                intent.putExtra("dienstid", String.valueOf(dienstid[0]));
                intent.putExtra("dienstwochentag", String.valueOf(dienstwochentag[0]));
                StartscreenActivity.this.startActivity(intent);
            }
        });
    }
}
