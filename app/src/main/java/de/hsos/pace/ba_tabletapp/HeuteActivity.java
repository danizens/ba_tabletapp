package de.hsos.pace.ba_tabletapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import de.hsos.pace.ba_tabletapp_respository.RepositoryTaetigkeit;

/**
 * Created by danielzens on 08.02.17.
 */

public class HeuteActivity extends AppCompatActivity{
    //Ermitteln der Dienstinformationen, per AsyncHttpClient
    //Zu ermittelnde Daten: Bezeichnung, Status1, Startzeit, Ort, Endzeit, Status2
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heute);
        Intent intent = getIntent();
        String userid = intent.getStringExtra("userid");
        String busnummer = intent.getStringExtra("busnummer");
        String dienstid = intent.getStringExtra("dienstid");
        String dienstwochentag = intent.getStringExtra("dienstwochentag");
        //Ermittelt die einzelnen Tätigkeiten eines Dienstes durch die Dienstid und den Dienstwochentag
        //Aufruf ins Repository
        RepositoryTaetigkeit.getTaetigkeitByDienstIdDienstwochentag(dienstid, dienstwochentag, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(new String(responseBody));
                    JSONArray arr = obj.getJSONArray("data");

                    //Log.i("lengtharr", String.valueOf(arr.length()));
                    int rowcount = 2;
                    int columncount = arr.length() * 6;

                    GridLayout glayout = (GridLayout)findViewById(R.id.layoutgrid);
                    glayout.setRowCount(rowcount);
                    glayout.setColumnCount(columncount);

                    //Setzen der Überschriften im GRID-Layout
                    for(int i = 0; i < arr.length(); i++){
                        TextView dienstname = new TextView(HeuteActivity.this);
                        dienstname.setTextSize(50);
                        dienstname.setText(R.string.actheutename);

                        TextView status1 = new TextView(HeuteActivity.this);
                        status1.setTextSize(50);
                        status1.setText(R.string.actheutestatus1);

                        TextView startzeit = new TextView(HeuteActivity.this);
                        startzeit.setTextSize(50);
                        startzeit.setText(R.string.actheutestartzeit);

                        TextView ort = new TextView(HeuteActivity.this);
                        ort.setTextSize(50);
                        ort.setText(R.string.actheuteortkuerzel);

                        TextView endzeit = new TextView(HeuteActivity.this);
                        endzeit.setTextSize(50);
                        endzeit.setText(R.string.actheuteendzeit);

                        TextView status2 = new TextView(HeuteActivity.this);
                        status2.setTextSize(50);
                        status2.setText(R.string.actheutestatus2);

                        GridLayout.LayoutParams layoutName = new GridLayout.LayoutParams();
                        layoutName.rightMargin = 40;

                        GridLayout.LayoutParams layoutStatus1 = new GridLayout.LayoutParams();
                        layoutStatus1.rightMargin = 40;

                        GridLayout.LayoutParams layoutStartzeit = new GridLayout.LayoutParams();
                        layoutStartzeit.rightMargin = 40;

                        GridLayout.LayoutParams layoutOrt = new GridLayout.LayoutParams();
                        layoutOrt.rightMargin = 40;

                        GridLayout.LayoutParams layoutEndzeit = new GridLayout.LayoutParams();
                        layoutEndzeit.rightMargin = 40;

                        GridLayout.LayoutParams layoutStatus2 = new GridLayout.LayoutParams();
                        layoutStatus2.rightMargin = 40;


                        glayout.addView(dienstname, layoutName);
                        glayout.addView(status1, layoutStatus1);
                        glayout.addView(startzeit, layoutStartzeit);
                        glayout.addView(ort, layoutOrt);
                        glayout.addView(endzeit, layoutEndzeit);
                        glayout.addView(status2, layoutStatus2);

                    }

                    //Setzen der gelesen Werte der erhaltenen JSON-Objekte
                    for(int i = 0; i < arr.length(); i++){
                        JSONObject newobj = arr.getJSONObject(i);

                        TextView dienstnameinp = new TextView(HeuteActivity.this);
                        dienstnameinp.setTextSize(40);
                        if(newobj.getString("name").trim().equals("Fuellzeit")){
                            dienstnameinp.setText("|Füllzeit");
                        }else{
                            dienstnameinp.setText("|"+newobj.getString("name").trim());
                        }

                        TextView status1inp = new TextView(HeuteActivity.this);
                        status1inp.setTextSize(40);
                        status1inp.setText(newobj.getString("status1").trim());

                        TextView startzeitinp = new TextView(HeuteActivity.this);
                        startzeitinp.setTextSize(40);
                        startzeitinp.setText(newobj.getString("startzeit").trim());

                        TextView ortinp = new TextView(HeuteActivity.this);
                        ortinp.setTextSize(40);
                        ortinp.setText(newobj.getString("ort_kuerzel").trim());

                        TextView endzeitinp = new TextView(HeuteActivity.this);
                        endzeitinp.setTextSize(40);
                        endzeitinp.setText(newobj.getString("endzeit").trim());

                        TextView status2inp = new TextView(HeuteActivity.this);
                        status2inp.setTextSize(40);
                        status2inp.setText(newobj.getString("status2").trim());

                        GridLayout.LayoutParams layoutNameInp = new GridLayout.LayoutParams();
                        layoutNameInp.rightMargin = 30;
                        GridLayout.LayoutParams layoutStatus1Inp = new GridLayout.LayoutParams();
                        layoutStatus1Inp.rightMargin = 30;
                        GridLayout.LayoutParams layoutStartzeitInp = new GridLayout.LayoutParams();
                        layoutStartzeitInp.rightMargin = 30;
                        GridLayout.LayoutParams layoutOrtInp = new GridLayout.LayoutParams();
                        layoutOrtInp.rightMargin = 30;
                        GridLayout.LayoutParams layoutEndzeitInp = new GridLayout.LayoutParams();
                        layoutEndzeitInp.rightMargin = 30;
                        GridLayout.LayoutParams layoutStatus2Inp = new GridLayout.LayoutParams();
                        layoutStatus2Inp.rightMargin = 30;

                        glayout.addView(dienstnameinp, layoutNameInp);
                        glayout.addView(status1inp, layoutStatus1Inp);
                        glayout.addView(startzeitinp, layoutStartzeitInp);
                        glayout.addView(ortinp, layoutOrtInp);
                        glayout.addView(endzeitinp, layoutEndzeitInp);
                        glayout.addView(status2inp, layoutStatus2Inp);

                    }
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
