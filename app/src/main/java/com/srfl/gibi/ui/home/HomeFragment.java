package com.srfl.gibi.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.srfl.gibi.R;
import com.srfl.gibi.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.bAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Fragment studentCreateEdit = new StudentCreateEdit();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, studentCreateEdit);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                onDestroy();
            }
        });
    }
    public void onDestroy(){
        super.onDestroy();
        binding = null;
    }
}