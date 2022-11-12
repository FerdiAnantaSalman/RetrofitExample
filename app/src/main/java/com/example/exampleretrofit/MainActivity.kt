package com.example.exampleretrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exampleretrofit.databinding.ActivityMainBinding
import com.example.exampleretrofit.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]



        binding.btnNumber.setOnClickListener {
            closeKeyboard()

            val number = Integer.parseInt(binding.etNumber.text.toString())

            // Initialize QueryMap
            val option: HashMap<String, String> = HashMap()
            option["_sort"] = "id"
            option["_order"] = "desc"

            // Running viewModelScope

            viewModel.putPost(number,1, 1, null, "Ferdi Ananta")
//            viewModel.deletePost(number)

            // Observe LiveData
            viewModel.myResponse.observe(this, Observer {
                if (it.isSuccessful) {
                    binding.tvCode.text = it.code().toString()
                    binding.tvResponse.text = "userId : ${it.body()?.userId}\n" +
                                "id : ${it.body()?.id}\n" +
                            "title : ${it.body()?.title}\n" +
                            "body : ${it.body()?.body}"
//                    it.body()?.forEach {
//                        Log.d("RETROFIT", "postId : ${it.postId}")
//                        Log.d("RETROFIT", "Id : ${it.id}")
//                        Log.d("RETROFIT", "name : ${it.name}")
//                        Log.d("RETROFIT", "email : ${it.email}")
//                        Log.d("RETROFIT", "body : ${it.body}")
//                        Log.d("RETROFIT", "---------")
//                    }
                } else {
                    binding.tvResponse.text = it.errorBody().toString()
                }
            })
        }


    }


    private fun closeKeyboard() {
        val focus = this.currentFocus

        when  {
            focus != null -> {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focus.windowToken, 0)
            }
        }
    }
}