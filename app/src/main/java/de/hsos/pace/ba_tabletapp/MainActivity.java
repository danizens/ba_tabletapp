package de.hsos.pace.ba_tabletapp;

/* Verwendete Quellen
** http://stackoverflow.com/questions/19465049/changing-api-level-android-studio
** https://developer.android.com/reference/android/widget/RelativeLayout.LayoutParams.html
** https://developer.android.com/reference/android/widget/GridLayout.html
** https://developer.android.com/guide/topics/ui/layout/linear.html
** https://developer.android.com/reference/android/widget/HorizontalScrollView.html
** http://stackoverflow.com/questions/18656949/how-to-implement-horizontalscrollview-like-gallery
** http://stackoverflow.com/questions/4368047/java-rest-client-api-for-android
** http://programmerguru.com/android-tutorial/android-restful-webservice-tutorial-how-to-call-restful-webservice-in-android-part-3/
** https://developer.android.com/reference/android/content/Context.html
** http://stackoverflow.com/questions/10231309/android-button-onclick
** http://stackoverflow.com/questions/12117023/android-icon-sizes
** https://developer.android.com/guide/components/activities/activity-lifecycle.html
** http://loopj.com/android-async-http/
** https://developer.android.com/reference/android/util/JsonReader.html
*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.hsos.pace.ba_tabletapp_respository.RepositoryMitarbeiter;
import de.hsos.pace.de.hsos.pace.ba_tabletapp_model.User;

//Ermitteln der Logindaten eines Benutzers
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
                //Ermitteln der eingegebenen Daten und setzen des Users
                loginuser.setUsername(getUsername());
                loginuser.setPasswort(getPassword());
                //Aufruf ins Repository um die eingegebenen Daten mit denen aus der DB zu identifzieren
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

    //Wird aufgerufen wenn zum LoginScreen zurückgekehrt wird.
    //Clearen der Daten der Textfelder
    @Override
    protected void onResume(){
        super.onResume();
        EditText username = (EditText)findViewById(R.id.txtusername);
        username.setText("");
        EditText password = (EditText)findViewById(R.id.txtpassword);
        password.setText("");
        Log.i("hallo","onResume");
    }
}
