package org.assembly.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

import org.assembly.R;
import org.assembly.models.Proposal;

public class ProposalActivity extends AppCompatActivity {
    private TextView title;
    private TextView description;
    private TextView votes;
    private TextView comments;
    private TextView closeDate;
    private Proposal proposal;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);

        title = findViewById(R.id.proposal_title);
        description = findViewById(R.id.proposal_desc);
        back = findViewById(R.id.back);
        closeDate = findViewById(R.id.proposal_close_date);
        votes = findViewById(R.id.proposal_votes);
        comments = findViewById(R.id.proposal_comments);

        proposal = (Proposal) getIntent().getExtras().get("proposal");
        title.setText(proposal.getTitle());
        description.setText(proposal.getDescription());
        closeDate.setText("Colses on: " + proposal.getClose_date());
        votes.setText(String.valueOf(proposal.getVote_votes_count()));
        comments.setText(String.valueOf(proposal.getComment_count()));
        back.setOnClickListener(v -> finish());
    }
}
