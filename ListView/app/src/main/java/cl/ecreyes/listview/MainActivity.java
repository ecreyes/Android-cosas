package cl.ecreyes.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView myListView;
    private List<String> nombres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = findViewById(R.id.my_Listview);

        nombres = new ArrayList<String>();
        nombres.add("Eduardo");
        nombres.add("Carlos");
        nombres.add("Ignacio");
        nombres.add("Eduardo");
        nombres.add("Carlos");
        nombres.add("Ignacio");
        nombres.add("Eduardo");
        nombres.add("Carlos");
        nombres.add("Ignacio");
        nombres.add("Eduardo");
        nombres.add("Carlos");
        nombres.add("Ignacio");
        nombres.add("Eduardo");
        nombres.add("Carlos");
        nombres.add("Ignacio");
        nombres.add("Eduardo");
        nombres.add("Carlos");
        nombres.add("Ignacio");

        MainActivityAdapter adapter = new MainActivityAdapter(this,R.layout.listview_activity_main,nombres);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, nombres.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
