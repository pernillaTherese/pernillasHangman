package com.pernillatherese.hangman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PlayActivity extends AppCompatActivity {

    //vars
    AnimationDrawable animation;
    private String selectedWord;
    private String underscoredWord;
    private char guessedLetter;
    private int noOfFailure =7;
    private boolean correctWord;
    private ArrayList<Character> rightWordList = new ArrayList<>();
    private ArrayList<Character> guessedLetters = new ArrayList<>();
    private ArrayList<Character> underscoredWordList = new ArrayList<>();

    //vars animations
    private final int anim_a = R.drawable.anim_a;
    private final int anim_b = R.drawable.anim_b;
    private final int anim_c = R.drawable.anim_c;
    private final int anim_d = R.drawable.anim_d;
    private final int anim_e = R.drawable.anim_e;
    private final int anim_f = R.drawable.anim_f;
    private final int anim_g = R.drawable.anim_g;
    private final ArrayList<Integer> animList = new ArrayList<>(Arrays.asList
            (anim_a, anim_b, anim_c,anim_d, anim_e, anim_f, anim_g));


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
        animView.setImageResource(R.drawable.play_background);

        //Start new game (recreate PlayActivity)
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(intent);
            }
        });

        //Play game
        playGame();
    }

/*    public void playGame() {
        while(true) {
            guessLetters();
            if(replaceLetters()) {
                break;
            }
        }
    }*/
    public void playGame() {
        guessLetters();

    }

    //Guess letters and put them to List of guessed letters
    public void guessLetters() {

        //Make keys clickable and clicked letters disappear from keyboard
        for (int i = 1; i < (keyboard.getChildCount()); i++) {
            TextView letterView = (TextView) keyboard.getChildAt(i);
            letterView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    guessedLetter = letterView.getText().charAt(0);
                    letterView.setVisibility(View.GONE);

                    //Make guessed letter add to guessedLetters TextView
                    guessedLetters.add(guessedLetter);
                    guessedTV.setText(TextUtils.join(", ", guessedLetters));
                    if(!replaceLetters()) {
                        animView.setImageResource(animList.get(0));
                    }
                }
            });
        }

    }

    //Check if guessed letter is correct and replace underscore with correct letter.
    public boolean replaceLetters() {
        boolean correctLetter = false;
        //Make correctly guessed letter replace underscore in correctWordTV
        for(int j=0; j<underscoredWordList.size(); j++){

            if (guessedLetter == selectedWord.toUpperCase().charAt(j)) {
                underscoredWordList.set(j, guessedLetter);
                correctWordTV.setText(TextUtils.join("", underscoredWordList));
                correctLetter = true;
                Toast.makeText(PlayActivity.this, "toast" + correctLetter, Toast.LENGTH_SHORT).show();
            }
        }
        return correctLetter;
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