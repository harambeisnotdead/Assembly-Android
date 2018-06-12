package org.assembly.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.assembly.R;
import org.assembly.tasks.PopulateTask;
import org.assembly.views.recycler.ProposalViewAdapter;

public class ProposalsFragment extends Fragment {
    private TabLayout tabs;
    private RecyclerView rv;
    private RecyclerView.LayoutManager rvManager;
    private ProposalViewAdapter rvAdapter;
    private Context context;
    private SwipeRefreshLayout refreshLayout;

    private static class TabOptions {
        public static final int DEBATING = 0;
        public static final int VOTING = 1;
    }

    private TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(Tab tab) {
            switch (tab.getPosition()) {
                case TabOptions.DEBATING:
                    new PopulateTask(rv, rvAdapter, R.layout.item_proposal_debate,
                                     context, null).execute();
                    break;
                case TabOptions.VOTING:
                    new PopulateTask(rv, rvAdapter, R.layout.item_proposal_vote,
                                     context, null) .execute();
                    break;
            }
        }

        @Override
        public void onTabUnselected(Tab tab) {}

        @Override
        public void onTabReselected(Tab tab) {}
    };

    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        if (tabs.getSelectedTabPosition() == TabOptions.DEBATING)
            new PopulateTask(rv, rvAdapter, R.layout.item_proposal_debate,
                             context, refreshLayout).execute();
        else
            new PopulateTask(rv, rvAdapter, R.layout.item_proposal_vote,
                             context, refreshLayout).execute();
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proposals, container, false);
        context = getContext();
        tabs = view.findViewById(R.id.tabs);
        rv = view.findViewById(R.id.recycler_view);
        rvManager = new LinearLayoutManager(context);
        rv.setLayoutManager(rvManager);
        tabs.addOnTabSelectedListener(tabListener);
        refreshLayout = view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(refreshListener);
        new PopulateTask(rv, rvAdapter, R.layout.item_proposal_debate,
                         context, null).execute();
        return view;
    }
}
