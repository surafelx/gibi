package com.srfl.gibi.ui.instructor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InstructorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InstructorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is instructor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}