package com.example.nanny.ui.search;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nanny.model.User;

import java.util.List;

public class SearchViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private SearchLiveData list;


    public SearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Search nanny: ");
//        list = new SearchLiveData();


    }

    public LiveData<String> getText() {
        return mText;
    }


}
