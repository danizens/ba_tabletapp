package de.hsos.pace.ba_tabletapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import de.hsos.pace.ba_tabletapp_respository.RepositoryDienstlisteDienst;

/**
 * Created by danielzens on 27.01.17.
 */

public class StartscreenActivity extends AppCompatActivity{
    //RepositoryDienstlisteDienst rdd = new RepositoryDienstlisteDienst();
    TextView dienstId;
    TextView dienstWochentag;
    Intent intent = getIntent();
    String id = intent.getStringExtra("userid");
    int mitarbeiterid = Integer.parseInt(id);
    int dienstlisteid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);

        //Holen der Dienstlistennummer
        RepositoryDienstlisteDienst.getDienstListeId(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject obj = new JSONObject(new String(responseBody));
                    JSONObject tmpobj = obj.getJSONObject("data");
                    dienstlisteid = tmpobj.getInt("id");
                    Log.i("asdf", String.valueOf(dienstlisteid));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        //Holen der DienstId und des DienstWochentags, zum Anzeigen auf dem Bildschirm
        RepositoryDienstlisteDienst.getDienstNummerByIdDatum(mitarbeiterid, dienstlisteid, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

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
