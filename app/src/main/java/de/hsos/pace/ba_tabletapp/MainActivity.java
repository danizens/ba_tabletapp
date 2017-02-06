package de.hsos.pace.ba_tabletapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hsos.pace.ba_tabletapp_respository.RepositoryMitarbeiter;
import de.hsos.pace.de.hsos.pace.ba_tabletapp_model.User;

public class MainActivity extends AppCompatActivity {
    private int loginid = 0;
    private RepositoryMitarbeiter rm = new RepositoryMitarbeiter();
    User loginuser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                loginuser.setUsername(getUsername());
                loginuser.setPasswort(getPassword());
                rm.getMitarbeiterIdByUsernamePassword(loginuser, MainActivity.this);
            }
        });
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
