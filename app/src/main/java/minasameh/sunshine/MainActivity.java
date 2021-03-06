package minasameh.sunshine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "openning main activity", Toast.LENGTH_SHORT).show();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        else if(id == R.id.action_view_on_map){
            showMap();
        }


        return super.onOptionsItemSelected(item);
    }




    private void showMap() {

        SharedPreferences settings = getSharedPreferences(
                SettingsActivity.PREFS_NAME,
                Context.MODE_PRIVATE);
        String postal = settings.getString(
                getString(R.string.postal_code_key),
                getString(R.string.postal_code_default));

        Uri location = Uri.parse("geo:0,0").buildUpon()
                            .appendQueryParameter("q", postal).build();

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(location);

        if(i.resolveActivity(getPackageManager())!=null){
            startActivity(i);
        }
    }
}
