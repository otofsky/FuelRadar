package pl.mobile.fuelradar.data.remote;


import android.util.Log;

import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import pl.mobile.fuelradar.Constants;
import pl.mobile.fuelradar.data.model.places.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;


public class HttpInvoker {
    private String TAG = "HttpInvoker";
    private static HttpInvoker httpsInvoker;

    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670,151.1957&radius=500&types=food&name=cruise&key=AIzaSyDaWsv4OfAZEVu6bxSA62cSoDFOxoGCmIo



    private HttpInterface weatherInterface;

    private HttpInvoker() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .client(client)
                .build();

        weatherInterface = retrofit.create(HttpInterface.class);


    }

    public static HttpInvoker getInstance() {
        if (httpsInvoker == null) {
            httpsInvoker = new HttpInvoker();
        }
        return httpsInvoker;
    }


    public Observable<Response> getNearby() {
        Log.d(TAG, "getNearbyStations: ");
        return weatherInterface.getNearby();
    }

}
