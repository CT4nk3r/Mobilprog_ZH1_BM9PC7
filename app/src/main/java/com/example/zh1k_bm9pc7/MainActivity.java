package com.example.zh1k_bm9pc7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDice;
    Button btnLogical;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDice = findViewById(R.id.btnDice);
        btnLogical = findViewById(R.id.btnLogical);

        btnDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DicePokerActivity.class);
                startActivity(intent);
            }
        });
    }
}