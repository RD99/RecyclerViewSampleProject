package com.example.recyclerview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.recyclerview.databinding.ActivityMainBinding
import org.json.JSONObject


class MainActivity : AppCompatActivity(), CollegeDataAdapter.OnItemClickListener {
    //private val model: NameViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CollegeDataAdapter
    var data: ArrayList<CollegeData> = ArrayList<CollegeData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val url = "http://universities.hipolabs.com/search?country=United+States"
        //println(url)

//        val nameObserver = Observer<String> { newName ->
//            // Update the UI, in this case, a TextView.
//            //nameTextView.text = newName
//        }

        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                println(s.toString())
                val newData: ArrayList<CollegeData> = data.filter { t -> t.name.contains(s.toString())  } as ArrayList<CollegeData>
//
                binding.recyclerView.adapter=CollegeDataAdapter(newData,this@MainActivity)
                binding.recyclerView.invalidate()
                println(newData)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        //model.currentName.observe(this, nameObserver)
        val que = Volley.newRequestQueue(applicationContext)
        binding.spinner.visibility= View.VISIBLE
        val req= JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                for (i in 0 until  response.length()){
                    binding.spinner.visibility= View.GONE
                    val college: JSONObject = response.getJSONObject(i)
                    val name: String = college.getString("name")
                    val country: String = college.getString("country")
                    val webPages= college.getJSONArray("web_pages")
                    val domains = college.getJSONArray("domains")
                    val alphaTwoCode: String = college.getString("alpha_two_code")
                    val stateProvince: String = college.getString("state-province")
                    binding.recyclerView.visibility= View.VISIBLE
                    val collegeData = CollegeData(domains, webPages, name, alphaTwoCode, stateProvince, country)

                    data.add(collegeData)
                    //println("$webPages $domains $name \n")
                }
                adapter = CollegeDataAdapter(data,this)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.setHasFixedSize(true)
                println(response)
            },
            { error ->
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            })
        que.add(req)
    }


    override fun onItemClick(url:String) {
        println("THE URL IS $url")
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

}

