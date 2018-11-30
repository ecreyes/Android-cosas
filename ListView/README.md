# Guía para crear un ListView.

Para empezar esta guía se hará primeramente el Listview más basico, luego un personalizado y por 
último un patrón para mejorar el Listview.

## Creando un Listview básico.
Lo primero que hay que hacer es ir al layout de la actividad en la cual se desea implementar un listview,
luego se crea un elemento Listview de la siguiente forma:
```xml
<ListView
    android:id="@+id/my_Listview"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
</ListView>
```
Ahora hay que crear un atributo de tipo Listview en la actividad y referenciarlo al id, quedando así:
```java
public class MainActivity extends AppCompatActivity {
    private ListView myListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = findViewById(R.id.my_Listview);
    }
}
```
ya tenemos el ListView referenciado al elemento del layout, ahora los elementos que se listarán
seran del tipo String, entonces se creará una lista con nombres:
```java
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
    }
}
```
Ahora falta implementar el adaptador, esto permite hacer la conexión del ListView con los datos que tendra
y asociarlos al layout.
```java
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        myListView.setAdapter(adapter);
    }
}
```
Los parametros pasados al adaptador son el contexto,el layout que se va renderizar que en este caso
es uno que trae android por defecto (se refiere a los elementos a renderizar) y el ArrayList con los nombres.
Luego al ListView se le asiga el adaptador, con esto ya estaria terminado.

Actualmente solo se muestra el listado de los datos, pero si queremos agregar interacción, por ejemplo
el click, los Listview contienen un método para realizar esto de forma facil, esto quedaria asi:
```java
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        myListView.setAdapter(adapter);
        
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, nombres.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```
El método que se utiliza es `setOnItemClickListener` y dentro el parámetro que interesa es el position
que permite obtener la posición del arreglo en que se hizo click. Recordar que dentro de ese método
en vez de mostrar un Toast se podria hacer un Intent y cargar una nueva actividad.

## ListView personalizado
Supongamos que ahora se quiere hacer un layout más personalizado y no solamente un listado
de string, por ejemplo que contenga ahora una imagen al lado izquierdo y el nombre al lado derecho.

Lo primero que habría que hacer es un nuevo layout, será algo básico de tipo LinearLayout:
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="70dp"
    android:orientation="horizontal"
    >

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@android:drawable/star_big_on"
        android:layout_gravity="center_vertical"
        android:layout_margin="25dp"
        />

    <TextView
        android:id="@+id/tv_nombre"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Nombre"
        android:textSize="16dp"
        android:layout_gravity="center_vertical"
        />
</LinearLayout>
```
Ahora hay que crear una nueva clase para el adaptador, lo importante es que la clase se extienda
de `BaseAdapter` y hay que implementar los 4 métodos que trae.
```java
public class MainActivityAdapter extends BaseAdapter {
    private Context context;
    private List<String> nombres;
    private int layout;
    
    public MainActivityAdapter(Context context,int layout,List<String> nombres){
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
        return null;
    }
}
```
Se agregaron tres atributos que como se recuerda anteriormente, son los que se pasaron al adapter anterior
que ya no se utilizará, es decir este nuevo adapter recivira el contexto, el layout a renderizar
y el arreglo de elementos. Luego se inicializo con un constructor de la clase y de los cuatro métodos
que se implementaron, los primeros tres son bastante intuitivos asi que el más importante es el último
ese hará toda la magia.

```java
public class MainActivityAdapter extends BaseAdapter {
    private Context context;
    private List<String> nombres;
    private int layout;

    public MainActivityAdapter(Context context,int layout,List<String> nombres){
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
        View v = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(this.layout,null);
        TextView myTextView = v.findViewById(R.id.tv_nombre);
        String nombre = this.nombres.get(position);
        myTextView.setText(nombre);        
        return v;
    }
}
```
Lo que se hace es pasar el contexto a un layoutInflater , luego inflar y asignar el layout que se
creo (el que se va a repetir varias veces o renderizar), se coloco con this porque se supone que al crear
el adaptador se pasaron los parámetros al constructor. Luego se crea una variable tipo Textview, esta
se crea porque el layout tiene una imagen y un textview pero solo vamos a modificar el textview,
si fueran más se tendria que crear mas variables, a esta variable se le hace la referencia con su id.
Se obtiene el nombre del arreglo con la posicion y se asigna al textview, finalmente se retorna la view.

La actividad quedaría de la siguiente forma con el adaptador personalizado:
```java
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
```

## Implementando el patrón ViewHolder.
Este patrón se utiliza porque el adaptador que se creó anteriormente tiene un problema, siempre
va a ir buscar el id con el R y esto puede resultar costoso si el listado aumenta además de estar
inflando a cada rato.
El código queda de la siguiente manera:
```java
public class MainActivityAdapter extends BaseAdapter {
    private Context context;
    private List<String> nombres;
    private int layout;

    public MainActivityAdapter(Context context,int layout,List<String> nombres){
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
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout,null);
            holder = new ViewHolder();
            holder.nombreTextView= convertView.findViewById(R.id.tv_nombre);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String nombre = this.nombres.get(position);
        holder.nombreTextView.setText(nombre);
        return convertView;
    }

    static class ViewHolder{
        private TextView nombreTextView;
    }
}
```