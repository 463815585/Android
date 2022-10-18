# Android界面组件实验

## 实验一:Android ListView的用法

- 实现思路

  往ListView加入SimpleAdapter适配器构建视图，

  public SimpleAdapter ( Context context,List<?extends Map<String,?>> data,int resource,String[] form,int[] to)是SimpleAdapter适配器的构造函数，其中----

  ```
  context SimpleAdapter关联的View的运行环境
  * data 一个Map组成的List。列表种的每个条目对应列表中的一行，每个Map中应该包含from参数中指定的键
  * resource 定义列表项的布局文件的资源id
  * from 被添加到Map映射上的键名
  * to   将绑定数据的视图的Id和from参数对应
  ```

  再利用ListView中的setOnItemClickListener()方法监听listView的监听操作。

- 代码

  1. MainActivity.java

     ```java
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
     ```

  2. activity_main.xml

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto"
         xmlns:tools="http://schemas.android.com/tools"
         android:layout_width="wrap_content"
         android:layout_height="match_parent"
         tools:context=".MainActivity">
     
         <ListView
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             app:layout_constraintBottom_toBottomOf="parent"
             app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toStartOf="parent"
             app:layout_constraintTop_toTopOf="parent"
             android:id="@+id/_dynamic"
             android:listSelector="#666"/>
     </androidx.constraintlayout.widget.ConstraintLayout>
     ```

  3. item.xml

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <!-- 相对布局才可实现效果 -->
     <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto"
         xmlns:tools="http://schemas.android.com/tools"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:orientation="vertical"
         android:stretchColumns="1">
     
         <TextView
             android:id="@+id/textView"
             android:layout_width="match_parent"
             android:layout_height="50dp"
             android:gravity="center_vertical"
             android:paddingLeft="10dp"
             android:text="TextView"
             android:textSize="24sp" />
     
         <!-- 靠右android:layout_alignParentRight="true" -->
         <ImageView
             android:id="@+id/imageView2"
             android:layout_width="50dp"
             android:layout_height="50dp"
             android:layout_marginRight="10dp"
             android:layout_alignParentRight="true"
             app:srcCompat="@drawable/cat"/>
     
     </RelativeLayout>
     ```

