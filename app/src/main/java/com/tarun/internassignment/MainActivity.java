package com.tarun.internassignment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tarun.internassignment.Database.CrewRepository;
import com.tarun.internassignment.Database.CrewViewModel;
import com.tarun.internassignment.Models.CrewDataModel;
import com.tarun.internassignment.Models.RoomCrewModel;
import com.tarun.internassignment.Retrofits.RetrofitInstance;
import com.tarun.internassignment.Retrofits.RetrofitInterface;
import com.tarun.internassignment.ViewAdapters.RecyclerViewAdapter;
import com.tarun.internassignment.ViewAdapters.RoomCrewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CrewRepository crewRep;
    private RecyclerView recyclerView;
    List<CrewDataModel> listResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton refresh = findViewById(R.id.refresh);
        ImageButton delete = findViewById(R.id.delete);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
        if (checkInternetConnection()){
            getData();
        }else{
            getFromRoom();
        }
    }

    private void deleteData() {
        crewRep = new CrewRepository(getApplication());
        crewRep.delete();
        Toast.makeText(this, "Data deleted Succesfully...", Toast.LENGTH_SHORT).show();
        getFromRoom();
    }

    private void refreshData() {
        if (checkInternetConnection()){
            getData();
        }else{
            getFromRoom();
        }
    }

    private void getFromRoom() {
        RoomCrewAdapter recyclerViewAdapter = new RoomCrewAdapter(this);
        recyclerView=findViewById(R.id.recyc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CrewViewModel crewViewModel = new CrewViewModel(getApplication());
        LiveData<List<RoomCrewModel>> roomCrewModels = crewViewModel.getAllData();
        roomCrewModels.observe(this, new Observer<List<RoomCrewModel>>() {
            @Override
            public void onChanged(List<RoomCrewModel> roomCrewModel) {
                if (!roomCrewModel.isEmpty()) {
                    recyclerViewAdapter.setCrews(roomCrewModel);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }
        });
    }

    private void getData() {
        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        retrofit2.Call<List<CrewDataModel>> listCall = retrofitInterface.getAllData();
        listCall.enqueue(new Callback<List<CrewDataModel>>() {
            @Override
            public void onResponse(Call<List<CrewDataModel>> call, Response<List<CrewDataModel>> response) {
                listResponse =response.body();
                crewRep = new CrewRepository(getApplication());
                crewRep.delete();

                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(listResponse, getApplicationContext(), getApplication());
                recyclerView=findViewById(R.id.recyc);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<CrewDataModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Some error Occured",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}