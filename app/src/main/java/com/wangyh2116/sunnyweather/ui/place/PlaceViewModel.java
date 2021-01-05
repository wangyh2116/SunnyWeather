package com.wangyh2116.sunnyweather.ui.place;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.wangyh2116.sunnyweather.logic.Repository;
import com.wangyh2116.sunnyweather.logic.model.Place;
import com.wangyh2116.sunnyweather.logic.model.PlaceResponse;

import java.util.ArrayList;
import java.util.List;

public class PlaceViewModel extends ViewModel {
    private MutableLiveData<String> searchLiveData=new MutableLiveData<>();
    private List<Place> placeList=new ArrayList<>();

    public List<Place> getPlaceList() {
        return placeList;
    }

    public LiveData<PlaceResponse> getPlaceLiveData() {
        return placeLiveData;
    }

    private LiveData<PlaceResponse> placeLiveData= Transformations.switchMap(searchLiveData, new Function<String, LiveData<PlaceResponse>>() {
        @Override
        public LiveData<PlaceResponse> apply(String query) {
            return Repository.searchPlaces(query);
        }
    });
    public void searchPlace(String query){
        searchLiveData.setValue(query);
    }
}
