package org.assembly.views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import org.assembly.R;
import org.assembly.models.Proposal;
import org.assembly.models.Vote;
import org.assembly.utils.SharedKeys;
import org.assembly.views.fragments.HomeFragment;
import org.assembly.views.fragments.ProposalsFragment;
import org.assembly.views.fragments.ReviewFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ImageButton bttnProfile;
    private FloatingActionButton fabNewProposal;
    private Toolbar actionBar;
    private BottomNavigationView navigation;
    private TextView citizenUsername;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = item -> {
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
        citizenUsername = findViewById(R.id.citizen_username);
        citizenUsername.setText(String.format(
                sp.getString(SharedKeys.CITIZEN_USERNAME, "citizen")));

        actionBar = findViewById(R.id.action_bar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationListener);
        bttnProfile = findViewById(R.id.bttn_profile);
        bttnProfile.setOnClickListener(v -> startActivity(new Intent(this, CitizenActivity.class)));
        fabNewProposal = findViewById(R.id.fab_new_proposal);
        fabNewProposal.setOnClickListener(
                v -> startActivity(new Intent(this, NewProposalActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY)));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new HomeFragment())
                .commit();
    }
}
