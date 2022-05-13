package com.pfe.agilestore.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoriquesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HistoriquesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}