package com.mikeescom.challenge.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mikeescom.challenge.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class GraphFragment extends Fragment {
    int position;
    private PieChart pieChart;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        GraphFragment tabFragment = new GraphFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieChart = view.findViewById(R.id.pie_chart);
        setData();
    }

    private void setData() {
        pieChart.addPieSlice(new PieModel(
                getResources().getString(R.string.ecuador),
                30,
                getResources().getColor(R.color.Ecuador)));
        pieChart.addPieSlice(new PieModel(
                getResources().getString(R.string.brasil),
                10,
                getResources().getColor(R.color.Brasil)));
        pieChart.addPieSlice(new PieModel(
                getResources().getString(R.string.argentina),
                10,
                getResources().getColor(R.color.Argentina)));
        pieChart.addPieSlice(new PieModel(
                getResources().getString(R.string.peru),
                9,
                getResources().getColor(R.color.Peru)));
        pieChart.addPieSlice(new PieModel(
                getResources().getString(R.string.colombia),
                8,
                getResources().getColor(R.color.Colombia)));
        pieChart.addPieSlice(new PieModel(
                getResources().getString(R.string.bolivia),
                8,
                getResources().getColor(R.color.Bolivia)));
        pieChart.addPieSlice(new PieModel(
                getResources().getString(R.string.others),
                25,
                getResources().getColor(R.color.Others)));

        pieChart.startAnimation();
    }
}
