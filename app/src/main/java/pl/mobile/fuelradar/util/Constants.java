package pl.mobile.fuelradar.util;

import pl.mobile.fuelradar.App;
import pl.mobile.fuelradar.R;

/**
 * Created by pliszka on 03.08.15.
 */
public class Constants {

    public final static boolean DEBUG_LOG = App.getInstance().getResources().getBoolean(R.bool.log_debug);
    public final static String ENDPOINT = App.getInstance().getString(R.string.endpoint);


}
