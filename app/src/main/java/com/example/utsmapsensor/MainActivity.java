package com.example.utsmapsensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private boolean isFragmentDisplayed = false;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.purpose);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    switchFragment();
                }
            }
        });
    }

    public void displayFragment(){
        DescAppFragment descAppFragment = DescAppFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_desc, descAppFragment).commit();
        textView = findViewById(R.id.fragment_advance);
        textView.setText(R.string.show_map);
        mButton.setText(R.string.prev);
        isFragmentDisplayed = true;
    }

    public void switchFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        DescAppFragment descAppFragment = (DescAppFragment) fragmentManager
                .findFragmentById(R.id.fragment_desc);
        if (descAppFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_desc, descAppFragment).commit();
        }
        textView = findViewById(R.id.fragment_advance);
        textView.setText(R.string.deskripsi);
        mButton.setText(R.string.purpose);
        isFragmentDisplayed = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sensor_map) {
            Intent mapIntent = new Intent(this, MapsActivity.class);
            startActivity(mapIntent);
            return true;
        }
        else if (id == R.id.action_language){
            Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(languageIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}