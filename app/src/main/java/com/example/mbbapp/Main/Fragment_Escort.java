package com.example.mbbapp.Main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mbbapp.API_Login.LoginActivity;
import com.example.mbbapp.Main.Model.EscortModel;
import com.example.mbbapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_Escort extends Fragment {
    private RecyclerView recyclerView;
    private List<EscortModel> escortModelList;
    private ArrayList<EscortModel> resultsData = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment__escort, container, false);

        recyclerView = view.findViewById(R.id.recycleView_escort);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        escortModelList = new ArrayList<>();
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        showListEscort();

        return view;
    }

    private void showListEscort(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_Interface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        API_Interface api_interface = retrofit.create(API_Interface.class);

        api_interface.getAllEscort(LoginActivity.token).enqueue(new Callback<List<EscortModel>>() {
            @Override
            public void onResponse(Call<List<EscortModel>> call, Response<List<EscortModel>> response) {
                escortModelList = response.body();
                Escort_Adapter escort_adapter = new Escort_Adapter(escortModelList);
                recyclerView.setAdapter(escort_adapter);
            }

            @Override
            public void onFailure(Call<List<EscortModel>> call, Throwable t) {

            }
        });
    }
}