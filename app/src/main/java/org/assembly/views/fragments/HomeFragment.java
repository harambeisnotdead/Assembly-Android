package org.assembly.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.assembly.R;
import org.assembly.models.Proposal;
import org.assembly.tasks.RetrieveProposalTask;
import org.assembly.utils.Endpoints;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private List<Proposal> proposals;

    private ImageView voting_image;
    private ImageView debating_image;

    TextView voting_title, voting_close_date, voting_comments, voting_votes,
        debating_title, debating_close_date, debating_comments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        proposals = new ArrayList<>();

        voting_image = view.findViewById(R.id.proposal_voting_image);
        voting_title = view.findViewById(R.id.proposal_voting_title);
        voting_close_date = view.findViewById(R.id.proposal_voting_close);
        voting_comments = view.findViewById(R.id.proposal_voting_comments);
        voting_votes = view.findViewById(R.id.proposal_voting_votes);

        debating_image = view.findViewById(R.id.proposal_debate_image);
        debating_title = view.findViewById(R.id.proposal_debate_title);
        debating_close_date = view.findViewById(R.id.proposal_debate_close);
        debating_comments = view.findViewById(R.id.proposal_debate_comments);

        new RetrieveProposalTask(getContext(), voting_image, voting_title, voting_close_date,
                voting_comments, voting_votes).execute(Endpoints.Proposals.VOTING);
        new RetrieveProposalTask(getContext(), debating_image, debating_title, debating_close_date,
                debating_comments, null).execute(Endpoints.Proposals.DEBATING);

        return view;
    }
}
