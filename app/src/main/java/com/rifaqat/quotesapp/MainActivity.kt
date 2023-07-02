package com.rifaqat.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.rifaqat.quotesapp.databinding.ActivityMainBinding
import com.rifaqat.quotesapp.models.Quotes
import com.rifaqat.quotesapp.viewModels.MainViewModel
import com.rifaqat.quotesapp.viewModels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel= ViewModelProvider(this,MainViewModelFactory(application))[MainViewModel::class.java]

        setQuote(mainViewModel.getQuote())
    }

    private fun setQuote(quotes: Quotes){
        binding.quoteText.text=quotes.text
        binding.quoteAuthor.text=quotes.author
    }

    fun onShare(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
        startActivity(intent)
    }
    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }
    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }
}