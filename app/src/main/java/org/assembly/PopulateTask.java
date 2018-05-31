package org.assembly;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class PopulateTask extends AsyncTask<Void, Void, ArrayList<Proposal>> {
    private RecyclerView rv;
    private ProposalViewAdapter rvAdapter;
    private APIHandler api;
    private int layoutId;

    public PopulateTask(RecyclerView rv, ProposalViewAdapter rvAdapter, APIHandler api, int layoutId) {
        this.rv = rv;
        this.rvAdapter = rvAdapter;
        this.api = api;
        this.layoutId = layoutId;
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

    @Override
    protected void onPostExecute(ArrayList<Proposal> proposals) {
        super.onPostExecute(proposals);
        rvAdapter = new ProposalViewAdapter(layoutId, proposals);
        rv.setAdapter(rvAdapter);
    }
};

