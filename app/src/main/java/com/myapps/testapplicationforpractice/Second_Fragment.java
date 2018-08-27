package com.myapps.testapplicationforpractice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Second_Fragment extends Fragment {
    public static final String TAG = "Second_Fragment_Tag";
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, null, false);
        Button button = v.findViewById(R.id.button_hello);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((TextView)getActivity().findViewById(R.id.changing_text)).setText("Hello");
            }
        });
        return v;
    }
}
