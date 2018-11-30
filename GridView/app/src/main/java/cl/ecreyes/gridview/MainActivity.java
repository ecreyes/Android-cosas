package cl.ecreyes.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private List<String> nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);
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
        nombres.add("Eduardo");
        nombres.add("Carlos");
        nombres.add("Ignacio");

        MyAdapter adapter = new MyAdapter(this,R.layout.grid_item,nombres);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, nombres.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
