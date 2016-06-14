package pl.mobile.fuelradar.data.remote;


import pl.mobile.fuelradar.data.model.places.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zjuroszek on 20.05.16.
 */
public interface HttpInterface {
    //http://api.openweathermap.org/data/2.5/weather?q=Cieszyn&APPID=ab6b80c5f1d39e5f3b6fac39594910c4

   // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670,151.1957&radius=500&types=food&name=cruise&key=AIzaSyDaWsv4OfAZEVu6bxSA62cSoDFOxoGCmIo
/*
    @GET("weather?&APPID=ab6b80c5f1d39e5f3b6fac39594910c4&units=metric")
    Observable<Response> getNearby(@Query("q") String city);*/

    @GET("json?&types=food&name=cruise&key=AIzaSyDaWsv4OfAZEVu6bxSA62cSoDFOxoGCmIo")
    Observable<Response> getNearby(@Query("location") String latitude,@Query("radius") int radius );

}
