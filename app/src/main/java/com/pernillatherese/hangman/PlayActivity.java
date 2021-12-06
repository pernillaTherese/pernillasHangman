package com.pernillatherese.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlayActivity extends AppCompatActivity {

    //vars
    AnimationDrawable firstAnimation;
    String selectedWord;

    //views
    ImageView animView;
    TextView correctWordTV;
    TextView guessedTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //views
        animView = findViewById(R.id.anim_view);
        correctWordTV = findViewById(R.id.correct_word_txt);
        guessedTV = findViewById(R.id.guessed_letters_txt);

        //get random word
        randomWord();
        correctWordTV.setText(selectedWord);

        //Animation //TODO: Make it play when guess is wrong
        animView.setBackgroundResource(R.drawable.first_animation);
        firstAnimation = (AnimationDrawable) animView.getBackground();

        //Kod för att läsa in hela filen. men jag vill ju få ett random ord. //TODO: Can prob. remove this
        /*readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                try {
                    InputStream is = getAssets().open("wordList_eng.txt");
                    int size = is.available();
                    byte[] buffer = new byte(size);
                    is.read(buffer);
                    is.close();
                    text = new String(buffer);
                }catch(IOException ex) {
                    ex.printStackTrace();
                }
                tillfText.setText(text);
            }
        });*/
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