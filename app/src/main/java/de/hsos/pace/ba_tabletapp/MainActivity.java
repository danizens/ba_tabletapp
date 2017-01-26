package de.hsos.pace.ba_tabletapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import de.hsos.pace.ba_tabletapp_respository.RepositoryMitarbeiter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button button = (Button) findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Log.i("Username: ", getUsername());
                //Log.i("Password: ", getPassword());
                RepositoryMitarbeiter rm = new RepositoryMitarbeiter();
                rm.getMitarbeiterIdByUsernamePassword(getUsername(), getPassword());
                //Log.i("neueid: ", String.valueOf(rm.getId()));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getUsername(){
        EditText text = (EditText)findViewById(R.id.txtusername);
        String username = text.getText().toString();
        return username;
    }

    public String getPassword(){
        EditText text = (EditText)findViewById(R.id.txtpassword);
        String password = text.getText().toString();
        return password;
    }
}
