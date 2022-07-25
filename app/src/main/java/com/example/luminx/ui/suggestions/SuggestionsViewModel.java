package com.example.luminx.ui.suggestions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SuggestionsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SuggestionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is suggestions fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}