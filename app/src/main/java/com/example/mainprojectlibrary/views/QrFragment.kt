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
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mainprojectlibrary.databinding.QrFragmentBinding
import com.example.mainprojectlibrary.db.Book
import com.example.mainprojectlibrary.viewModel.BookViewModel
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import org.json.JSONObject
import java.util.UUID

@AndroidEntryPoint
class QrFragment : Fragment(),View.OnClickListener {
    private val viewModel : BookViewModel by viewModels()
    private lateinit var binding : QrFragmentBinding
    private lateinit var qrScan : IntentIntegrator
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QrFragmentBinding.inflate(inflater , container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        qrScan = IntentIntegrator(requireActivity())
//        qrScan?.setPrompt("")
//        qrScan?.setOrientationLocked(false)
//        qrScan?.initiateScan()
        binding.takeBookBtn.setOnClickListener(this)
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onClick(view: View) {
        qrScan?.setPrompt("Scan a qr code")
        qrScan?.setOrientationLocked(false)
        qrScan?.initiateScan()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult? =
            IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(requireActivity(), "Result Not Found", Toast.LENGTH_LONG).show()
            } else {
                try {
                    // Converting the data to json format
                    val obj = JSONObject(result.contents)

                    // Show values in UI.
                    binding.bookTitle.text = obj.getString("title")
                    binding.bookAuthor.text = obj.getString("author")
                    binding.bookDescription.text = obj.getString("desc")
                    val book : Book = Book(UUID.randomUUID().toString(),
                        binding.bookTitle.text as String?, binding.bookAuthor.text as String?,binding.bookDescription.text as String?)
                    viewModel.saveBook(book)
                } catch (e: JSONException) {
                    e.printStackTrace()

                    // Data not in the expected format. So, whole object as toast message.
                    Toast.makeText(requireActivity(), result.contents, Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}