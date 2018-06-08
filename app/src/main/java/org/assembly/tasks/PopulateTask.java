package org.assembly.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.assembly.APIHandler;
import org.assembly.models.Proposal;
import org.assembly.views.activities.ProposalActivity;
import org.assembly.views.recycler.ProposalViewAdapter;
import org.assembly.R;

import java.util.ArrayList;

public class PopulateTask extends AsyncTask<Void, Void, ArrayList<Proposal>> {
    private RecyclerView rv;
    private ProposalViewAdapter rvAdapter;
    private APIHandler api;
    private int layoutId;
    private ArrayList<Proposal> proposals;
    private Context context;
    private SwipeRefreshLayout refreshLayout;

    public PopulateTask(RecyclerView rv, ProposalViewAdapter rvAdapter, APIHandler api,
                        int layoutId, Context context, SwipeRefreshLayout refreshLayout) {
        this.rv = rv;
        this.rvAdapter = rvAdapter;
        this.api = api;
        this.layoutId = layoutId;
        this.context = context;
        this.refreshLayout = refreshLayout;
    }

    @Override
    protected ArrayList<Proposal> doInBackground(Void... voids) {
        switch (layoutId) {
            case R.layout.item_proposal_review:
                return api.getProposals(APIHandler.Endpoints.Proposals.REVIEWING);
            case R.layout.item_proposal_debate:
                return api.getProposals(APIHandler.Endpoints.Proposals.DEBATING);
            case R.layout.item_proposal_vote:
                return api.getProposals(APIHandler.Endpoints.Proposals.VOTING);
        }
        return null;
    }

    private ProposalViewAdapter.OnItemClickListener itemListener = p -> {
        context.startActivity(new Intent(context, ProposalActivity.class)
                .putExtra("proposal", proposals.get(p))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    };

    @Override
    protected void onPostExecute(ArrayList<Proposal> proposals) {
        super.onPostExecute(proposals);
        this.proposals = proposals;
        rvAdapter = new ProposalViewAdapter(layoutId, proposals);
        rvAdapter.setOnItemClickListener(itemListener);
        rv.setAdapter(rvAdapter);
        if (refreshLayout != null)
            refreshLayout.setRefreshing(false);
    }
};

