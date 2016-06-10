package pl.mobile.fuelradar.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.mobile.fuelradar.R;
import pl.mobile.fuelradar.data.model.FueilingStation;
import pl.mobile.fuelradar.ui.home.NearbyMvpView;

/**
 * Created by zjuroszek on 09.05.16.
 */
public class RouteFragment extends Fragment implements NearbyMvpView {

    Unbinder unbinder;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        unbinder = ButterKnife.bind(this, view);
        return  view;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showFuelingStations(List<FueilingStation> fueilingStationList) {

    }

    @Override
    public void showFuelingStationEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void showFuelingStationListUi(String gId) {

    }
}
