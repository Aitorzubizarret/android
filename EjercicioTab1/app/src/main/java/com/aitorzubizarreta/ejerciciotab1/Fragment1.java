package com.aitorzubizarreta.ejerciciotab1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        } else {
            View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
            return view;
        }
    }
}
