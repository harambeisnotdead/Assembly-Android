package org.assembly.views.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import org.assembly.R;
import org.assembly.views.fragments.HomeFragment;
import org.assembly.views.fragments.ProposalsFragment;
import org.assembly.views.fragments.ReviewFragment;


public class MainActivity extends AppCompatActivity {
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

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new HomeFragment())
                .commit();
    }
}
