package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    GifImageView gifview;
    TextView tomscoretv, jerryscoretv, winnertextbox;
    RelativeLayout tomcard, jerrycard;
    Handler handler = new Handler();

    int tomscore = 0, jerryscore = 0, flag = 0, count = 0;
    boolean isWinner = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    public void init() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        tomscoretv = findViewById(R.id.tomscore);
        jerryscoretv = findViewById(R.id.jerryscore);

        tomcard = findViewById(R.id.tomcard);
        jerrycard = findViewById(R.id.jerrycard);

        gifview = findViewById(R.id.gifImageView);

        winnertextbox = findViewById(R.id.winnertextview);

    }

    public void check(View view) {
        Button curbtn = (Button) view;
        MediaPlayer osound = MediaPlayer.create(this, R.raw.zerosound);
        MediaPlayer xsound = MediaPlayer.create(this, R.raw.xsound);
        MediaPlayer drawsound = MediaPlayer.create(this, R.raw.drawsound);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.framanimation);

        if (curbtn.getText().equals("") && isWinner == false) {
            if (flag == 0) {
                curbtn.setTextColor(getResources().getColor(R.color.white));
                curbtn.setText("X");
                xsound.start();
                count++;
                flag = 1;
                tomcard.clearAnimation();
                jerrycard.startAnimation(animation);

            } else {
                curbtn.setTextColor(getResources().getColor(R.color.black));
                curbtn.setText("O");
                osound.start();
                count++;
                flag = 0;

                jerrycard.clearAnimation();
                tomcard.startAnimation(animation);
            }
        }

        if (count > 4 && isWinner == false) {
            String[] vals = {btn1.getText().toString(), btn2.getText().toString(), btn3.getText().toString(),
                    btn4.getText().toString(), btn5.getText().toString(), btn6.getText().toString(),
                    btn7.getText().toString(), btn8.getText().toString(), btn9.getText().toString()};

            if (vals[0].equals(vals[1]) && vals[1].equals(vals[2]) && !vals[0].equals("")) {
                set_btn_atribute(btn1, btn2, btn3);
                winner(vals[0]);
            } else if (vals[3].equals(vals[4]) && vals[4].equals(vals[5]) && !vals[3].equals("")) {
                set_btn_atribute(btn4, btn5, btn6);
                winner(vals[3]);
            } else if (vals[6].equals(vals[7]) && vals[7].equals(vals[8]) && !vals[6].equals("")) {
                set_btn_atribute(btn7, btn8, btn9);
                winner(vals[6]);
            } else if (vals[0].equals(vals[3]) && vals[3].equals(vals[6]) && !vals[0].equals("")) {
                set_btn_atribute(btn1, btn4, btn7);
                winner(vals[0]);
            } else if (vals[1].equals(vals[4]) && vals[4].equals(vals[7]) && !vals[1].equals("")) {
                set_btn_atribute(btn2, btn5, btn8);
                winner(vals[1]);
            } else if (vals[2].equals(vals[5]) && vals[5].equals(vals[8]) && !vals[2].equals("")) {
                set_btn_atribute(btn3, btn6, btn9);
                winner(vals[2]);
            } else if (vals[0].equals(vals[4]) && vals[4].equals(vals[8]) && !vals[0].equals("")) {
                set_btn_atribute(btn1, btn5, btn9);
                winner(vals[0]);
            } else if (vals[2].equals(vals[4]) && vals[4].equals(vals[6]) && !vals[2].equals("")) {
                set_btn_atribute(btn3, btn5, btn7);
                winner(vals[2]);
            } else if (count == 9) {
                drawsound.start();
                winnertextbox.setText(".....Tie.....");
                winnertextbox.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isWinner == false) {
                            winnertextbox.setVisibility(View.GONE);
                            btnclear();
                        }
                    }
                }, 3000);

            }
        }
    }

    public void set_btn_atribute(Button btn1, Button btn2, Button btn3) {
        btn1.setTextColor(0xD0388E3C);
        btn2.setTextColor(0xD0388E3C);
        btn3.setTextColor(Color.parseColor("#D0388E3C"));
    }

    private void winner(String val) {
        isWinner = true;
        MediaPlayer winsound = MediaPlayer.create(this, R.raw.winnersound);
        winsound.start();

        if (val.equals("X")) {

            gifview.setImageResource(R.drawable.tompunchjerry);
            winnertextbox.setText("Player X is Winner");


            tomscore++;
            tomscoretv.setText(String.format("%s", tomscore));
            winnertextbox.setVisibility(View.VISIBLE);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isWinner) {
                        gifview.setVisibility(View.VISIBLE);
                    }
                }
            }, 2500);

        } else {
            gifview.setImageResource(R.drawable.j_t_punch);
            winnertextbox.setText("Player O is Winner");

            jerryscore++;
            jerryscoretv.setText(String.format("%s", jerryscore));
            winnertextbox.setVisibility(View.VISIBLE);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isWinner) {
                        gifview.setVisibility(View.VISIBLE);
                    }
                }
            }, 2500);
        }
    }

    public void btnclear() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        count = 0;
        flag = 0;
    }

    public void restart(View v) {
        MediaPlayer winsound = MediaPlayer.create(this, R.raw.winnersound);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.framanimation);

        winsound.start();
        btnclear();

        flag = 0;
        count = 0;

        jerrycard.clearAnimation();
        tomcard.startAnimation(animation);
        gifview.setVisibility(View.GONE);
        winnertextbox.setVisibility(View.GONE);
        isWinner = false;

    }
}

