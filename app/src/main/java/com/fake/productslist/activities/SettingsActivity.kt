package com.fake.productslist.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.fake.productslist.R
import com.fake.productslist.fragments.PrefsFragment

import kotlinx.android.synthetic.main.a_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_settings)
//        addPreferencesFromResource(R.xml.prefs)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction().replace(R.id.contentset,PrefsFragment()).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
