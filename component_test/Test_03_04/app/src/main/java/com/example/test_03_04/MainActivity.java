package com.example.test_03_04;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] nums = {"one","two","three","four","five"};
    private int be_selected_item;   //被选中的item的编号（用于选中的item，改变背景颜色）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        连接listview
        listView = findViewById(R.id.listView_main);
//        创建simpleAdapter适配器
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.item,
                new String[]{"img", "num"}, new int[]{R.id.imageView, R.id.textView});
//        为listview配置simpleAdapter
        listView.setAdapter(adapter);
//        监听事件，为listview创建actionMode监听事件
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                mode.setTitle(listView.getCheckedItemCount() + " selected");   //更新选择数量
                if(!checked){    //未选中恢复原来颜色
                    listView.getChildAt(position).setBackgroundColor(Color.WHITE);
                }else {
                    listView.getChildAt(position).setBackgroundColor(Color.CYAN);
                }

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_main,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_item_delete_crime:
                        Toast.makeText(MainActivity.this, "点击了删除按钮", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                listView.clearChoices();
            }
        });
    }


    /**
     * 填充到listview的数据
     * @return
     */
    private List<? extends Map<String,?>> getData() {

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map;

        for(int i = 0 ; i < nums.length ; i++){
            map = new HashMap<>();
            map.put("img",R.drawable.sym_def_app_icon);
            map.put("num",nums[i]);
            list.add(map);
        }
        return list;
    }
}