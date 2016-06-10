package pl.mobile.fuelradar.data;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by zjuroszek on 10.06.16.
 */
public class SyncService extends IntentService {

    public SyncService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
