package com.auxy.archapp.di

import android.app.Application
import com.auxy.archapp.app.ArchApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ActivityModule::class,
    FragmentModule::class,
    ViewModelModule::class,
    RetrofitClientModule::class,
    GsonModule::class
])
interface ApplicationComponent : AndroidInjector<ArchApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(application: ArchApp)
}
