package com.wangyh2116.sunnyweather.ui.place;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wangyh2116.sunnyweather.R;
import com.wangyh2116.sunnyweather.logic.model.PlaceResponse;

public class PlaceFragment extends Fragment {
    PlaceViewModel viewModel;
    private PlaceAdapter placeAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_place,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel= ViewModelProviders.of(this).get(PlaceViewModel.class);
        final RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        EditText searchPlaceEdit=view.findViewById(R.id.searchPlaceEdit);
        final ImageView bgImageView=view.findViewById(R.id.bgImageView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        placeAdapter = new PlaceAdapter(this,viewModel.getPlaceList());
        recyclerView.setAdapter(placeAdapter);
        searchPlaceEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content=s.toString();
                if(!content.isEmpty()){
                    viewModel.searchPlace(content);
                }else {
                    recyclerView.setVisibility(View.GONE);
                    bgImageView.setVisibility(View.VISIBLE);
                    viewModel.getPlaceList().clear();
                    placeAdapter.notifyDataSetChanged();
                }
            }
        });
        viewModel.getPlaceLiveData().observe(PlaceFragment.this, new Observer<PlaceResponse>() {
            @Override
            public void onChanged(final PlaceResponse placeResponse) {
                if(placeResponse.isOk()){
                            recyclerView.setVisibility(View.VISIBLE);
                            bgImageView.setVisibility(View.GONE);
                            viewModel.getPlaceList().clear();
                            viewModel.getPlaceList().addAll(placeResponse.getPlaces());
                            placeAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getActivity(),"未能查询到任何地点",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}