package com.example.nanny.ui.edit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nanny.model.UserDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditViewModel extends ViewModel {

    private DatabaseReference dbRef;

    private final MutableLiveData<String> mText;
    private MutableLiveData<String> name;
    private MutableLiveData<String> description;
    private MutableLiveData<String> address;
    private MutableLiveData<String> age;
    private MutableLiveData<String> city;

    public EditViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Edit your profile!");

        name = new MutableLiveData<>();
        description = new MutableLiveData<>();
        address = new MutableLiveData<>();
        age = new MutableLiveData<>();
        city = new MutableLiveData<>();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("users");

        dbRef.child(currentUser.getUid()).child("userDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserDetails userDetails = snapshot.getValue(UserDetails.class);
                name.setValue(userDetails.getName());
                description.setValue(userDetails.getDescription());
                address.setValue(userDetails.getAddress());
                age.setValue(userDetails.getAge());
                city.setValue(userDetails.getCity());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getCityText() {
        return city;
    }
    public LiveData<String> getNameText() {
        return name;
    }
    public LiveData<String> getDescriptionText() {
        return description;
    }
    public LiveData<String> getAddressText() {
        return address;
    }
    public LiveData<String> getAgeText() {
        return age;
    }

    public void editProfile(UserDetails userDetails)
    {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid()).child("userDetails").updateChildren(userDetails.toMap());

    }
}