package com.pernillatherese.hangman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlayActivity extends AppCompatActivity {

    //vars
    AnimationDrawable firstAnimation;
    String selectedWord;

    //views - buttons
    ImageView animView;
    TextView countDownTV;
    TextView correctWordTV;
    TextView guessedTV;
    Button newGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Find views - buttons
        animView = findViewById(R.id.anim_view);
        countDownTV = findViewById(R.id.countdown_txt);
        correctWordTV = findViewById(R.id.correct_word_txt);
        guessedTV = findViewById(R.id.guessed_letters_txt);
        newGameBtn = findViewById(R.id.new_game_btn);

        //get random word
        randomWord();
        correctWordTV.setText(selectedWord);

        //Animation //TODO: Make it play when guess is wrong
        animView.setBackgroundResource(R.drawable.first_animation);
        firstAnimation = (AnimationDrawable) animView.getBackground();

        //Start new game (recreate PlayActivity)
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(intent);
            }
        });
    }

    // animation - switch between pictures
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        firstAnimation.start();
    }

    //get random word from in app storage .txt-file
    public void randomWord() {

        ArrayList<String> words = new ArrayList<>();

        try {
            Scanner sc = new Scanner(getAssets().open("myWordList_sv.txt"));

            while (sc.hasNext()) {
                words.add(sc.nextLine());
            }

            Random rand = new Random();
            selectedWord = words.get(rand.nextInt(words.size()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}