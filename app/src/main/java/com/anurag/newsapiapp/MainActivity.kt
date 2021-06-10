package com.anurag.newsapiapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
//     val container: View = findViewById(R.id.container)
    private val articles = mutableListOf<Articles>()
    lateinit var adapter: NewsAdapter
    var pageNum = 1
    var totalResult = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter = NewsAdapter(this@MainActivity, articles)
        val recyclerView: RecyclerView = findViewById(R.id.newsList)
        recyclerView.adapter = adapter

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(isPagerMode = true)
        layoutManager.setPagerFlingVelocity(3000)
//        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
//            override fun onItemChanged(position: Int) {
//                container.setBackgroundColor(Color.parseColor(ColorPiker.getColor()))
//
//            }
//        })

        recyclerView.layoutManager = layoutManager




        getNews()
    }

    private fun getNews() {
        val news = NewsServices.newInstance.getHeadline("In", pageNum)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news = response.body()
                if (news != null) {
                    Log.d("hello", news.toString())
                    totalResult = news.totalResult
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {

                Log.d("hello", "error in Fetching")
                Toast.makeText(this@MainActivity, "error in Fetching", Toast.LENGTH_SHORT).show();
            }
        })
    }
}



