package com.pfe.agilestore.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RendezVousViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RendezVousViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}