- 实验截图

  ![](https://github.com/463815585/Android/blob/main/component_test/img/ListView_result.jpg)

## 实验二：创建自定义布局的AlertDialog

- 实现思路

  自定义对话框，利用new AlertDialog.Builder(this).create()创建AlertDialog对象，然后往里面加入自己自己定义格式即可实现自定义对话框

  关键代码：

  ```java
  AlertDialog.Builder builder = new AlertDialog.Builder(this);
  final AlertDialog dialog = builder.create();
  View dialogView = View.inflate(this, R.layout.index_linaer, null);
  dialog.setView(dialogView);
  ```

- 代码

  1. MainActivity.java

     ```java
     package com.example.test_03_02;
     
     import androidx.appcompat.app.AlertDialog;
     import androidx.appcompat.app.AppCompatActivity;
     
     import android.os.Bundle;
     import android.view.View;
     import android.widget.Button;
     import android.widget.EditText;
     import android.widget.Toast;
     
     public class MainActivity extends AppCompatActivity {
     
         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
             AlertDialog.Builder builder = new AlertDialog.Builder(this);
             final AlertDialog dialog = builder.create();
             View dialogView = View.inflate(this, R.layout.index_linaer, null);
             dialog.setView(dialogView);
     
     //        final EditText et_name = dialogView.findViewById(R.id.et_name);
     //        final EditText et_pwd = dialogView.findViewById(R.id.et_pwd);
     
             final Button btn_login = dialogView.findViewById(R.id.btn_login);
             final Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
             final Button btn_view = this.findViewById(R.id.button);
     
             btn_view.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     dialog.show();
                 }
             });
     
             btn_cancel.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     dialog.dismiss();
                 }
             });
     
             btn_login.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     dialog.dismiss();
                     Toast.makeText(MainActivity.this,"欢迎登录",Toast.LENGTH_SHORT).show();
                 }
             });
     
         }
     }
     ```

  2. activity_main.xml

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto"
         xmlns:tools="http://schemas.android.com/tools"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         tools:context=".MainActivity">
     
         <Button
             android:id="@+id/button"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:text="对话框"
             app:layout_constraintBottom_toBottomOf="parent"
             app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toStartOf="parent"
             app:layout_constraintTop_toTopOf="parent" />
     
     </androidx.constraintlayout.widget.ConstraintLayout>
     ```

  3. index_linear.xml

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
         android:orientation="vertical"
         android:layout_width="match_parent"
         android:layout_height="match_parent">
     
         <TextView
             android:id="@+id/textView"
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:background="#FFC107"
             android:gravity="center"
             android:text="RNOROIO APP"
             android:textColor="@android:color/white"
             android:textSize="34sp" />
     
         <EditText
             android:id="@+id/et_name"
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:hint="Username"
             android:textSize="18sp"/>
     
         <EditText
             android:id="@+id/et_pwd"
             android:inputType="textPassword"
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:hint="Password"
             android:textSize="18sp"/>
     
         <LinearLayout
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:layout_marginBottom="5dp"
             android:orientation="horizontal"
             android:paddingLeft="5dp"
             android:paddingRight="5dp">
     
             <Button
                 android:id="@+id/btn_cancel"
                 android:layout_width="wrap_content"
                 android:layout_height="wrap_content"
                 android:layout_marginRight="10dp"
                 android:layout_weight="1"
                 android:background="#169ee5"
                 android:text="Cancel"
                 android:textColor="@android:color/white"
                 android:textSize="16sp" />
     
             <Button
                 android:id="@+id/btn_login"
                 android:layout_width="wrap_content"
                 android:layout_height="wrap_content"
                 android:layout_weight="1"
                 android:background="#169ee5"
                 android:text="Sign in"
                 android:textColor="@android:color/white"
                 android:textSize="16sp" />
         </LinearLayout>
     
     </LinearLayout>
     ```

  实验截图

  ![](https://github.com/463815585/Android/blob/main/component_test/img/AlertDialog_result.jpg)

## 实验三：**使用XML定义菜单**

- 实现思路

  首先需要在资源中创建menu文件夹，然后在其中创建.xml菜单项文件，然后才能在MainActivity.java中加入菜单项，其中onCreateOptionsMenu(Menu menu)方法创建菜单项，onOptionsItemSelected(@NonNull MenuItem item)监听菜单项点击事件。

- 代码

  1. MainActivity.java

     ```java
     package com.example.test_03_03;
     
     import androidx.annotation.NonNull;
     import androidx.appcompat.app.AppCompatActivity;
     
     import android.graphics.Color;
     import android.os.Bundle;
     import android.view.Menu;
     import android.view.MenuItem;
     import android.view.View;
     import android.widget.TextView;
     import android.widget.Toast;
     
     public class MainActivity extends AppCompatActivity {
     
         private TextView textView;
     
         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
             textView = findViewById(R.id.testFont);
         }
     
         @Override
         public boolean onCreateOptionsMenu(Menu menu) {
             getMenuInflater().inflate(R.menu.menu_main,menu);
             return true;
         }
     
         @Override
         public boolean onOptionsItemSelected(@NonNull MenuItem item) {
             switch (item.getItemId()){
                 case R.id.general:
                     Toast.makeText(this, "普通按钮触发", Toast.LENGTH_SHORT).show();
                     break;
                 case R.id.big:
                     textView.setTextSize(20);
                     break;
                 case R.id.mid:
                     textView.setTextSize(16);
                     break;
                 case R.id.small:
                     textView.setTextSize(10);
                     break;
                 case R.id.it_red:
                     textView.setTextColor(Color.RED);
                     break;
                 case R.id.it_black:
                     textView.setTextColor(Color.BLACK);
                     break;
                 default:
                     break;
             }
             return true;
         }
     }
     ```

  2. activity_main.xml

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto"
         xmlns:tools="http://schemas.android.com/tools"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         tools:context=".MainActivity">
     
         <TextView
             android:id="@+id/testFont"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:text="Hello World!"
             app:layout_constraintBottom_toBottomOf="parent"
             app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toStartOf="parent"
             app:layout_constraintTop_toTopOf="parent" />
     
     </androidx.constraintlayout.widget.ConstraintLayout>
     ```

  3. menu/menu_main.xml

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <menu xmlns:android="http://schemas.android.com/apk/res/android">
     
         <item android:id="@+id/fontSize"
             android:title="字体大小">
             <menu>
                 <item android:id="@+id/big"
                     android:title="大"/>
                 <item android:id="@+id/mid"
                     android:title="中"/>
                 <item android:id="@+id/small"
                     android:title="小"/>
             </menu>
         </item>
     
         <item android:id="@+id/general"
             android:title="普通菜单项"/>
     
         <item android:id="@+id/fontColor"
             android:title="字体颜色">
             <menu>
                 <item android:title="红"
                     android:id="@+id/it_red"/>
                 <item android:id="@+id/it_black"
                     android:title="黑"/>
             </menu>
         </item>
     
     </menu>
     ```

- 实验截图

  ![](https://github.com/463815585/Android/blob/main/component_test/img/menu_result_01.jpg)

  ![](https://github.com/463815585/Android/blob/main/component_test/img/menu_result_02.jpg)

## 实验四：**创建上下文操作模式(ActionMode)的上下文菜单**

- 实现思路

  首先利用到第一个小实验创建列表，其二利用ListView中的setMultiChoiceModeListener()方法创建上下文菜单，在创建前必须加listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL)表示ListView可多选，初始设置是只可单选的。然后在其中创建AbsListView.MultiChoiceModeListener()接口的匿名内部类，实现方法。在onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked)方法中实现本次实验内容。

  关键代码----

  ```java
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
  ```

- 代码

  1. MainActivity.java

     ```java
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
     ```

  2. activity_main.xml

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto"
         xmlns:tools="http://schemas.android.com/tools"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         tools:context=".MainActivity">
     
         <ListView
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             app:layout_constraintBottom_toBottomOf="parent"
             app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toStartOf="parent"
             app:layout_constraintTop_toTopOf="parent"
             android:id="@+id/listView_main"
             android:choiceMode="multipleChoiceModal">
     <!--        android:listSelector="#00BCD4">-->
         </ListView>
     </androidx.constraintlayout.widget.ConstraintLayout>
     ```

  3. item.xml

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto"
         android:orientation="horizontal"
         android:layout_width="match_parent"
         android:layout_height="match_parent">
     
         <ImageView
             android:id="@+id/imageView"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             app:srcCompat="@android:mipmap/sym_def_app_icon"
             android:layout_gravity="center"/>
     
         <TextView
             android:id="@+id/textView"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:layout_gravity="center"
             android:layout_marginLeft="10dp"
             android:text="TextView"
             android:textColor="@color/black"
             android:textSize="24sp" />
     </LinearLayout>
     ```

- 实验截图

  ![](https://github.com/463815585/Android/blob/main/component_test/img/ActionMode_result.jpg)







  