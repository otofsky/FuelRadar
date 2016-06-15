package pl.mobile.fuelradar.ui.home;

import android.os.Handler;
import android.util.Log;

import java.util.List;


import pl.mobile.fuelradar.data.DataManager;
import pl.mobile.fuelradar.data.Service;
import pl.mobile.fuelradar.data.model.FueilingStation;

import pl.mobile.fuelradar.data.model.places.Response;
import pl.mobile.fuelradar.ui.base.BasePresenter;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zjuroszek on 10.05.16.
 */
public class NearbyPresenter extends BasePresenter<NearbyMvpView> {

    private String TAG = "WeatherPresenter";

    private DataManager mDataManager;
    private Subscription mSubscription;


    public NearbyPresenter() {
        if (mDataManager == null) {
            mDataManager = new DataManager();
        }
    }

    @Override
    public void attachView(NearbyMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    public void loadNearbyFuelStationsByLocation(String location, int radius) {
        Log.d(TAG, "loadNearbyFuelStationsByLocation: ");
        checkViewAttached();
        mSubscription =  mDataManager.getNearbyStations(location, radius)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext:  showCurrentWeather" + response.toString());
                        getMvpView().showNearbyFuelStationsByLocation(response);
                    }
                });
    }

/*    public void loadNearby() {
        getMvpView().setProgressIndicator(true);

        final List<FueilingStation> list = Service.getServiceInstance().getNearbyFueilingStations();
      //  getMvpView().showFuelingStations(list);
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
    }*/

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
