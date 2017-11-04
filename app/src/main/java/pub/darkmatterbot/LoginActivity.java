package pub.darkmatterbot;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import pub.libogame.LibOgame;
import pub.libogame.LibOgameException;
import pub.libogame.ReturnCode;

public class LoginActivity extends Activity {
    Button _loginButton;
    Spinner _server_dropdown;
    EditText _username;
    EditText _password;
    LibOgame _libOgame;
    ReturnCode _setServerReturnCode = ReturnCode.REFUSED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _loginButton     = (Button) findViewById(R.id.button_login);
        _server_dropdown = (Spinner) findViewById(R.id.server);
        _username        = (EditText) findViewById(R.id.username);
        _password        = (EditText) findViewById(R.id.password);
        _libOgame        = LibOgameHandler.libOgame;

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReturnCode u_rc = _libOgame.auth.setUsername( _username.getText().toString() );
                ReturnCode p_rc = _libOgame.auth.setPassword( _password.getText().toString() );

                if( _setServerReturnCode != ReturnCode.SUCCESS ) {
                    Toast.makeText(getApplicationContext(), "No Universe Selected!",
                            Toast.LENGTH_SHORT).show();
                }
                else if( u_rc != ReturnCode.SUCCESS) {
                    Toast.makeText(getApplicationContext(), "No Username specified!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(p_rc != ReturnCode.SUCCESS ) {
                    Toast.makeText(getApplicationContext(), "No Password specified!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Logging in...",
                            Toast.LENGTH_LONG).show();
                    new LoginTask().execute( (Void[]) null );
                }
            }
        });

        //Get Server List
        String[] stringArray = new String[_libOgame.servers.count()+1];
        stringArray[0] = "Select Universe";
        for( int i = 1; i < stringArray.length; i++ )
            stringArray[i] = _libOgame.servers.getName(i-1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, stringArray);
        //Hook our ServerList Adapter into the spinner.
        _server_dropdown.setAdapter(adapter);
        //When a server name is selected, set the serverIndex in libogame.auth to that postion:
        _server_dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _setServerReturnCode = _libOgame.auth.setServer(i-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                _setServerReturnCode = ReturnCode.REFUSED;
            }
        });

        _username.requestFocus();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private class LoginTask extends AsyncTask<Void, Void, ReturnCode>
    {
        @Override
        public ReturnCode doInBackground(Void[] nothing)
        {
            try {
                return _libOgame.auth.login();
            }
            catch (LibOgameException e) {
                Log.println(Log.ERROR,"LoginActivity", e.toString());
                return ReturnCode.REFUSED;
            }
        }

        @Override
        public void onPostExecute(ReturnCode rc)
        {
            if( rc == ReturnCode.SUCCESS ) {
                if( _libOgame.auth.isAuthenticated() ) startMainActivity();
                else Toast.makeText(getApplicationContext(), "Login Failed!",
                        Toast.LENGTH_SHORT).show();
            }
            else finish();
        }
    }
}
