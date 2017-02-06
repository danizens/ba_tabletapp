package de.hsos.pace.ba_tabletapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import de.hsos.pace.ba_tabletapp_respository.RepositoryMitarbeiter;

/**
 * Created by danielzens on 27.01.17.
 */

public class StartscreenActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);

        Intent intent = getIntent();
        String id = intent.getStringExtra("userid");
        int neueid = Integer.parseInt(id);
        Log.i("asdf",String.valueOf(neueid));
    }
}
