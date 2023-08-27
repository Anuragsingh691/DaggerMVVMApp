package com.example.daggerbaseapiapplication.core

import android.app.Activity
import android.app.Application
import com.example.daggerbaseapiapplication.di.ApplicationComponent
import com.example.daggerbaseapiapplication.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/*
 * we use our AppComponent (now prefixed with Dagger)
 * to inject our Application class.
 * This way a DispatchingAndroidInjector is injected which is
 * then returned when an injector for an activity is requested.
 * */
class BaseApplication : Application(), HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

//    // Instance of the AppComponent that will be used by all the Activities in the project
//    lateinit var applicationComponent: ApplicationComponent
//
//    override fun onCreate() {
//        super.onCreate()
//        // Creates an instance of AppComponent using its Factory constructor
//        // We pass the applicationContext that will be used as Context in the graph
//        applicationComponent = DaggerApplicationComponent.builder().build()
//    }
}