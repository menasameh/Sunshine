package minasameh.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivityFragment extends Fragment {

    private ArrayAdapter<String> adapter;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.fragment_main, container, false);

        String[] array = {"today - sunny - 88/63", "tomorrow - foggy - 88/63", "weds - cloudy - 88/63"};

        List<String> list = new ArrayList<String>(Arrays.asList(array));

        adapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textView,
                list);

        ListView lv = (ListView)rootView.findViewById(R.id.listview_forecast);

        lv.setAdapter(adapter);

        return rootView;
    }
}
