package com.example.daggerbaseapiapplication.di

import android.app.Application
import com.example.daggerbaseapiapplication.core.BaseApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/*
* Any class with the annotation @Component defines the connection between the modules and the classes which need the dependency.
* We define a @Component.Builder interface which will be called from our custom Application class.
* This will set our application object to the AppComponent. So inside the AppComponent the application instance is available.
* So this application instance can be accessed by our modules such as ApiModule when needed.
*
 * We mark this interface with the @Component annotation.
 * And we define all the modules that can be injected.
 * Note that we provide AndroidSupportInjectionModule.class
 * here. This class was not created by us.
 * It is an internal class in Dagger 2.10.
 * Provides our activities and fragments with given module.
 *
* */

// Definition of a Dagger component that adds info from the NetWorkModule to the graph
@Component(
    modules = [NetWorkModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class]
)
@Singleton
interface ApplicationComponent {

    /*
    * We will call this builder interface from our custom Application class.
    * This will set our application object to the AppComponent.
    * So inside the AppComponent the application instance is available.
    * So this application instance can be accessed by our modules
    * such as ApiModule when needed
    *
    * */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    /*
     * This is our custom Application class
     * */
    fun inject(baseApplication: BaseApplication)
//    // Classes that can be injected by this Component
//    fun inject(mainActivity: MainActivity)
}