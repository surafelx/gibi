package com.srfl.gibi.ui.student;

import android.content.Context;
import android.text.method.NumberKeyListener;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class StudentViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<StudentData>> liveData;
    private static final String TAG = "StudentViewModel";
    private Context context;

    public void init(Context context){
        if(liveData != null) {
            return;
        }
        this.context = context;
        liveData = StudentRepo.getInstance(context).getStudentData();

    }

    public LiveData<ArrayList<StudentData>> getStudentData() {
        liveData = StudentRepo.getInstance(context).getStudentData();
        return liveData;
    }
}