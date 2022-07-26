package com.pernillatherese.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about: {
                goToAboutActivity();
                return true;
            }
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
    public void goToAboutActivity() {
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);
    }
}