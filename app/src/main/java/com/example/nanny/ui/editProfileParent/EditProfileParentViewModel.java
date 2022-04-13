package com.example.nanny.ui.editProfileParent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditProfileParentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EditProfileParentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is edit profile parent fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}