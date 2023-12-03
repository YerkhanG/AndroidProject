package com.example.mainprojectlibrary.views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.example.mainprojectlibrary.R
import com.example.mainprojectlibrary.databinding.FragmentMainBinding
import com.google.zxing.integration.android.IntentIntegrator

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private var qrScan: IntentIntegrator? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater ,container, false)
        return binding.root
    }
    fun Fragment.navigate(fragmentClass: Class<Fragment>, bundle: Bundle? = null) {
        requireActivity().supportFragmentManager.commit {
            add(R.id.nav, fragmentClass, bundle, fragmentClass.name)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!allPermissionGranted()) {
            pushPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
        binding.bottomNavView.selectedItemId = R.id.page_1
        binding?.bottomNavView?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    Toast.makeText(requireContext(), "Already selected", Toast.LENGTH_SHORT).show()
                    return@setOnItemSelectedListener true

                }

                R.id.page_2 -> {
                    val intent = Intent(requireContext(), BookListActivity::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.page_3 -> {
                    findNavController().navigate(MainFragmentDirections.actionMainFragmentToQrFragment())
                    return@setOnItemSelectedListener true
                }

                else -> {
                    false
                }
            }
        }
    }
    private var pushPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(
                requireActivity(),
                if (it) "Permission granted" else "Permission NOT granted",
                Toast.LENGTH_SHORT
            ).show()
        }

    private fun allPermissionGranted() =
        ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
}