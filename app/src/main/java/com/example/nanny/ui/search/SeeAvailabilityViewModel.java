package com.example.nanny.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SeeAvailabilityViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public SeeAvailabilityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
