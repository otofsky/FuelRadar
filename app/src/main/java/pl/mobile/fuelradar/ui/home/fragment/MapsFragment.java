package pl.mobile.fuelradar.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import butterknife.ButterKnife;
import pl.mobile.fuelradar.R;
import pl.mobile.fuelradar.data.model.FueilingStation;
import pl.mobile.fuelradar.data.model.places.Response;
import pl.mobile.fuelradar.ui.home.NearbyMvpView;
import pl.mobile.fuelradar.ui.home.NearbyPresenter;
import pl.mobile.fuelradar.util.MapHelper;

/**
 * Created by zjuroszek on 11.06.16.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback, NearbyMvpView {

    private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);

    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);

    private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);

    private static final LatLng PERTH = new LatLng(-31.95285, 115.85734);

    private static final LatLng LHR = new LatLng(51.471547, -0.460052);

    private static final LatLng LAX = new LatLng(33.936524, -118.377686);

    private static final LatLng JFK = new LatLng(40.641051, -73.777485);

    private static final LatLng AKL = new LatLng(-37.006254, 174.783018);

    private Polyline mMutablePolyline;

    private Polyline mClickablePolyline;

    private View rootView;
    GoogleMap mMap;
    MapView mMapView;

    //https://developers.google.com/places/android-api/add-place#add-place


    NearbyPresenter nearbyPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nearbyPresenter = new NearbyPresenter();
    }

    //  @DebugLog
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            rootView = inflater.inflate(R.layout.fragment_map, container, false);
            nearbyPresenter.attachView(this);
            ButterKnife.bind(this, rootView);

            MapsInitializer.initialize(this.getActivity());
            mMapView = (MapView) rootView.findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
        } catch (InflateException e) {
            //Log.e(TAG, "Inflate exception");
        }
        return rootView;
    }


    //@DebugLog
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        nearbyPresenter.loadCurrentWeather();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    // @DebugLog
    @Override
    public void onDestroy() {
        super.onDestroy();
        nearbyPresenter.detachView();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setContentDescription("Google Map with polylines.");

        mMap.addPolyline((new PolylineOptions())
                .add(MELBOURNE, ADELAIDE, PERTH));


        // Move the map so that it is centered on the mutable polyline.
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));

        // Add a listener for polyline clicks that changes the clicked polyline's color.
    }


    @Override
    public void showNearbyFuelStationsByLocation(Response fueilingStatResponse) {
        Iterable<LatLng> points = MapHelper.generateLocations(fueilingStatResponse);
        mMap.addPolyline((new PolylineOptions())
                        .add(MELBOURNE, ADELAIDE, PERTH)
                        .addAll(points)
        );
    }

    @Override
    public void showFavoriteFuelStations(Response fueilingStatResponse) {

    }

    @Override
    public void showFuelStationsBySelectedRout(Response fueilingStatResponse) {

    }

    @Override
    public void showFuelingStations(List<FueilingStation> fueilingStationList) {

    }

    @Override
    public void showFuelingStationEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void showFuelingStationListUi(String gId) {

    }
}