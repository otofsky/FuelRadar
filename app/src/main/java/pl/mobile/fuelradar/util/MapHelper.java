package pl.mobile.fuelradar.util;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import pl.mobile.fuelradar.data.model.places.Geometry;
import pl.mobile.fuelradar.data.model.places.Location;
import pl.mobile.fuelradar.data.model.places.Response;
import pl.mobile.fuelradar.data.model.places.Result;

/**
 * Created by zjuroszek on 13.06.16.
 */
public class MapHelper {


    private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);

    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);

    private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);

    private static final LatLng PERTH = new LatLng(-31.95285, 115.85734);




    public static List<LatLng> generateLocations(Response fueilingStationResponse) {
        List<LatLng> latLngList = new ArrayList<LatLng>();
        latLngList.add(MELBOURNE);
        latLngList.add(SYDNEY);
        latLngList.add(ADELAIDE);
        latLngList.add(PERTH);


       /* List<Result> resultList = fueilingStationResponse.getResults();
        for (Result r : resultList) {
            Geometry geometry = r.getGeometry();
            Location location = geometry.getLocation();
            latLngList.add(new LatLng(location.getLat(), location.getLng()));
        }*/
        return latLngList;
    }


}
