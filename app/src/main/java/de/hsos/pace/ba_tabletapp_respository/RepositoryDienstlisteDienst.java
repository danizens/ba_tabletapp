package de.hsos.pace.ba_tabletapp_respository;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;
import de.hsos.pace.ba_tabletapp.R;
import de.hsos.pace.ba_tabletapp.StartscreenActivity;

import static android.R.attr.id;

/**
 * Created by danielzens on 06.02.17.
 */

public class RepositoryDienstlisteDienst{
    private static final String conn_str = "http://131.173.111.8/ba/api/";
    //private String datum = "26.10.";
    private static AsyncHttpClient mClient = new AsyncHttpClient();

    public static void getDienstListeId(AsyncHttpResponseHandler handler){
        String datum = "26.10.";
        mClient.get(conn_str + "dienstliste/" + datum, handler);
    }

    public static void getDienstNummerByIdDatum(int mitarbeiterid, int dienstlisteid, AsyncHttpResponseHandler handler){
        mClient.get(conn_str + "dienstlistedienst/id/" + mitarbeiterid + "/" + dienstlisteid, handler);
    }

    /*
        Datum wird für den Testfall festgelegt. Im Konzept zwei Möglichkeiten berücksichtigen. Entweder
        wählt der der Anwender, per Dropdown, selbst ein Datum aus oder das System bereitet die darzustellenden
        Informationen für das aktuelle Datum auf.
    */

    /*public void getDienstlisteId(final int mitarbeiterid){
        String url = conn_str + "dienstliste/"+datum;
        Log.i("URL: ", url);
        final JSONArray[] arr = new JSONArray[1];
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //log.i("id: ", response.toString());
                try {
                    if(response.getJSONObject("data").length() == 0){

                    }else{
                        JSONObject obj = response.getJSONObject("data");
                        int dienstlisteid = obj.getInt("id");
                        arr[0] = getDienstNummerByIdDatum(dienstlisteid, mitarbeiterid);
                        Log.i("json", String.valueOf(arr[0]));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JSONArray getDienstNummerByIdDatum(int dienstlisteid, int mitarbeiterid){
        //Log.i("dienstlisteid", String.valueOf(dienstlisteid));
        String url = conn_str + "dienstlistedienst/id/" + mitarbeiterid + "/" + dienstlisteid;
        final JSONArray[] arr = new JSONArray[1];
        Log.i("URL: ", url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //log.i("id: ", response.toString());
                try {
                    if(response.getJSONArray("data").length() == 0){

                    }else{
                        arr[0] = response.getJSONArray("data");
                        Log.i("asdf", String.valueOf(arr[0]));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return arr[0];
    }

    /*public void getDienstNummerByIdDatum(int dienstlisteid, int mitarbeiterid){
        //Log.i("dienstlisteid", String.valueOf(dienstlisteid));
        String url = conn_str + "dienstlistedienst/id/" + mitarbeiterid + "/" + dienstlisteid;
        Log.i("URL: ", url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //log.i("id: ", response.toString());
                try {
                    if(response.getJSONArray("data").length() == 0){

                    }else{
                        StartscreenActivity sa = new StartscreenActivity();
                        JSONArray arr = response.getJSONArray("data");
                        int did = arr.getJSONObject(0).getInt("dienstid");
                        String tag = arr.getJSONObject(0).getString("dienstwochentag");
                        //Log.i("dienstid", String.valueOf(id));
                        //Log.i("dienstwochentag", tag.trim());
                        sa.writeDienstnummerToScreen(did, tag);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}
