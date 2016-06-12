package pl.mobile.fuelradar;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by zjuroszek on 12.06.16.
 */
public class App extends Application  {


    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
