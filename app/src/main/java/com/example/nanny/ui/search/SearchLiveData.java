package com.example.nanny.ui.search;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.nanny.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class SearchLiveData extends LiveData<List<User>> {

    private final ValueEventListener listener = new ValueEventListener() {

        final StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        final List<User> users = new ArrayList<>();

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                User user = dataSnapshot.getValue(User.class);
                storageReference.child("users").child(dataSnapshot.getKey()).child("profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        user.getUserDetails().setPicture(uri.toString());
                        users.add(user);
                    }
                });

            }
            setValue(users);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    DatabaseReference databaseReference;

    public SearchLiveData(DatabaseReference ref) {
        databaseReference = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}
