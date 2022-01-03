package com.pernillatherese.hangman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class PlayActivity extends AppCompatActivity {

    //vars
    AnimationDrawable animation;
    private String selectedWord;
    private String underscoredWord;
    private char guessedLetter;
    private int noOfGuesses =10;
    boolean rightLetter;
    private ArrayList<Character> rightWordList = new ArrayList<>();
    private ArrayList<Character> guessedLetters = new ArrayList<>();
    private ArrayList<Character> underscoredWordList = new ArrayList<>();

    //views - buttons
    private ImageView animView;
    private TextView countDownTV;
    private TextView correctWordTV;
    private TextView guessedTV;
    private Button newGameBtn;
    private ConstraintLayout keyboard;

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
        keyboard = findViewById(R.id.letters_layout);

        //Set up game board
        randomWord();
        correctWordTV.setText(underscoredWord);

        //Play game
        play();

        //First Background
        //TODO: Fix background image
        animView.setBackgroundResource(R.drawable.play_background);
        //animView.setBackgroundResource(R.drawable.first_animation);
        //animation = (AnimationDrawable) animView.getBackground();


        //Start new game (recreate PlayActivity)
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(intent);
            }
        });
    }

    //Play the game - Guess letters
    public void play() {

        //Make keys clickable and clicked letters disappear from keyboard
        for (int i = 1; i<(keyboard.getChildCount()); i++) {
            TextView letterView = (TextView) keyboard.getChildAt(i);
            letterView.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                    guessedLetter = letterView.getText().charAt(0);
                    letterView.setVisibility(View.GONE);

                    //Make guessed letter add to guessedLetters TextView
                    guessedLetters.add(guessedLetter);
                    guessedTV.setText(TextUtils.join(", ", guessedLetters));

                    for(int i=0; i<underscoredWordList.size(); i++){
                        if (guessedLetter == selectedWord.toUpperCase().charAt(i)) {
                            underscoredWordList.set(i, guessedLetter);
                            correctWordTV.setText(TextUtils.join("", underscoredWordList));
                        }else{
                            animView.setBackgroundResource(R.drawable.first_animation);
                            animation = (AnimationDrawable) animView.getBackground();
                            animation.start();
                        }
                    }
                }

            });
        }

    }


    // animation - switch between pictures
    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animation.start();
    }*/


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

            //make word show only underscores
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i<selectedWord.length(); i++) {
                sb.append("_");
            }

            //make list of underscored word
            underscoredWord = sb.toString();
            for(int i=0; i<underscoredWord.length(); i++) {
                underscoredWordList.add(underscoredWord.charAt(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}