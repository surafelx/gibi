package com.srfl.gibi.ui.student;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.srfl.gibi.R;

import java.util.ArrayList;

public class StudentFragment extends Fragment implements OnDataAdded {

    private StudentViewModel studentViewModel;
    private ArrayList<StudentData> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    private static final String TAG = "StudentFragment";



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studentViewModel =
                new ViewModelProvider(this).get(StudentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_student, container, false);
        try {
            studentAdapter = new StudentAdapter(studentViewModel.getStudentData().getValue(), getActivity());
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
        }
        studentAdapter.notifyDataSetChanged();

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(studentAdapter);
        return root;
    }

    public void added(){
        studentAdapter.notifyDataSetChanged();
    }


}