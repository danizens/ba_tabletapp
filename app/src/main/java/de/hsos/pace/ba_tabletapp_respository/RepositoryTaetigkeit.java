package de.hsos.pace.ba_tabletapp_respository;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by danielzens on 07.02.17.
 */

public class RepositoryTaetigkeit {
    private static final String conn_str = "http://131.173.111.8/ba/api/";
    private static AsyncHttpClient mClient = new AsyncHttpClient();

    //Asynchroner Aufruf der REST-Schnittstelle
    public static void getTaetigkeitByDienstIdDienstwochentag(String dienstid, String dienstwochentag, AsyncHttpResponseHandler handler){
        mClient.get(conn_str + "taetigkeit/dienst/" + dienstwochentag + "/" + dienstid, handler);
    }
}
