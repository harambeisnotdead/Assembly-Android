package org.assembly.views.fragments;

import java.util.List;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import org.assembly.R;
import org.assembly.APIHandler;
import org.assembly.models.Proposal;
import org.assembly.tasks.RetrieveProposalTask;


public class HomeFragment extends Fragment {
    private APIHandler api;
    Context context;
    private List<Proposal> proposals;

    ImageView voting_image, debating_image;
    TextView voting_title, voting_close_date, voting_comments, voting_votes,
        debating_title, debating_close_date, debating_comments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        voting_image = (ImageView) view.findViewById(R.id.proposal_voting_image);
        voting_title = (TextView) view.findViewById(R.id.proposal_voting_title);
        voting_close_date = (TextView) view.findViewById(R.id.proposal_voting_close);
        voting_comments = (TextView) view.findViewById(R.id.proposal_voting_comments);
        voting_votes = (TextView) view.findViewById(R.id.proposal_voting_votes);

        debating_image = (ImageView) view.findViewById(R.id.proposal_debate_image);
        debating_title = (TextView) view.findViewById(R.id.proposal_debate_title);
        debating_close_date = (TextView) view.findViewById(R.id.proposal_debate_close);
        debating_comments = (TextView) view.findViewById(R.id.proposal_debate_comments);

        context = getContext();
        proposals = new ArrayList<Proposal>();
        api = new APIHandler(context);
        new RetrieveProposalTask(api, proposals, voting_image, voting_title, voting_close_date,
                voting_votes, voting_comments).execute(APIHandler.Endpoints.Proposals.VOTING);
        new RetrieveProposalTask(api, proposals, debating_image, debating_title, debating_close_date,
                debating_comments, null).execute(APIHandler.Endpoints.Proposals.DEBATING);

        return view;
    }
}
