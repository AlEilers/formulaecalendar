package de.ae.formulaecalendar.app.view.settings

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import de.ae.formulaecalendar.R
import kotlinx.android.synthetic.main.toolbar.*

class MyPreferenceActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    val fragment = MyPreferenceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        //set Toolbar
        setSupportActionBar(toolbar_view)
        supportActionBar?.title = getString(R.string.activity_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fragmentManager.beginTransaction()
                .replace(R.id.preferences_fragment, fragment)
                .commit()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        fragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
