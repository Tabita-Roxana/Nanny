package com.example.nanny.ui.availability;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AvailabilityViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AvailabilityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Register your availability!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
