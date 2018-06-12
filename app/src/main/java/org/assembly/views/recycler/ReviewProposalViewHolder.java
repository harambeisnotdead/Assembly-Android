package org.assembly.views.recycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.assembly.R;
import org.assembly.models.Proposal;
import org.assembly.tasks.VoteTask;
import org.assembly.utils.Endpoints;
import org.assembly.utils.SharedKeys;

import java.util.ArrayList;

public class ReviewProposalViewHolder extends ProposalViewHolder {
    public TextView votes;
    public ImageButton upvote;
    private ArrayList<Proposal> proposals;
    private Context context;

    public ReviewProposalViewHolder(View itemView, ProposalViewAdapter.OnItemClickListener listener,
                                    ArrayList<Proposal> proposals) {
        super(itemView, listener);
        votes = itemView.findViewById(R.id.proposal_votes);
        upvote = itemView.findViewById(R.id.bttn_upvote);
        upvote.setOnClickListener(upvoteListener);
        this.proposals = proposals;
        context = itemView.getContext();
    }

    private View.OnClickListener upvoteListener = v -> {
        int user = PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(SharedKeys.CITIZEN_ID, 0);
        int proposal = proposals.get(getAdapterPosition()).getId();
        new VoteTask(context, user, Endpoints.Proposals.REVIEW, proposal, false).execute();
//        v.setSelected(!v.isSelected());
    };

    @Override
    public void setVotes(String votes) {
        this.votes.setText(votes);
    }
}

