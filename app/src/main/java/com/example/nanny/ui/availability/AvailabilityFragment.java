package com.example.nanny.ui.availability;

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
import com.example.nanny.model.WorkingDay;
import com.example.nanny.model.WorkingWeek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AvailabilityFragment extends Fragment {

    private FragmentAvailabilityBinding binding;

    private RecyclerView recyclerView;
    private WorkingDayAdapter workingDayAdapter;
    private WorkingWeek workingWeek;
    private Button save;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AvailabilityViewModel availabilityViewModel =
                new ViewModelProvider(this).get(AvailabilityViewModel.class);

        binding = FragmentAvailabilityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAvailability;
        availabilityViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        save = binding.saveButton;
        recyclerView = binding.recyclerViewAvailability;
        workingWeek =  new WorkingWeek();

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.HORIZONTAL,false));


        createWorkingDaysRecycler();
        createListeners();

        return root;
    }

    private void createWorkingDaysRecycler() {
        workingWeek.addDay(new WorkingDay(true, false,false,"M"));
        workingWeek.addDay(new WorkingDay(true, false,false,"T"));
        workingWeek.addDay(new WorkingDay(true, false,false,"W"));
        workingWeek.addDay(new WorkingDay(false, true,false,"T"));
        workingWeek.addDay(new WorkingDay(false, true,false,"F"));
        workingWeek.addDay(new WorkingDay(false, false,false,"S"));
        workingWeek.addDay(new WorkingDay(false, false,false,"S"));

        workingDayAdapter = new WorkingDayAdapter(workingWeek);
        recyclerView.setAdapter(workingDayAdapter);

    }

    private void createListeners() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 workingDayAdapter.setWorkingWeek(workingWeek);
                //Save working week into database
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
