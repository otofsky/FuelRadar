package pl.mobile.fuelradar.ui.home;

import java.util.List;

import pl.mobile.fuelradar.data.model.FueilingStation;
import pl.mobile.fuelradar.ui.base.MvpView;


public interface NearbyMvpView extends MvpView {

    void showFuelingStations(List<FueilingStation> fueilingStationList);

    void showFuelingStationEmpty();

    void showError();

    void setProgressIndicator(boolean active);


    void showFuelingStationListUi(String gId);


}
