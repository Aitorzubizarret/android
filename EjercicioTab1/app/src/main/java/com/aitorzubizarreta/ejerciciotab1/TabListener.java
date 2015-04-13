package com.aitorzubizarreta.ejerciciotab1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by cursomovil on 10/04/15.
 */
public  class TabListener<T extends Fragment> implements ActionBar.TabListener {

    private Fragment fragment;
    private Activity activity;
    private Class<T> fragmentClass;
    private int fragmentContainer;

    public TabListener(Activity activity, int fragmentContainer, Class<T> fragmentClass) {
        this.activity = activity;
        this.fragmentContainer = fragmentContainer;
        this.fragmentClass = fragmentClass;
    }

    // Se ejecuta cuando se pulsa un tab
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (fragment == null) {
            String fragmentName = fragmentClass.getName();
            fragment = Fragment.instantiate(activity, fragmentName);
            ft.add(fragmentContainer, fragment, fragmentName);
        } else {
            ft.attach(fragment);
        }
    }

    // Se ejecuta cuando se pulsa en otro tab que no es el que esta "activo"
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (fragment != null) {
            ft.detach(fragment);
        }
    }

    // Se ejecuta cuando se pulsa sobre el tab que ya esta seleccionado
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (fragment != null) {
            ft.attach(fragment);
        }
    }
}
