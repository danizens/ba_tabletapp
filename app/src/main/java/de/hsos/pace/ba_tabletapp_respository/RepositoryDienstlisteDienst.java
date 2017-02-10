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

    /*
        Datum wird für den Testfall festgelegt. Im Konzept zwei Möglichkeiten berücksichtigen. Entweder
        wählt der der Anwender, per Dropdown, selbst ein Datum aus oder das System bereitet die darzustellenden
        Informationen für das aktuelle Datum auf.
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
}
