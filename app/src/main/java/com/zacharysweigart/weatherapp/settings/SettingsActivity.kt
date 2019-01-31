package com.zacharysweigart.weatherapp.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.zacharysweigart.weatherapp.DelegatesExtensions
import com.zacharysweigart.weatherapp.R
import com.zacharysweigart.weatherapp.view.ToolbarManager
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.find

class SettingsActivity : AppCompatActivity(), ToolbarManager {

    companion object {
        val ZIP_CODE = "zipCode"
        val DEFAULT_ZIP = 75235L
    }

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    private var zipCode: Long by DelegatesExtensions.preference(this, ZIP_CODE, DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        enableHomeAsUp { onBackPressed() }

        toolbarTitle = getString(R.string.settings)

        cityCode.setText(zipCode.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        zipCode = cityCode.text.toString().toLong()
    }
}
