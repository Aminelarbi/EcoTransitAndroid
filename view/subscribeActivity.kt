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
import com.example.ecotansit.Service.SubscribeService
import com.example.ecotansit.adapters.SubscribeAdapter
import com.example.ecotansit.models.Subscribe
import com.example.ecotansit.models.SubscribeServiceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class subscribeActivity : AppCompatActivity(), SubscribeAdapter.OnItemClickListener {
    lateinit var subscribeAdapter: SubscribeAdapter
    lateinit var subscribeRecyclerView: RecyclerView
    private var subscribeList: ArrayList<Subscribe> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding
        setContentView(R.layout.subscribe_item)
        subscribeRecyclerView = findViewById(R.id.recycler_view_subscribes)

        // Configure the RecyclerView
        val layoutManager = LinearLayoutManager(this)
        subscribeRecyclerView.layoutManager = layoutManager

        // Initialize and configure the adapter
        subscribeAdapter = SubscribeAdapter()
        subscribeRecyclerView.adapter = subscribeAdapter
        subscribeAdapter.setSubscribes(subscribeList)

        loadSubscribeData()
    }

    private fun loadSubscribeData() {
        val apiService = SubscribeService.create()

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                Log.d("tag", "test")
                val response = apiService.getAllSubscribes()
                Log.d("teagggggggggg", "${response.body()}")

                if (response.isSuccessful) {
                    val subscribeServiceResponse: SubscribeServiceResponse? = response.body()

                    if (subscribeServiceResponse != null) {
                        subscribeList = ArrayList(subscribeServiceResponse.subscribes)
                        subscribeAdapter.setSubscribes(subscribeList)
                        Log.d("subscribeList", "$subscribeList")
                        // showToast("History loaded successfully.")
                    } else {
                        showToast("Error: SubscribeServiceResponse is null.")
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
            } finally {
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

    override fun onItemClick(subscribe: Subscribe) {
        Log.d("c", "hello")
    }
}
