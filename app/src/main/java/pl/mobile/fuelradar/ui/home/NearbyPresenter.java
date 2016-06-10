package pl.mobile.fuelradar.ui.home;

import android.os.Handler;
import android.util.Log;

import java.util.List;


import pl.mobile.fuelradar.data.Service;
import pl.mobile.fuelradar.data.model.FueilingStation;

import pl.mobile.fuelradar.ui.base.BasePresenter;

/**
 * Created by zjuroszek on 10.05.16.
 */
public class NearbyPresenter extends BasePresenter<NearbyMvpView> {


    @Override
    public void attachView(NearbyMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();

    }

    public void loadNearby() {
        getMvpView().setProgressIndicator(true);

        final List<FueilingStation> list = Service.getServiceInstance().getNearbyFueilingStations();
        getMvpView().showFuelingStations(list);
        // getMvpView().setProgressIndicator(false);
        MyService service = new MyService();
        service.getResources(new Callback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d("NearbyPresenter", "onSuccess: " + s);

            }
            @Override
            public void onFailure(String s) {
                Log.d("NearbyPresenter", "onFailure: ");
            }
        });


    }


    public interface Callback<T> {
        void onSuccess(T t);

        void onFailure(T t);

    }

    private class MyService {
        Handler handler = new Handler();


        public String getResources(final Callback callback) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (true) {
                        callback.onSuccess("Hurra");
                    } else {
                        callback.onFailure(" lipa");
                    }
                }
            },1000);

            return "resource";
        }


    }


}
