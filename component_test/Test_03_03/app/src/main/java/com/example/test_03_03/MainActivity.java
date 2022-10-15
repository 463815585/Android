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