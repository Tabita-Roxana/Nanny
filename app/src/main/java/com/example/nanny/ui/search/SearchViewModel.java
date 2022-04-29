package com.example.nanny.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nanny.model.User;

import java.util.List;

public class SearchViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<User>> allNannies;

    public SearchViewModel() {
        allNannies = new MutableLiveData<>();
        mText = new MutableLiveData<>();
        mText.setValue("Search nanny: ");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
