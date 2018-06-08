package org.assembly.views.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageButton;

import org.assembly.R;
import org.assembly.views.fragments.HomeFragment;
import org.assembly.views.fragments.ProposalsFragment;
import org.assembly.views.fragments.ReviewFragment;


public class MainActivity extends AppCompatActivity {
    private ImageButton profile;
    private Toolbar actionBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment selected = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                selected = new HomeFragment();
                break;

            case R.id.navigation_dashboard:
                selected = new ProposalsFragment();
                break;

            case R.id.navigation_notifications:
                selected = new ReviewFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, selected)
                .commit();
        return true;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("MAIN", sp.getString("token", ""));

        actionBar = findViewById(R.id.action_bar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new HomeFragment())
                .commit();
    }
}
