package com.example.nanny.ui.search;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.example.nanny.model.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private DatabaseReference database;
    private StorageReference storageReference;

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


        storageReference = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance().getReference("users");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user.getType().equals("nanny")) {
                        storageReference.child("users").child(dataSnapshot.getKey()).child("profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                user.getUserDetails().setPicture(uri.toString());
                                users.add(user);
                                searchAdapter.setUsersNanny(users);
                                searchAdapter.notifyDataSetChanged();

                            }
                        });
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchAdapter = new SearchAdapter(users);
        recyclerView.setAdapter(searchAdapter);


        //TODO move unnecessary in View Model

        searchAdapter.setOnClickListener(user -> {
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
