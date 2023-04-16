package com.example.list_view_nang_cao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterTraicay extends BaseAdapter {
    private Context context;
    private  int layout;
    private List <Fruit> fruitList;


    public adapterTraicay(Context context, int layout, List<Fruit> fruitList) {
        this.context = context;
        this.layout = layout;
        this.fruitList= fruitList;

    }
    public void ddListItem (List < Fruit> itemFruit){
        fruitList.addAll(itemFruit);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fruitList.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= layoutInflater.inflate(layout,null);
        // ánh xạ
        TextView txtNameFruit = (TextView) convertView.findViewById(R.id.txtNameFruit);
        TextView txtDescFruit= (TextView) convertView.findViewById(R.id.txtDescFruit);
        ImageView imgFruit= (ImageView) convertView.findViewById(R.id.imgFruit);
        // gán giá trị
        Fruit tracay= fruitList.get(position);
        txtNameFruit.setText(tracay.getNameFruit());
        txtDescFruit.setText(tracay.getDescFruit());
        imgFruit.setImageResource(tracay.getImgFruit());

        return convertView;
    }
}
