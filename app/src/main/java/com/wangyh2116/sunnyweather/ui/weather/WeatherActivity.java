package com.wangyh2116.sunnyweather.ui.weather;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.wangyh2116.sunnyweather.R;
import com.wangyh2116.sunnyweather.logic.model.Sky;
import com.wangyh2116.sunnyweather.logic.model.Weather;
import com.wangyh2116.sunnyweather.logic.model.dailyresponse.Daily;
import com.wangyh2116.sunnyweather.logic.model.dailyresponse.LifeIndex;
import com.wangyh2116.sunnyweather.logic.model.dailyresponse.Skycon;
import com.wangyh2116.sunnyweather.logic.model.dailyresponse.Temperature;
import com.wangyh2116.sunnyweather.logic.model.realtimeresponse.Realtime;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {



    WeatherViewModel viewModel;
    public WeatherViewModel getViewModel() {
        return viewModel;
    }
    TextView placeName;
    TextView currentTemp;
    ImageView currentSky;
    TextView currentAQI;
    RelativeLayout nowLayout;
    LinearLayout forecastLayout;
    TextView coldRiskText;
    TextView dressingText;
    TextView ultravioletText;
    TextView carWashingText;
    ScrollView weatherLayout;
    SwipeRefreshLayout swipeRefresh;
    Button navBtn;
    DrawerLayout drawerLayout;
    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        viewModel= ViewModelProviders.of(this).get(WeatherViewModel.class);
        placeName=findViewById(R.id.placeName);
        currentTemp =findViewById(R.id.currentTemp);
        currentSky =findViewById(R.id.currentSky);
        currentAQI=findViewById(R.id.currentAQI);
        nowLayout=findViewById(R.id.nowLayout);
        forecastLayout=findViewById(R.id.forecastLayout);
        coldRiskText=findViewById(R.id.coldRiskText);
        dressingText=findViewById(R.id.dressingText);
        ultravioletText=findViewById(R.id.ultravioletText);
        carWashingText=findViewById(R.id.carWashingText);
        weatherLayout=findViewById(R.id.weatherLayout);
        swipeRefresh=findViewById(R.id.swipeRefresh);
        navBtn=findViewById(R.id.navBtn);
        drawerLayout=findViewById(R.id.drawerLayout);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        if(viewModel.getLocationLng().equals("")){
            viewModel.setLocationLng(getIntent().getStringExtra("location_lng"));
        }
        if(viewModel.getLocationLat().equals("")){
            viewModel.setLocationLat(getIntent().getStringExtra("location_lat"));
        }
        if(viewModel.getPlaceName().equals("")){
            viewModel.setPlaceName(getIntent().getStringExtra("place_name"));
        }
        viewModel.getWeatherLiveData().observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                if(weather.isOk()){
                    showWeatherInfo(weather);
                }else{
                    Toast.makeText(WeatherActivity.this,"无法成功获取天气信息",Toast.LENGTH_SHORT).show();
                    Log.e("WeatherAcitvity",weather.getMsg());
                }
                swipeRefresh.setRefreshing(false);
            }
        });

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        refreshWeather();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshWeather();
            }
        });
        navBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                InputMethodManager manager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(drawerView.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }
    public void refreshWeather(){
        viewModel.refreshWeather(viewModel.getLocationLng(),viewModel.getLocationLat());
        swipeRefresh.setRefreshing(true);
    }
    public static void getPlaceIntent(Context context,String lng, String lat, String placeName){
        Intent intent=new Intent(context,WeatherActivity.class);
        intent.putExtra("location_lng", lng);
        intent.putExtra("location_lat",lat);
        intent.putExtra("place_name",placeName);
        context.startActivity(intent);
    }
    private void showWeatherInfo(Weather weather){
        placeName.setText(viewModel.getPlaceName());
        Realtime realtime=weather.getRealtime();
        Daily daily=weather.getDaily();
        currentTemp.setText(realtime.getTemperature()+"℃");
        currentSky.setImageResource(Sky.getSky(realtime.getSkycon()).getIcon());
        currentAQI.setText("空气指数"+realtime.getAirQuality().getAqi().getChn());
        nowLayout.setBackgroundResource(Sky.getSky(realtime.getSkycon()).getBg());
        //forecast.xml
        forecastLayout.removeAllViews();
        int days=daily.getSkycon().size();
        for(int i=0;i<days;i++){
            Skycon skycon = daily.getSkycon().get(i);
            Temperature temperature=daily.getTemperature().get(i);
            View view= LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false);
            TextView dateInfo=view.findViewById(R.id.dateInfo);
            ImageView skyIcon=view.findViewById(R.id.skyIcon);
            TextView skyInfo=view.findViewById(R.id.skyInfo);
            TextView temperatureInfo = view.findViewById(R.id.temperatureInfo);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            dateInfo.setText(simpleDateFormat.format(skycon.getDate()));
            skyIcon.setImageResource(Sky.getSky(skycon.getValue()).getIcon());
            skyInfo.setText(Sky.getSky(skycon.getValue()).getInfo());
            temperatureInfo.setText(temperature.getMin()+" ~ "+temperature.getMax()+" ℃");

            forecastLayout.addView(view);
        }
        //life_index.xml
        LifeIndex lifeIndex=daily.getLifeIndex();
        coldRiskText.setText(lifeIndex.getColdRisk().get(0).getDesc());
        dressingText.setText(lifeIndex.getColdRisk().get(0).getDesc());
        ultravioletText.setText(lifeIndex.getUltraviolet().get(0).getDesc());
        carWashingText.setText(lifeIndex.getCarWashing().get(0).getDesc());
        weatherLayout.setVisibility(View.VISIBLE);
    }
}
