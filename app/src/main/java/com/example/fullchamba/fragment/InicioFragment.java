package com.example.fullchamba.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fullchamba.R;

import static androidx.navigation.Navigation.findNavController;

public class InicioFragment extends Fragment {

    Button buttonLogInInicio;
    Button buttonSignUpInicio;

    public InicioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonLogInInicio = view.findViewById(R.id.buttonLogInInicio);
        buttonSignUpInicio = view.findViewById(R.id.buttonSignUpInicio);

        buttonLogInInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(v).navigate(R.id.logInFragment);
            }
        });

        buttonSignUpInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(v).navigate(R.id.signUpFragment);
            }
        });

    }

}