package minasameh.sunshine;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);


        // Fetch and store ShareActionProvider

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

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        private static String hashtag = "#SunshineAPP";
        private String message;


        public PlaceholderFragment() {
            setHasOptionsMenu(true);
        }

        protected Intent createIntent(){
            return new Intent(Intent.ACTION_SEND)
            .setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, message+" "+hashtag);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            TextView tv = (TextView) rootView.findViewById(R.id.weather_text);
            message = getActivity().getIntent().getStringExtra(ForecastFragment.forecastString);
            if(message != null){
                tv.setText(message);

                }
            else{
                tv.setText("no data received");
            }

            return rootView;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

            MenuItem item = menu.findItem(R.id.menu_item_share);

            try{
                ShareActionProvider actionProvider =
                        (ShareActionProvider) MenuItemCompat.getActionProvider(item);
                actionProvider.setShareIntent(createIntent());
            }catch(Exception e){
                Toast.makeText(getActivity(), "error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}