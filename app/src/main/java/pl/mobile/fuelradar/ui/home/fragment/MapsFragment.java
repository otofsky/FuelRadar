package pl.mobile.fuelradar.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.util.Iterator;

import butterknife.ButterKnife;
import pl.mobile.fuelradar.R;
import pl.mobile.fuelradar.data.model.places.Response;
import pl.mobile.fuelradar.ui.home.NearbyMvpView;
import pl.mobile.fuelradar.ui.home.NearbyPresenter;
import pl.mobile.fuelradar.util.MapHelper;

/**
 * Created by zjuroszek on 11.06.16.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback, NearbyMvpView {

  public   enum TYPE_TASK {
        NEAREST,
        FAVOURITE,
        ROUTE
    }

    public static final String TASK_ARGUMENT_ID = "TASK_ARGUMENT_ID";

    TYPE_TASK enumType;


    private Polyline mMutablePolyline;

    private Polyline mClickablePolyline;

    private View rootView;
    GoogleMap mMap;
    MapView mMapView;

    //https://developers.google.com/places/android-api/add-place#add-place

    NearbyPresenter nearbyPresenter;

    public static MapsFragment newInstance(TYPE_TASK taskId) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(TASK_ARGUMENT_ID, taskId);
        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enumType = (TYPE_TASK) savedInstanceState.getSerializable(TASK_ARGUMENT_ID);
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


    /*    public final double latitude;
        public final double longitude;
        */
    //@DebugLog
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        LatLng sydney = new LatLng(-34, 151);
        switch (enumType) {
            case NEAREST:
                nearbyPresenter.loadNearbyFuelStationsByLocation("-33.8670,151.1957", 1000);
                break;
            case FAVOURITE:
                nearbyPresenter.loadNearbyFuelStationsByLocation("-33.8670,151.1957", 1000);
                break;
            case ROUTE:
                nearbyPresenter.loadNearbyFuelStationsByLocation("-33.8670,151.1957", 1000);
                break;
        }
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

       /* mMap.addPolyline((new PolylineOptions())
                .add(MELBOURNE, ADELAIDE, PERTH));*/


        // Move the map so that it is centered on the mutable polyline.
        //   mMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));

        // Add a listener for polyline clicks that changes the clicked polyline's color.
    }


    @Override
    public void showNearbyFuelStationsByLocation(Response fuelingStationResponse) {
        Iterable<LatLng> points = MapHelper.generateLocations(fuelingStationResponse);
        Iterator var2 = points.iterator();
        while (var2.hasNext()) {
            LatLng var3 = (LatLng) var2.next();
            Log.d("MapFragment ", "" + var3.latitude);
            Log.d("MapFragment ", "" + var3.longitude);
        }
        mMap.addPolyline((new PolylineOptions())
                        //.add(MELBOURNE, ADELAIDE, PERTH)
                        .addAll(points)
        );
    }

    @Override
    public void showFavoriteFuelStations(Response fuelingStationResponse) {

    }

    @Override
    public void showFuelStationsBySelectedRout(Response fuelingStationResponse) {

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