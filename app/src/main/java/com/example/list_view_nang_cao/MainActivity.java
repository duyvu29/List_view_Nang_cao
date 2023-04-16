package com.example.list_view_nang_cao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener   {
   ListView  listviewMain;
   private ArrayList <Fruit> fruitArrayList;
   private adapterTraicay adapterFruit;

   private SwipeRefreshLayout swipeRefreshLayout;

   private ProgressBar progressList;

   View fruitVieew;

   Boolean isLoangding = false;

   hander mhander;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ánh xạ
        mapping();
        // tạo list view
        setListView();
        // khởi tạo swpipeefresh
        swipeRefreshLayout.setOnRefreshListener(this);
        // sự kiện
        event();


    }
    private void mapping(){
        // set progressBar cho Load More
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        fruitVieew = layoutInflater.inflate(R.layout.progres_view,null);
        mhander = new hander();
        // ánh xạ
        listviewMain = (ListView) findViewById(R.id.listviewMain);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refeshLayout);
        progressList = findViewById(R.id.progressList);

    }
    private void event(){
        listviewMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               fruitArrayList.remove(position);
               adapterFruit.notifyDataSetChanged();
                return true;
            }
        });
        listviewMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view.getLastVisiblePosition() == totalItemCount -1 && isLoangding == false){
                    isLoangding = true;
                    Thread thread =new thread();
                    thread.start();

                }

            }
        });
    }

    private void setListView(){
        fruitArrayList= new ArrayList<Fruit>();

        fruitArrayList.add(new Fruit("Cam sành Long An","Ngọt như đường",R.drawable.cam));
        fruitArrayList.add(new Fruit("Dưa hấu Vĩnh Long","Mọng nước siêu ngon", R.drawable.duahau));
        fruitArrayList.add(new Fruit("Mận Hà Nội","Đặc sản miền Bắc",R.drawable.man));
        fruitArrayList.add(new Fruit("Đào lộn hộn","Chuyên chế biến và xuất khẩu", R.drawable.dieu));
        fruitArrayList.add(new Fruit("Chuối cau ","Hỗ trợ tiêu hóa",R.drawable.chuoi));
        fruitArrayList.add(new Fruit("Xoài cát vĩnh lộc","Mang hương vị quê hương", R.drawable.xoia));
        fruitArrayList.add(new Fruit("Nho Ninh Thuận","Ngọt dịu dàng",R.drawable.nho));

        adapterFruit = new adapterTraicay(MainActivity.this,R.layout.dong_trai_cay,fruitArrayList);
        listviewMain.setAdapter(adapterFruit);

    }


    @Override
    public void onRefresh() {
        fruitArrayList.add(new Fruit("Bơ Đak Nông","Ngon ngọt dẻo",R.drawable.bo));
        adapterFruit.notifyDataSetChanged();
        Handler handler = new Handler();
          handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                  swipeRefreshLayout.setRefreshing(false);
              }
          },3000);
    }

    public class hander extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){

                case 0:
                     listviewMain.addFooterView(fruitVieew);
                     break;

                case  1:
                    listviewMain.removeFooterView(fruitVieew);
                    adapterFruit.ddListItem((List<Fruit>) msg.obj);
                    isLoangding= false;
                    break;
            }
        }
    }
    public class thread extends Thread{
        @Override
        public void run() {
            mhander.sendEmptyMessage(0);
            ArrayList <Fruit> data = getMoreData();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mhander.obtainMessage(1,data);
            mhander.sendMessage(message);
        }
    }
    public ArrayList <Fruit> getMoreData (){
        ArrayList < Fruit> fruitArray = new ArrayList<>();
        fruitArray.add(new Fruit("Cam sành Long An","Ngọt như đường",R.drawable.cam));
        fruitArray.add(new Fruit("Dưa hấu Vĩnh Long","Mọng nước siêu ngon", R.drawable.duahau));
        fruitArray.add(new Fruit("Mận Hà Nội","Đặc sản miền Bắc",R.drawable.man));
        fruitArray.add(new Fruit("Đào lộn hộn","Chuyên chế biến và xuất khẩu", R.drawable.dieu));
        fruitArray.add(new Fruit("Chuối cau ","Hỗ trợ tiêu hóa",R.drawable.chuoi));
        fruitArray.add(new Fruit("Xoài cát vĩnh lộc","Mang hương vị quê hương", R.drawable.xoia));
        fruitArray.add(new Fruit("Nho Ninh Thuận","Ngọt dịu dàng",R.drawable.nho));
        return fruitArray;

    }

}