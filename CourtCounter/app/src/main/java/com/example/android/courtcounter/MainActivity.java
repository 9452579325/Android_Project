package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int score = 0, scoreA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void On3Shoot(View view) {
        score = score + 3;
        displayScore(score);
    }

    public void On2Shoot(View view) {
        score = score + 2;
        displayScore(score);
    }

    public void FreeThrows(View view) {
        score = score + 1;
        displayScore(score);
    }

    public void displayScore(int a) {
        TextView text_view = (TextView) findViewById(R.id.score_text_view);
        text_view.setText(String.valueOf(a));
    }

    public void On3ShootB(View view) {
        scoreA = scoreA + 3;
        displayScoreB(scoreA);
    }

    public void On2ShootB(View view) {
        scoreA = scoreA + 2;
        displayScoreB(scoreA);
    }

    public void FreeThrowsB(View view) {
        scoreA = scoreA + 1;
        displayScoreB(scoreA);
    }

    public void displayScoreB(int a) {
        TextView text_view1 = (TextView) findViewById(R.id.score_text_vie);
        text_view1.setText(String.valueOf(a));
    }

    public void ResetScore(View view) {
        score = 0;
        scoreA = 0;
        displayScore(score);
        displayScoreB(scoreA);
    }
}
