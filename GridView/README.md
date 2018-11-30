# GridView
Lo primero que hay que hacer es crear un gridview en el layout de la actividad.
```xml
<GridView
    android:id="@+id/gridView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:numColumns="auto_fit"
    android:verticalSpacing="10dp"
    android:horizontalSpacing="10dp"
    android:columnWidth="90dp"
    android:stretchMode="columnWidth"
    android:gravity="center"
    />
```
Notar que como el columnwidth es de 90dp, va a tomar 90dp del layout que se este renderizando.
luego hay que ir a la actividad y crear el atribudo de gridview para hacer referencia:
```java
public class MainActivity extends AppCompatActivity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);
    }
}
```
Ahora al igual que en el Listview se creará una lista con nombres:
```java
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
    }
}
```
Una vez hecho esto hay que hacer el layout que se va a utilizar para renderizar, es decir el que
se va a estar repitiendo constantemente:
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="120dp"
    android:orientation="vertical"
    >

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@android:drawable/star_big_on"
        android:layout_gravity="center_horizontal"
        android:layout_margin="20dp"
        />
    <TextView
        android:id="@+id/tv_nombre"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Nombre"
        android:textSize="16dp"
        android:layout_gravity="center_horizontal"
        />

</LinearLayout>
```
ahora hay que crear un adaptador igual como se hizo con el Listview (ver readme del listview).
```java
public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<String> nombres;

    public MyAdapter(Context context,int layout,List<String> nombres) {
        this.context = context;
        this.layout = layout;
        this.nombres = nombres;
    }

    @Override
    public int getCount() {
        return nombres.size();
    }

    @Override
    public Object getItem(int position) {
        return nombres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout,null);
            holder = new ViewHolder();
            holder.myTextView = convertView.findViewById(R.id.tv_nombre);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String nombreActual = this.nombres.get(position);
        holder.myTextView.setText(nombreActual);
        return convertView;
    }

    static class ViewHolder{
        private TextView myTextView;
    }
}
```
Ahora en la actividad solo queda crear el adaptador y pasarlo al gridview
```java
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
    }
}
```
Ademas tambien funciona lo del click como en ListView:
```java
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
```