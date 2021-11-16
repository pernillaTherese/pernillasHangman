package com.pernillatherese.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageView;

public class PlayActivity extends AppCompatActivity {
    AnimationDrawable firstAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ImageView animView = findViewById(R.id.anim_view);
        animView.setBackgroundResource(R.drawable.first_animation);
        firstAnimation = (AnimationDrawable) animView.getBackground();

        //Keyboard setting
        EditText guessEdit = findViewById(R.id.guess_edit);
        KeyBoard keyboard = findViewById(R.id.keyboard);
        guessEdit.setRawInputType(InputType.TYPE_CLASS_TEXT);
        guessEdit.setTextIsSelectable(true);

        InputConnection ic = guessEdit.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        firstAnimation.start();
    }
}