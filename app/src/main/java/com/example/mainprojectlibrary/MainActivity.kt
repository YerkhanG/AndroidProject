package com.example.mainprojectlibrary

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mainprojectlibrary.databinding.ActivityMainBinding
import com.example.mainprojectlibrary.views.BookListActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.journeyapps.barcodescanner.CaptureActivity
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding

    //qr code scanner object
    private var qrScan: IntentIntegrator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavView.selectedItemId = R.id.page_1
        binding!!.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.page_1 -> {
                    Toast.makeText(this, "Already selected", Toast.LENGTH_SHORT).show()
                    return@setOnItemSelectedListener true

                }
                R.id.page_2 -> {
                    val intent = Intent(this,BookListActivity::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                else -> {false}
            }
        }

        if (!allPermissionGranted()) {
            pushPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        qrScan = IntentIntegrator(this)

        //attaching onclick listener
        binding.floatingActionButton.setOnClickListener(this)

    }

    //Getting the scan results
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult? =
            IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show()
            } else {
                binding.name.text = result.contents
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onClick(view: View) {
        qrScan!!.setPrompt("Scan a qr code")
        qrScan!!.setOrientationLocked(false)
        qrScan!!.initiateScan()
    }


    private var pushPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(
                this,
                if (it) "Permission granted" else "Permission NOT granted",
                Toast.LENGTH_SHORT
            ).show()
        }

    private fun allPermissionGranted() =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
}