package cl.ecreyes.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

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