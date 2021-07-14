package com.auxy.archapp.globalcomponent

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExitEventManager @Inject constructor() {
    private val _exitTaskEvent = PublishSubject.create<Boolean>()
    val exitTaskEvent: Flowable<Boolean> = _exitTaskEvent.toFlowable(BackpressureStrategy.LATEST)

    fun removedFromTask() {
        _exitTaskEvent.onNext(true)
    }
}
