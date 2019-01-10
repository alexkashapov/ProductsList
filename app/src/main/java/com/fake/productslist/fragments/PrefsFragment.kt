package com.fake.productslist.fragments

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceManager
import com.fake.productslist.R


class PrefsFragment() : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//        PreferenceManager.setDefaultValues(getActivity(), R.xml.prefs, false);
        setPreferencesFromResource(R.xml.prefs,rootKey)
    }
}