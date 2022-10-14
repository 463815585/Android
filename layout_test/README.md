# 实验二报告

## 题目一线性布局应用

- ### 思路：

  创建一个水平线性布局，然后在其中填充四个垂直线性布局，利用了线性布局的嵌套实现页面布局

- ### ①部分代码

  ```java
  <LinearLayout
          android:layout_width="match_parent"
          android:layout_height="181dp"
          android:orientation="horizontal">
  
          <TextView
              android:id="@+id/textView"
              android:layout_width="74dp"
              android:layout_height="match_parent"
              android:layout_weight="1"
              android:background="@drawable/border"
              android:gravity="center"
              android:text="One,One"
              android:textColor="#C5C5C5"
              android:textSize="20sp" />
  
          <TextView
              android:id="@+id/textView2"
              android:layout_width="76dp"
              android:layout_height="match_parent"
              android:layout_weight="1"
              android:background="@drawable/border"
              android:gravity="center"
              android:text="One,Two"
              android:textColor="#C5C5C5"
              android:textSize="20sp" />
  
          <TextView
              android:id="@+id/textView3"
              android:layout_width="wrap_content"
              android:layout_height="match_parent"
              android:layout_weight="1"
              android:background="@drawable/border"
              android:gravity="center"
              android:text="One,Three"
              android:textColor="#C5C5C5"
              android:textSize="20sp" />
  
          <TextView
              android:id="@+id/textView4"
              android:layout_width="wrap_content"
              android:layout_height="match_parent"
              android:layout_weight="1"
              android:background="@drawable/border"
              android:gravity="center"
              android:text="One,Four"
              android:textColor="#C5C5C5"
              android:textSize="20sp" />
      </LinearLayout>
  ```

- ### 实验截图

  ![](img\test_02_01.png)

## 题目二表格布局页面实现

- ### 思路

  利用android:stretchColumns="1"填满tablerow

- ### ①部分代码

  ```jave
  <TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:orientation="vertical"
      android:background="@color/black"
      android:stretchColumns="1">
  
      <TableRow>
          <TextView
              android:text="Open..."
              android:paddingLeft="12dp"
              android:textColor="@color/white">
          </TextView>
          <TextView
              android:text="Ctrl-O"
              android:gravity="right"
              android:padding="1dp"
              android:textColor="@color/white">
          </TextView>
      </TableRow>
  </TableLayout>
  ```

- ### ①横线代码

  ```java
  <View
          android:layout_width="match_parent"
          android:layout_height="2dp"
          android:background="#dfdfdf"/>
  ```

- ### 实验截图

  ![](img\实验二-二.png)

## 题目三约束布局实现①

- ### 思路

  直接在Design中拖取实现

- ### 代码

  ```java
      <EditText
          android:id="@+id/writer"
          android:layout_width="320dp"
          android:layout_height="47dp"
          android:ems="10"
          android:inputType="number"
          android:text="Input"
          android:textColor="@color/black"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.527"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent" />
  
      <EditText
          android:id="@+id/operation"
          android:layout_width="320dp"
          android:layout_height="47dp"
          android:layout_marginTop="16dp"
          android:background="#E2A23F"
          android:ems="10"
          android:gravity="center|right"
          android:inputType="number"
          android:singleLine="false"
          android:text="0.0"
          android:textAllCaps="false"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.527"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/writer" />
  
      <Button
          android:id="@+id/button2"
          android:layout_width="64dp"
          android:layout_height="41dp"
          android:layout_marginStart="20dp"
          android:layout_marginTop="12dp"
          android:text="8"
          app:layout_constraintStart_toEndOf="@+id/button"
          app:layout_constraintTop_toBottomOf="@+id/operation" />
  ```

- ### 实验截图

  ![实验二-三](img\实验二-三.png)

## 题目四约束布局实现②

