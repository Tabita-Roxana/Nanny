package com.example.nanny.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nanny.databinding.FragmentSearchBinding;
import com.example.nanny.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private DatabaseReference database;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private ArrayList<User> users;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSearch;
        searchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        recyclerView = binding.recycleViewSearch;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        users = new ArrayList<>();
        searchAdapter = new SearchAdapter(users);
        recyclerView.setAdapter(searchAdapter);

//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    User user =  new User(dataSnapshot.getValue(User.class).getName(),dataSnapshot.getValue(User.class).getAge(),
//                            dataSnapshot.getValue(User.class).getCity(),dataSnapshot.getValue(User.class).getPicture());
//                    users.add(user);
//                }
//                searchAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        //TODO move unnecessary in View Model

        searchAdapter.setOnClickListener(student -> {
            //TODO set the listener
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
