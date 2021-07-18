package app.sparsh.projectiniectiohilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import app.sparsh.projectiniectiohilt.R
import app.sparsh.projectiniectiohilt.databinding.ActivityMainBinding
import app.sparsh.projectiniectiohilt.model.Blog
import app.sparsh.projectiniectiohilt.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when(dataState) {
                is DataState.Success -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            binding.tvText.text = message
        } else {
            binding.tvText.text = "Unknown Error"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.pbProgressBar.visibility = if (isDisplayed) View.VISIBLE else View.INVISIBLE
    }

    private fun appendBlogTitles(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append("${blog.title}\n")
        }
        binding.tvText.text = sb
    }
}