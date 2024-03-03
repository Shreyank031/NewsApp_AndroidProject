package com.shrey.newsbee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

// MainActivity class responsible for displaying news
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Call function to fetch news data
        getNews()
    }

    // Function to fetch news data from the News API
    private fun getNews() {
        // Create a call to fetch headlines for a specific country (in this case, "in" for India) and page number
        val newsCall = NewsService.newsInterface.getHeadlines("in", 1)
        // Asynchronously execute the call
        newsCall.enqueue(object : retrofit2.Callback<News> {
            // Callback invoked when the request fails
            override fun onFailure(call: Call<News>, t: Throwable) {
                // Log error message along with the exception
                Log.d("Yallah", "Error in fetching News", t)
            }

            // Callback invoked when the request is successful and a response is received
            override fun onResponse(call: Call<News>, response: Response<News>) {
                // Extract the news data from the response body
                val news = response.body()
                // Check if the news data is not null
                if (news != null) {
                    // Log the retrieved news data
                    Log.d("Yallah", news.toString())
                }
            }
        })
    }
}
