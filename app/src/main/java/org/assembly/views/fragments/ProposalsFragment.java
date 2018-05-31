package org.assembly.views.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.assembly.APIHandler;
import org.assembly.tasks.PopulateTask;
import org.assembly.views.recycler.ProposalViewAdapter;
import org.assembly.R;

public class ProposalsFragment extends Fragment {
    private TabLayout tabs;
    private RecyclerView rv;
    private RecyclerView.LayoutManager rvManager;
    private ProposalViewAdapter rvAdapter;
    private APIHandler api;

    private static class TabOptions {
        public static final int DEBATING = 0;
        public static final int VOTING = 1;
    }

    private TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(Tab tab) {
            switch (tab.getPosition()) {
                case TabOptions.DEBATING:
                    new PopulateTask(rv, rvAdapter, api, R.layout.item_proposal_debate).execute();
                    break;
                case TabOptions.VOTING:
                    new PopulateTask(rv, rvAdapter, api, R.layout.item_proposal_vote).execute();
                    break;
            }
        }

        @Override
        public void onTabUnselected(Tab tab) {
        }

        @Override
        public void onTabReselected(Tab tab) {
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proposals, container, false);
        tabs = view.findViewById(R.id.tabs);
        api = new APIHandler(getContext());
        rv = view.findViewById(R.id.recycler_view);
        rvManager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(rvManager);
        tabs.addOnTabSelectedListener(tabListener);
        new PopulateTask(rv, rvAdapter, api, R.layout.item_proposal_debate).execute();
        return view;
    }
}
