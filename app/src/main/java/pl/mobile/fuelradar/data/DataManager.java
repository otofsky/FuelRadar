package pl.mobile.fuelradar.data;

import android.util.Log;

import pl.mobile.fuelradar.data.model.places.Response;
import pl.mobile.fuelradar.data.remote.HttpInvoker;
import rx.Observable;

/**
 * Created by zjuroszek on 10.06.16.
 */
public class DataManager {

    public Observable<Response> getNearbyStations(String location, int radius){
        return HttpInvoker.getInstance().getNearby(location,radius);
    }

}
