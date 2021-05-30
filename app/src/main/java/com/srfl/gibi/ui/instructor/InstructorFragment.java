package com.srfl.gibi.ui.instructor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.srfl.gibi.R;

public class InstructorFragment extends Fragment {

    private InstructorViewModel instructorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_instructor, container, false);
        final TextView textView = root.findViewById(R.id.text_instruct);
        instructorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}