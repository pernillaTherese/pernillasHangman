package com.pernillatherese.hangman;

import androidx.annotation.ColorRes;
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

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class PlayActivity extends AppCompatActivity {

    //vars
    AnimationDrawable firstAnimation;
    private String selectedWord;
    private String underscoredWord;
    private String guessedLetter;
    private int noOfGuesses =10;
    boolean rightLetter;
    private ArrayList<Character> rightWord = new ArrayList<>();
    private ArrayList<String> guessedLetters = new ArrayList<>();

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
        keyboardSetting();
        correctWordTV.setText(underscoredWord);

        //Play


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

    //Keyboard settings
    //TODO: consider mark them instead of disappearing
    public void keyboardSetting() {

        //List<View> letterViews = new ArrayList<>();
        for (int i = 1; i<(keyboard.getChildCount()); i++) {

            //View letterView = keyboard.getChildAt(i);
            TextView letterView = (TextView) keyboard.getChildAt(i);

            //Make keys clickable and clicked letters disappear from keyboard
            letterView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view) {
                    guessedLetter = letterView.getText().toString();
                    guessedLetters.add(guessedLetter);
                    guessedTV.setText(TextUtils.join(", ", guessedLetters));
                    letterView.setVisibility(View.GONE);

                    /*if (rightLetter) {
                        Toast.makeText(getApplicationContext(), "Rätt bokstav", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Fel bokstav", Toast.LENGTH_SHORT).show();
                    }*/


                }

            });
        }

    }

    // play
    /*public void play() {
        StringBuilder sb = new StringBuilder();
        guessedTV.setText(guessedLetter);
        //guessedTV.setText(sb.append(guessedLetter));
    }*/

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

            //make word show only underscores
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i<selectedWord.length(); i++) {
                sb.append("_");
            }

            //make list of characters
            for(int i=0; i<selectedWord.length(); i++) {
                rightWord.add(selectedWord.charAt(i));
                //Toast.makeText(getApplicationContext(), rightWord.get(i).toString(), Toast.LENGTH_SHORT).show(); Arrayen funkar!
            }
            underscoredWord = sb.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}