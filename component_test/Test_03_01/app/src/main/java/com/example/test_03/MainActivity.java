package com.example.test_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    int[] imageArr = {R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};
    String[] titleArr = {"lion","tiger","monkey","dog","cat","elephant"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //连接listview
        listView = findViewById(R.id._dynamic);
        //创建SimpleAdapter适配器
        /**
         * public SimpleAdapter ( Context context,List<?extends Map<String,?>> data,int resource,String[] form,int[] to)
         * context	SimpleAdapter关联的View的运行环境
         * data	一个Map组成的List。列表种的每个条目对应列表中的一行，每个Map中应该包含from参数中指定的键
         * resource	定义列表项的布局文件的资源id
         * from	被添加到Map映射上的键名
         * to	将绑定数据的视图的Id和from参数对应
         */
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,getData(),
                R.layout.item1,new String[]{"describe","img"},new int[]{R.id.textView,R.id.imageView2});
        //为listView添加适配器
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,titleArr[position],Toast.LENGTH_SHORT).show();
            }
        });

    }

    private List<? extends Map<String,?>> getData() {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map ;

        for(int i = 0 ; i < imageArr.length ; i++){
            map = new HashMap<>();
            map.put("img",imageArr[i]);
            map.put("describe",titleArr[i]);
            list.add(map);
        }

        return list;
    }
}