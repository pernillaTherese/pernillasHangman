package com.pernillatherese.hangman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    private boolean winner;
    private int countDown = 0;
    private String selectedWord;
    private String underscoredWord;
    private String finalWord;
    private char guessedLetter;
    private int wrongGuess = 0;
    private final int maxWrongGuess =7;
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
    private TextView guessedWordTV;
    private TextView guessedLettersTV;
    private TextView resultTV;
    private TextView resultCorrectWordTV;
    private Button newGameBtn;
    private ConstraintLayout keyboard;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Find views - buttons
        animView = findViewById(R.id.anim_view);
        countDownTV = findViewById(R.id.countdown_txt);
        guessedWordTV = findViewById(R.id.guessed_word_txt);
        guessedLettersTV = findViewById(R.id.guessed_letters_txt);
        resultTV = findViewById(R.id.result_txt);
        resultCorrectWordTV = findViewById(R.id.result_correct_word_txt);
        newGameBtn = findViewById(R.id.new_game_btn);
        keyboard = findViewById(R.id.letters_layout);

        //Set up game board
        randomWord();
        guessedWordTV.setText(underscoredWord);
        countDownTV.setText(countDown + "/" + maxWrongGuess + " " + getString(R.string.incorrect_guesses));
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

    //Guess letters and put them to List of guessed letters
    public void playGame() {
        //Make keys clickable and clicked letters disappear from keyboard
        for (int i = 1; i < (keyboard.getChildCount()); i++) {
            TextView letterView = (TextView) keyboard.getChildAt(i);
            letterView.setOnClickListener(new View.OnClickListener() {

                @SuppressLint("SetTextI18n")
                public void onClick(View view) {

                    guessedLetter = letterView.getText().charAt(0);
                    letterView.setVisibility(View.GONE);

                    //Make guessed letter add to guessedLetters TextView
                    guessedLetters.add(guessedLetter);
                    guessedLettersTV.setText(TextUtils.join(", ", guessedLetters));

                    //If guess is wrong
                    if(!checkGuess()) {
                        animView.setImageResource(animList.get(wrongGuess));
                        wrongGuess++;
                        countDown++;
                        countDownTV.setText(countDown + "/" + maxWrongGuess + " " + getString(R.string.incorrect_guesses));
                        //Toast.makeText(PlayActivity.this, "antal fel" + wrongGuess, Toast.LENGTH_SHORT).show();
                    }

                    if((wrongGuess == maxWrongGuess) && (!winner)) {
                        resultTV.setVisibility(View.VISIBLE);
                        resultTV.setText(getString(R.string.looser));
                        keyboard.setVisibility(View.INVISIBLE);
                        guessedLettersTV.setVisibility(View.INVISIBLE);
                        guessedWordTV.setVisibility(View.INVISIBLE);
                        resultCorrectWordTV.setVisibility(View.VISIBLE);
                        resultCorrectWordTV.setText(selectedWord.toUpperCase());
                    }

                    if(winner) {
                        resultTV.setVisibility(View.VISIBLE);
                        resultTV.setText(getString(R.string.winner));
                        keyboard.setVisibility(View.INVISIBLE);
                        guessedLettersTV.setVisibility(View.INVISIBLE);
                        guessedWordTV.setVisibility(View.INVISIBLE);
                        resultCorrectWordTV.setVisibility(View.VISIBLE);
                        resultCorrectWordTV.setText(selectedWord.toUpperCase());
                    }

                }
            });
        }
    }

    //Check if guessed letter is correct and replace underscore with correct letter.
    public boolean checkGuess() {
        boolean correctLetter = false;

        //Make correctly guessed letter replace underscore in correctWordTV
        for(int j=0; j<underscoredWordList.size(); j++){

            if (guessedLetter == selectedWord.toUpperCase().charAt(j)) {
                underscoredWordList.set(j, guessedLetter);
                guessedWordTV.setText(TextUtils.join("", underscoredWordList));
                correctLetter = true;
            }
            Toast.makeText(PlayActivity.this, selectedWord, Toast.LENGTH_SHORT).show();
            finalWord = TextUtils.join("", underscoredWordList);
            if (selectedWord.equalsIgnoreCase(finalWord)) {
                winner = true;
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