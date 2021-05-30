package com.srfl.gibi.ui.department;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DepartmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DepartmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is department fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}