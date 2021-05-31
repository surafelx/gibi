package com.srfl.gibi.ui.student;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class StudentRepo {
    private static StudentRepo instance;
    private ArrayList<StudentData> arrayList  = new ArrayList<>();
    private ArrayList<String> existingList = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "StudentRepo";


    private static OnDataAdded onDataAdded;

    public static StudentRepo getInstance(Context context){
        if(instance == null){
            instance = new StudentRepo();
        }
        onDataAdded = (OnDataAdded) context;
        return instance;
    }

    public MutableLiveData<ArrayList<StudentData>> getStudentData(){
        loadStudentData();
        MutableLiveData<ArrayList<StudentData>> data = new MutableLiveData<>();
        data.setValue(arrayList);
        return data;
    }

    private void loadStudentData() {
        Log.e(TAG, "Starting to Load Data.");
        db.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot documentSnapshot: list){
                        if(documentSnapshot.getString("isStudent") != null){
                            Log.e(TAG, documentSnapshot.getString("name"));
                            Log.e(TAG, documentSnapshot.getString("studentId"));
                            if(existingList.contains(documentSnapshot.getString("studentId"))){
                                   continue;
                               }
                            arrayList.add(documentSnapshot.toObject(StudentData.class));
                            existingList.add(documentSnapshot.getString("studentId"));

                        }
                    }
                    Log.e(TAG, "OnSuccess: Added");
//                    onDataAdded.added();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure", e);
            }
        });
    }
}
