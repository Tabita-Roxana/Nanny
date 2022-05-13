package com.example.nanny.ui.availability;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nanny.databinding.FragmentAvailabilityBinding;
import com.example.nanny.model.User;
import com.example.nanny.model.WorkingDay;
import com.example.nanny.model.WorkingWeek;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AvailabilityFragment extends Fragment {

    private FragmentAvailabilityBinding binding;
    private DatabaseReference database;
    private FirebaseUser currentUser;

    private RecyclerView recyclerView;
    private WorkingDayAdapter workingDayAdapter;
    private Button save;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AvailabilityViewModel availabilityViewModel =
                new ViewModelProvider(this).get(AvailabilityViewModel.class);

        binding = FragmentAvailabilityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAvailability;
        availabilityViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        database = FirebaseDatabase.getInstance().getReference("users");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        save = binding.saveButton;
        recyclerView = binding.recyclerViewAvailability;

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));

        database.child(currentUser.getUid()).child("workingWeek").addValueEventListener(new ValueEventListener() {
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

        createListeners();

        return root;
    }


    private void createListeners() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.child(currentUser.getUid()).child("workingWeek").setValue(workingDayAdapter.getWorkingWeek());
            }
        });
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
