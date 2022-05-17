package com.example.nanny.ui.search;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nanny.databinding.FragmentSeeAvailabilityBinding;
import com.example.nanny.model.User;
import com.example.nanny.model.WorkingWeek;
import com.example.nanny.ui.availability.WorkingDayAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SeeAvailabilityFragment extends Fragment {

    private FragmentSeeAvailabilityBinding binding;
    private DatabaseReference database;
    private Button sendMessage;

    private RecyclerView recyclerView;
    private WorkingDayAdapter workingDayAdapter;

    public SeeAvailabilityFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SeeAvailabilityViewModel seeAvailabilityViewModel =
                new ViewModelProvider(this).get(SeeAvailabilityViewModel.class);

        binding = FragmentSeeAvailabilityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        database = FirebaseDatabase.getInstance().getReference("users");
        sendMessage = binding.sendMessageButton;

        recyclerView = binding.recyclerSeeAvailability;

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));



        String id = getArguments().getString("userId");

        DatabaseReference nannyUser =  database.child(id);

//        Log.i("hello",getArguments().getString("userEmail" ));

        nannyUser.child("workingWeek").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                WorkingWeek workingWeek1 = snapshot.getValue(WorkingWeek.class);
                workingDayAdapter.setWorkingWeek(workingWeek1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        workingDayAdapter = new WorkingDayAdapter();
        recyclerView.setAdapter(workingDayAdapter);


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDo
            }
        });

        return root;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}