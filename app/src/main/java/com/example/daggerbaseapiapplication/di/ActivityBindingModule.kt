package com.example.daggerbaseapiapplication.di

import com.example.daggerbaseapiapplication.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
* Since we are using the dagger-android support library, we can make use of Android Injection.
*  The ActivityModule generates AndroidInjector(this is the new dagger-android class which exist in dagger-android framework) for Activities defined in this class.
* This allows us to inject things into Activities using AndroidInjection.inject(this) in the onCreate() method.
* */
@Module
internal abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}