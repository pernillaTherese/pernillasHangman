package com.pernillatherese.hangman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.text.method.LinkMovementMethod;
import android.graphics.Color;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView myGitUrlView = findViewById(R.id.my_git_url);
        myGitUrlView.setMovementMethod(LinkMovementMethod.getInstance());
        /*myGitUrlView.setLinkTextColor(Color.BLACK);*/

        TextView attBackgroundUrlView = findViewById(R.id.att_background_url);
        attBackgroundUrlView.setMovementMethod(LinkMovementMethod.getInstance());

        TextView attToonUrlView = findViewById(R.id.att_toon_url);
        attToonUrlView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_about);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_play: {
                goToPlayActivity();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void goToPlayActivity() {
        Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
        startActivity(intent);
    }
}