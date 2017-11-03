package pub.darkmatterbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

class LoginActivity extends Activity {
    Button _loginButton;
    Spinner _server_dropdown;
    EditText _username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _loginButton     = (Button) findViewById(R.id.button_login);
        _server_dropdown = (Spinner) findViewById(R.id.server);
        _username        = (EditText) findViewById(R.id.username);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMainActivity();
            }
        });

        String[] stringArray = new String[] {
                "Universe 1", "Universe 2", "Universe 3", "Universe 4"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringArray);

        _server_dropdown.setAdapter(adapter);
        _username.requestFocus();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