- ### 代码

  ```java
  <?xml version="1.0" encoding="utf-8"?>
  <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent">
  
      <TextView
          android:id="@+id/textView20"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="Space Stations"
          android:textSize="16sp"
          app:layout_constraintEnd_toEndOf="@+id/imageView2"
          app:layout_constraintStart_toStartOf="@+id/imageView2"
          app:layout_constraintTop_toBottomOf="@+id/imageView2" />
  
      <TextView
          android:id="@+id/textView22"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="Roves"
          android:textSize="16sp"
          app:layout_constraintBottom_toBottomOf="@+id/textView21"
          app:layout_constraintEnd_toEndOf="@+id/imageView4"
          app:layout_constraintStart_toStartOf="@+id/imageView4"
          app:layout_constraintTop_toTopOf="@+id/textView21"
          app:layout_constraintVertical_bias="0.0" />
  
      <TextView
          android:id="@+id/textView23"
          android:layout_width="150dp"
          android:layout_height="130dp"
          android:layout_marginStart="28dp"
          android:background="#F44336"
          android:gravity="center"
          android:text="DCA"
          android:textSize="24sp"
          app:layout_constraintBottom_toBottomOf="@+id/imageView5"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="@+id/imageView5" />
  
      <TextView
          android:id="@+id/textView24"
          android:layout_width="150dp"
          android:layout_height="130dp"
          android:layout_marginEnd="32dp"
          android:background="#F44336"
          android:gravity="center"
          android:text="MARS"
          android:textSize="24sp"
          app:layout_constraintBottom_toBottomOf="@+id/imageView5"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintTop_toTopOf="@+id/imageView5"
          app:layout_constraintVertical_bias="0.434" />
  
      <ImageView
          android:id="@+id/imageView2"
          android:layout_width="100dp"
          android:layout_height="100dp"
          android:layout_marginStart="16dp"
          android:layout_marginTop="16dp"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent"
          app:srcCompat="@drawable/space_station_icon" />
  
      <ImageView
          android:id="@+id/imageView4"
          android:layout_width="100dp"
          android:layout_height="100dp"
          android:layout_marginEnd="16dp"
          app:layout_constraintBottom_toBottomOf="@+id/imageView3"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintTop_toTopOf="@+id/imageView3"
          app:layout_constraintVertical_bias="0.0"
          app:srcCompat="@drawable/rover_icon" />
  
      <ImageView
          android:id="@+id/imageView3"
          android:layout_width="100dp"
          android:layout_height="100dp"
          android:layout_marginStart="39dp"
          app:layout_constraintBottom_toBottomOf="@+id/imageView2"
          app:layout_constraintStart_toEndOf="@+id/imageView2"
          app:layout_constraintTop_toTopOf="@+id/imageView2"
          app:layout_constraintVertical_bias="0.0"
          app:srcCompat="@drawable/rocket_icon" />
  
      <ImageView
          android:id="@+id/imageView5"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginTop="73dp"
          app:layout_constraintEnd_toEndOf="@+id/imageView3"
          app:layout_constraintHorizontal_bias="0.4"
          app:layout_constraintStart_toStartOf="@+id/imageView3"
          app:layout_constraintTop_toBottomOf="@+id/imageView3"
          app:srcCompat="@drawable/double_arrows" />
  
      <ImageView
          android:id="@+id/imageView6"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginStart="5dp"
          android:layout_marginTop="156dp"
          android:layout_marginEnd="9dp"
          app:layout_constraintEnd_toEndOf="@+id/imageView5"
          app:layout_constraintHorizontal_bias="0.0"
          app:layout_constraintStart_toStartOf="@+id/imageView5"
          app:layout_constraintTop_toBottomOf="@+id/imageView5"
          app:srcCompat="@drawable/galaxy" />
  
      <TextView
          android:id="@+id/textView21"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="Flights"
          android:textSize="16sp"
          app:layout_constraintBottom_toBottomOf="@+id/textView20"
          app:layout_constraintEnd_toEndOf="@+id/imageView3"
          app:layout_constraintStart_toStartOf="@+id/imageView3"
          app:layout_constraintTop_toTopOf="@+id/textView20"
          app:layout_constraintVertical_bias="0.0" />
  
      <Switch
          android:id="@+id/switch1"
          android:layout_width="122dp"
          android:layout_height="30dp"
          android:layout_marginTop="16dp"
          android:background="#03A9F4"
          android:text="One Way"
          android:textColor="#FFFFFF"
          android:textSize="14sp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.055"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/textView23" />
  
      <TextView
          android:id="@+id/textView17"
          android:layout_width="83dp"
          android:layout_height="29dp"
          android:layout_marginTop="20dp"
          android:background="#03A9F4"
          android:gravity="center"
          android:text="1 Traveller"
          android:textColor="#FFFFFF"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.048"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/switch1" />
  
      <ImageView
          android:id="@+id/imageView7"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginEnd="31dp"
          android:layout_marginBottom="4dp"
          app:layout_constraintBottom_toBottomOf="@+id/imageView6"
          app:layout_constraintEnd_toStartOf="@+id/imageView6"
          app:layout_constraintTop_toTopOf="@+id/imageView6"
          app:srcCompat="@drawable/rocket_icon" />
  
      <TextView
          android:id="@+id/textView18"
          android:layout_width="357dp"
          android:layout_height="33dp"
          android:layout_marginBottom="16dp"
          android:background="#F44336"
          android:gravity="center"
          android:text="DEPART"
          android:textColor="#FFFFFF"
          android:textSize="20sp"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent" />
  
  </androidx.constraintlayout.widget.ConstraintLayout>
  ```

- ### 实验截图

  ![实验二-四](img\实验二-四.png)