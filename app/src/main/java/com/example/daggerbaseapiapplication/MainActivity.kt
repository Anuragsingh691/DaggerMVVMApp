package com.example.daggerbaseapiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.daggerbaseapiapplication.core.BaseApplication
import com.example.daggerbaseapiapplication.databinding.ActivityMainBinding
import com.example.daggerbaseapiapplication.vm.MainViewModel
import com.example.daggerbaseapiapplication.vm.MyViewModelProviderFactory
import com.example.swipemvvmkoin.util.AppResult
import dagger.android.AndroidInjection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var myViewModelProviderFactory: MyViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this);

//        (application as BaseApplication).applicationComponent.inject(this)
        mainViewModel = ViewModelProvider(this,myViewModelProviderFactory)[MainViewModel::class.java]
        mainViewModel.getProducts()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mainViewModel.productFlow.collectLatest { result->
                    when(result){
                        is AppResult.Success ->
                            Toast.makeText(this@MainActivity, result.successData.toString(),Toast.LENGTH_LONG).show()
                        is AppResult.Error ->
                            Toast.makeText(this@MainActivity, result.exception.message.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}