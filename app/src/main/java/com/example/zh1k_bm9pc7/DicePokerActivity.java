package com.example.zh1k_bm9pc7;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewOnReceiveContentListener;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class DicePokerActivity extends AppCompatActivity {

    TextView roll1,roll2,roll3,textViewMoney;
    EditText editTextBet;
    ImageView imageViewDice;
    SeekBar seekBar;
    AlertDialog.Builder builder;
    Button btnHome,btnDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_poker);
        roll1 = findViewById(R.id.textViewRoll1);
        roll2 = findViewById(R.id.textViewRoll2);
        roll3 = findViewById(R.id.textViewRoll3);
        textViewMoney = findViewById(R.id.textViewMoney);
        editTextBet = findViewById(R.id.editTextBet);
        imageViewDice = findViewById(R.id.imageViewDice);
        seekBar = findViewById(R.id.seekBar);

        textViewMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (Integer.parseInt(String.valueOf(textViewMoney.getText()))<0){
                    btnDice.setEnabled(false);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                editTextBet.setText(String.valueOf((int)(i)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder = new AlertDialog.Builder(this);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DicePokerActivity.this,MainActivity.class);
                Toast.makeText(DicePokerActivity.this, textViewMoney.getText(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }


    public void rollDice(View view){
        int intRoll1 = (int)(6.0 * Math.random()) + 1;
        int intRoll2 = (int)(6.0 * Math.random()) + 1;
        int intRoll3 = (int)(6.0 * Math.random()) + 1;
        String strRoll1 = Integer.toString(intRoll1);
        String strRoll2 = Integer.toString(intRoll2);
        String strRoll3 = Integer.toString(intRoll3);

        roll1.setText(strRoll1);
        roll2.setText(strRoll2);
        roll3.setText(strRoll3);

        int ctr = 0;
        if (intRoll1 == intRoll2){
            ctr++;
        }
        if (intRoll1 == intRoll3){
            ctr++;
        }
        if (intRoll2 == intRoll3){
            ctr++;
        }

        if (ctr == 3){
            Toast.makeText(DicePokerActivity.this, "Tripla!", Toast.LENGTH_SHORT).show();
            roll1.setBackgroundColor(getResources().getColor(R.color.red));
            roll2.setBackgroundColor(getResources().getColor(R.color.red));
            roll3.setBackgroundColor(getResources().getColor(R.color.red));
            int bet = Integer.parseInt(String.valueOf(editTextBet.getText()));
            int money = Integer.parseInt(String.valueOf(textViewMoney.getText()));
            int new_money = money+bet;
            textViewMoney.setText(String.valueOf(new_money));

            Animation rotation  = AnimationUtils.loadAnimation(
                    getApplicationContext(),R.anim.rotation
            );
            imageViewDice.startAnimation(rotation);
        }
        if (ctr == 1){
            Toast.makeText(DicePokerActivity.this, "Dupla!", Toast.LENGTH_SHORT).show();
            int bet = Integer.parseInt(String.valueOf(editTextBet.getText()));
            int money = Integer.parseInt(String.valueOf(textViewMoney.getText()));
            int new_money = money+bet/2;
            textViewMoney.setText(String.valueOf(new_money));
            roll1.setBackgroundColor(getResources().getColor(R.color.black));
            roll2.setBackgroundColor(getResources().getColor(R.color.black));
            roll3.setBackgroundColor(getResources().getColor(R.color.black));
        }
        if (ctr == 0){
            int bet = Integer.parseInt(String.valueOf(editTextBet.getText()));
            int money = Integer.parseInt(String.valueOf(textViewMoney.getText()));
            int new_money = money-bet;
            textViewMoney.setText(String.valueOf(new_money));
            roll1.setBackgroundColor(getResources().getColor(R.color.black));
            roll2.setBackgroundColor(getResources().getColor(R.color.black));
            roll3.setBackgroundColor(getResources().getColor(R.color.black));
        }
        if (Integer.parseInt(String.valueOf(textViewMoney.getText())) <= 0){
            builder.setMessage("Szeretnél kilépni??")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            Toast.makeText(getApplicationContext(),"Kiléptél",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(),"Mínuszban leszel",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Nincs tobb penzed");
            alert.show();
        }
    }
}