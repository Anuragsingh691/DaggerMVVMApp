package com.example.daggerbaseapiapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daggerbaseapiapplication.vm.MainViewModel
import com.example.daggerbaseapiapplication.vm.MyViewModelProviderFactory
import com.example.daggerbaseapiapplication.vm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: MyViewModelProviderFactory): ViewModelProvider.Factory


    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  MovieListViewModel.class as key,
     * and a Provider that will build a MovieListViewModel
     * object.
     *
     * */

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun movieListViewModel(mainViewModel: MainViewModel): ViewModel
}