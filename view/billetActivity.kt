package com.example.ecotansit.view

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecotansit.R
import com.example.ecotansit.Service.BilletService
import com.example.ecotansit.adapters.BilletAdapter
import com.example.ecotansit.models.Billet
import com.example.ecotansit.models.BilletServiceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class billetActivity :  AppCompatActivity(), BilletAdapter.OnItemClickListener  {
    lateinit var billetAdapter: BilletAdapter
    lateinit  var  billetrecyclerView: RecyclerView
    private var billetList: ArrayList<Billet> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding
        setContentView(R.layout.billet_item)
        billetrecyclerView= findViewById(R.id.recycler_view_billets)

        // Configurer le RecyclerView
        val layoutManager = LinearLayoutManager(this)
        billetrecyclerView.setLayoutManager(layoutManager)

        // Initialiser et configurer l'adaptateur
        billetAdapter = BilletAdapter()
        billetrecyclerView.adapter = billetAdapter // Associer l'adaptateur au RecyclerView
        billetAdapter.setBillets(billetList)   // Configurer l'adaptateur pour RecyclerView avec la liste d'achats


        loadBilletData()
    }
    private fun loadBilletData() {
        val apiService = BilletService.create()

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                Log.d("tag", "test")
                val response = apiService.getAllBillets()
                Log.d("teagggggggggg", "${response.body()}")

                if (response.isSuccessful) {
                    val billetServiceResponse: BilletServiceResponse? = response.body()

                    if (billetServiceResponse != null) {
                        billetList = ArrayList(billetServiceResponse.billets)
                        billetAdapter.setBillets(billetList)
                        Log.d("billetList", "$billetList")
                        // showToast("History loaded successfully.")
                    } else {
                        showToast("Error: BilletServiceResponse is null.")
                    }
                } else {
                    when (response.code()) {
                        401 -> {
                            // Code 401 : Unauthorized
                            // Redirect the user to the login page or show an error message
                            // showToast("Unauthorized. Redirecting to login.")
                        }
                        404 -> {
                            // Code 404 : Not Found
                            // showToast("History not found.")
                        }
                        else -> {
                            // Show a generic error message
                            // showToast("An error occurred.")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "An error occurred.", e)
                showToast("An error occurred. Please try again later. ${e.message}")
            }finally {
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
    private fun styleTextView(textView: TextView, partOne: String, partTwo: String) {
        val spannableString = SpannableString(partOne + partTwo)

        spannableString.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0,
            partOne.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#44F1A6")),
            partOne.length,
            spannableString.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView.text = spannableString
    }
    override fun onItemClick(billet: Billet) {
        Log.d("c","hello")
    }
}