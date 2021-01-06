package com.wangyh2116.sunnyweather.ui.place;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.wangyh2116.sunnyweather.R;
import com.wangyh2116.sunnyweather.logic.model.Place;
import com.wangyh2116.sunnyweather.logic.model.Weather;
import com.wangyh2116.sunnyweather.ui.weather.WeatherActivity;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView placeName=null;
        private TextView placeAddress=null;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeAddress=itemView.findViewById(R.id.placeAddress);
            placeName=itemView.findViewById(R.id.placeName);
        }
    }
    private PlaceFragment fragment=null;
    private List<Place> placeList=null;

    public PlaceAdapter(PlaceFragment fragment, List<Place> placeList) {
        super();
        this.fragment = fragment;
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Place place=placeList.get(position);
                Activity activity=fragment.getActivity();
                if(activity instanceof WeatherActivity){
                    ((WeatherActivity) activity).getDrawerLayout().closeDrawers();
                    ((WeatherActivity) activity).getViewModel().setLocationLng(place.getLocation().getLng());
                    ((WeatherActivity) activity).getViewModel().setLocationLat(place.getLocation().getLat());
                    ((WeatherActivity) activity).getViewModel().setPlaceName(place.getName());
                    ((WeatherActivity) activity).refreshWeather();
                }else {
                    WeatherActivity.getPlaceIntent(parent.getContext(), place.getLocation().getLng(), place.getLocation().getLat(), place.getName());
                    activity.finish();
                }
                fragment.viewModel.savePlace(place);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place place=placeList.get(position);
        holder.placeName.setText(place.getName());
        holder.placeAddress.setText(place.getAddress());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }


}
