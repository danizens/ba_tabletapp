package de.hsos.pace.ba_tabletapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by danielzens on 08.02.17.
 */

public class MenuscreenActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuscreen);
        Intent intent = getIntent();
        final String userid = intent.getStringExtra("userid");
        final String busnummer = intent.getStringExtra("busnummer");
        final String dienstid = intent.getStringExtra("dienstid");
        final String dienstwochentag = intent.getStringExtra("dienstwochentag");

        final ImageButton auswertung = (ImageButton)findViewById(R.id.imgbtnHeute);
        auswertung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuscreenActivity.this, HeuteActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("busnummer", busnummer);
                intent.putExtra("dienstid", dienstid);
                intent.putExtra("dienstwochentag", dienstwochentag);
                MenuscreenActivity.this.startActivity(intent);
            }
        });

        //Log.i("mitarbeiterid", mitarbeiterid);
        //Log.i("busnummer", busnummer);
    }
}
