package pub.darkmatterbot;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import pub.libogame.LibOgameException;

class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new InitializeLibOgameInstanceTask().execute((Void[]) null);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private class InitializeLibOgameInstanceTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        public Void doInBackground(Void[] nothing)
        {
            try {
                LibOgameHandler.initialize();
            } catch (LibOgameException e) {
                Log.println(Log.ERROR,"StartActivity", e.toString()); }

            return null;
        }

        @Override
        public void onPostExecute(Void nothing)
        {
            if( LibOgameHandler.libOgame != null ) startLoginActivity();
            else finish();
        }
    }
}
