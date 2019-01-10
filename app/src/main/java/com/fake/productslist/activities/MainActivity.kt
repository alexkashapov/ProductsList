package com.fake.productslist.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.fake.productslist.R
import com.fake.productslist.fragments.AboutFragment
import com.fake.productslist.fragments.BarCodeFragment
import com.fake.productslist.fragments.MainListFragment
import kotlinx.android.synthetic.main.a_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {

    val date = Date()
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.US).format(date)
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)
        setSupportActionBar(toolbar)


//        fab.setOnClickListener {
//
//            supportFragmentManager.beginTransaction().add(R.id.content, BarCodeFragment(), "").commit()
//            Snackbar.make(fab, "$date\n$format", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }


//        supportActionBar!!.title = format
//        val builder = AlertDialog.Builder(this)
//                .setView(layoutInflater.inflate(R.layout.d_newlist,null))
//                .setTitle("Новый список")
//                .setPositiveButton(
//                        "ok", DialogInterface.OnClickListener { dialog, id ->
//                    val intent = Intent(this,CurrentProductListActivity::class.java)
//                    intent.putExtra("name", editText.text)
//                    startActivity(intent)
//                    // User clicked OK button
//                })
//                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id -> })
//                .create()
//        builder.show()
//        editText = builder.findViewById<EditText>(R.id.new_list_name)!!
//        editText.setText("Список $format")


        supportFragmentManager
                .beginTransaction()
                .add(R.id.content, MainListFragment(), "")
                .commit()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
//            R.id.nav_camera -> {
//                // Handle the camera action
//            }
            R.id.nav_bar_code -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.content,BarCodeFragment())
                        .commit()
            }
            R.id.nav_about -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.content, AboutFragment(),"")
                        .commit()
            }
            R.id.nav_lists -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.content,MainListFragment())
                        .commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
