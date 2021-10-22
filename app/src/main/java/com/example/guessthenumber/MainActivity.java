package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.Math;


public class MainActivity extends AppCompatActivity {
    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int number = (int) (Math.random() * 100);
    int max = 100;
    boolean is_easy = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = findViewById(R.id.text_view);
        etInput = findViewById(R.id.edit_text);
        bControl = findViewById(R.id.button);
    }

    @SuppressLint("SetTextI18n")
    public void onClick(View view) {
        String input = etInput.getText().toString();
        if (!input.matches("\\d+")) {
            tvInfo.setText(getResources().getString(R.string.error));
        } else {
            int inputNumber = Integer.parseInt(input);
            if (inputNumber > number && inputNumber <= max) {
                tvInfo.setText(getResources().getString(R.string.ahead));
            } else {
                if (inputNumber < number && inputNumber >= 0) {
                    tvInfo.setText(getResources().getString(R.string.behind));
                } else {
                    if (inputNumber == number) {
                        tvInfo.setText(getResources().getString(R.string.hit));
                    } else {
                        tvInfo.setText(getResources().getString(R.string.range_error));
                    }
                }
            }
        }
    }

    public void restart() {
        if (is_easy) {
            make_action("Easy");
        } else {
            make_action("Hard");
        }
    }

    public void difficulty_easy() {
        number = (int) (Math.random() * 100);
        etInput.setText(R.string.empty_text);
        max = 100;
        is_easy = true;
    }

    public void difficulty_hard() {
        number = (int) (Math.random() * 10000);
        etInput.setText(R.string.empty_text);
        max = 10000;
        is_easy = false;
    }

    public void exit() {
        System.exit(0);
    }

    @SuppressLint("SetTextI18n")
    public void make_action(String string) {
        switch (string) {
            case "Restart":
                restart();
                break;
            case "Hard":
                difficulty_hard();
                tvInfo.setText(getString(R.string.difficulty_hard) + getString(R.string.restarted));
                break;
            case "Easy":
                difficulty_easy();
                tvInfo.setText(getString(R.string.difficulty_easy) + getString(R.string.restarted));
                break;
            case "Exit":
                exit();
                break;
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        make_action(item.getTitle().toString());
        return super.onOptionsItemSelected(item);
    }
}