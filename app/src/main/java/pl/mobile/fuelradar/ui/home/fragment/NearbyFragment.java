package pl.mobile.fuelradar.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import pl.mobile.fuelradar.R;
import pl.mobile.fuelradar.data.model.FueilingStation;
import pl.mobile.fuelradar.data.model.places.Response;
import pl.mobile.fuelradar.ui.home.NearbyMvpView;
import pl.mobile.fuelradar.ui.home.NearbyPresenter;
import pl.mobile.fuelradar.ui.home.adapter.FuelAdapter;
import pl.mobile.fuelradar.ui.home.listener.FuelItemListener;

/**
 * Created by zjuroszek on 09.05.16.
 */
public class NearbyFragment extends Fragment implements NearbyMvpView {

    List<FueilingStation> fueilingStations = new ArrayList<FueilingStation>();
    NearbyPresenter nearbyPresenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    FuelAdapter mAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    BacgkroundThread bacgkroundThread;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
         ButterKnife.bind(this, view);
        nearbyPresenter = new NearbyPresenter();
        nearbyPresenter.attachView(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new FuelAdapter(fueilingStations, fuelItemListener);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fuelStation_list);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        // Pull-to-refresh
        swipeRefreshLayout =
                (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // mActionsListener.loadNotes(true);
            }
        });

        Log.d(TAG, "onCreateView: " + Looper.getMainLooper());

        bacgkroundThread = new BacgkroundThread();
        bacgkroundThread.start();
        return view;
    }


    FuelItemListener fuelItemListener = new FuelItemListener() {
        @Override
        public void onFuelItemCLick(FueilingStation fueilingStation) {

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {

       //nearbyPresenter.loadNearby();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        nearbyPresenter.detachView();
        bacgkroundThread.exit();
        super.onDestroy();
    }




    @Override
    public void showNearbyFuelStationsByLocation(Response fuelingStationResponse) {

    }

    @Override
    public void showFavoriteFuelStations(Response fuelingStationResponse) {

    }

    @Override
    public void showFuelStationsBySelectedRout(Response fuelingStationResponse) {

    }

  /*  @Override
    public void showFuelingStations(List<FueilingStation> fueilingStationList) {
        mAdapter.replaceData(fueilingStationList);
    }*/

    @Override
    public void showFuelingStationEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void setProgressIndicator(boolean active) {
        Log.d(TAG, "setProgressIndicator: ");
        swipeRefreshLayout.setRefreshing(active);
    }

    @Override
    public void showFuelingStationListUi(String gId) {

    }

    private static final int PROGRESS_START = 0;
    private static final int PROGRESS_END = 1;


    private class BacgkroundThread extends Thread {

        Handler handler;

        @Override
        public void run() {


            Looper l = Looper.myLooper();
            Looper lz = Looper.getMainLooper();
            if(l==lz){
                Log.d(TAG, "run:  true"  );
            }
            else{
                Log.d(TAG, "run:  false"  );
            }


            Looper.prepare();
            handler = new Handler();
            Looper.loop();
        }

        public void doWork() {

     /*       handler.post(new Runnable() {
                @Override
                public void run() {
                    Message message = uiHandler.obtainMessage(PROGRESS_START);
                    uiHandler.sendMessage(message);
                    Random random = new Random();
                    SystemClock.sleep(random.nextInt(5000));
                    Message message1 = uiHandler.obtainMessage(PROGRESS_END);
                    uiHandler.sendMessage(message1);
                }
            });*/
        }

        public void exit() {
            handler.getLooper().quit();
        }
    }

    private String TAG = "Nearby";

    private final Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PROGRESS_START:
                    Log.d(TAG, "handleMessage: PROGRESS_START");
                    break;

                case PROGRESS_END:
                    Log.d(TAG, "handleMessage: PROGRESS_END");
                    break;

            }
        }
    };


}


/*
private static final String EXTRA_TRIGGER_SYNC_FLAG =
        "uk.co.ribot.androidboilerplate.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";

@Inject MainPresenter mMainPresenter;
@Inject RibotsAdapter mRibotsAdapter;

@Bind(R.id.recycler_view)
RecyclerView mRecyclerView;

    */
/*****
 * Return an Intent to start this Activity.
 * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
 * only be set to false during testing.
 * MVP View methods implementation
 * MVP View methods implementation
 * MVP View methods implementation
 * MVP View methods implementation
 * MVP View methods implementation
 * MVP View methods implementation
 * MVP View methods implementation
 *****//*

    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(mRibotsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);
        mMainPresenter.loadRibots();

        if (getIntent().getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            startService(SyncService.getStartIntent(this));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMainPresenter.detachView();
    }

    */
/***** MVP View methods implementation *****//*


    @Override
    public void showRibots(List<Ribot> ribots) {
        mRibotsAdapter.setRibots(ribots);
        mRibotsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_ribots))
                .show();
    }

    @Override
    public void showRibotsEmpty() {
        mRibotsAdapter.setRibots(Collections.<Ribot>emptyList());
        mRibotsAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_ribots, Toast.LENGTH_LONG).show();
    }*/
