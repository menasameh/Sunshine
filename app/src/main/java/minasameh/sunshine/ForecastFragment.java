package minasameh.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ForecastFragment extends Fragment {

    private ArrayAdapter<String> adapter;
    private String url = "api.openweathermap.org/data/2.5/forecast/daily?q=Alexandria,EG&APPID=b55e3c1c7aa050f6fae3829be574f2e8&units=metric&cnt=7";

    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.forecast_fragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_refresh:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.fragment_main, container, false);

        String[] array = {"today - sunny - 88/63", "tomorrow - foggy - 88/63", "weds - cloudy - 88/63"};

        List<String> list = new ArrayList<>(Arrays.asList(array));

        adapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textView,
                list);

        ListView lv = (ListView)rootView.findViewById(R.id.listview_forecast);

        lv.setAdapter(adapter);

        new FetchWeatherTask().execute(url);

        return rootView;
    }
}
