package de.hsos.pace.ba_tabletapp_respository;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


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

    //Asynchroner Aufruf der REST-Schnittstelle
    public static void getDienstListeId(AsyncHttpResponseHandler handler){
        String datum = "26.10.";
        mClient.get(conn_str + "dienstliste/" + datum, handler);
    }

    //Asynchroner Aufruf der REST-Schnittstelle
    public static void getDienstNummerByIdDatum(int mitarbeiterid, int dienstlisteid, AsyncHttpResponseHandler handler){
        mClient.get(conn_str + "dienstlistedienst/id/" + mitarbeiterid + "/" + dienstlisteid, handler);
    }
}
