package pl.mobile.fuelradar.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pl.mobile.fuelradar.R;
import pl.mobile.fuelradar.data.model.FueilingStation;
import pl.mobile.fuelradar.ui.home.listener.FuelItemListener;

/**
 * Created by zjuroszek on 13.05.16.
 */
public class FuelAdapter extends RecyclerView.Adapter<FuelAdapter.ViewHolder> {

    private List<FueilingStation> fueilingStationList;
    private FuelItemListener mFuelItemListener;

    public FuelAdapter(List<FueilingStation> fueilingStationList, FuelItemListener itemListener) {
        setList(fueilingStationList);
        mFuelItemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.layout_fuel_nearby, parent, false);
        return new ViewHolder(noteView, mFuelItemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        FueilingStation fueilingStation = fueilingStationList.get(position);
        viewHolder.address.setText(fueilingStation.getAddress());
        viewHolder.prices.setText(fueilingStation.getPrice());
    }

    public void replaceData(List<FueilingStation> notes) {
        setList(notes);
        notifyDataSetChanged();
    }

    private void setList(List<FueilingStation> fueilingStationList) {
       // fueilingStationList = checkNotNull(fueilingStationList);
        this.fueilingStationList = fueilingStationList;
    }

    @Override
    public int getItemCount() {
        return fueilingStationList.size();
    }

    public FueilingStation getItem(int position) {
        return fueilingStationList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView address;
        public TextView prices;
        private FuelItemListener mItemListener;

        public ViewHolder(View itemView, FuelItemListener listener) {
            super(itemView);
            mItemListener = listener;
            address = (TextView) itemView.findViewById(R.id.txtAddress);
            prices = (TextView) itemView.findViewById(R.id.txtPrices);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FueilingStation fueilingStation = getItem(position);
            mItemListener.onFuelItemCLick(fueilingStation);
        }
    }
}
