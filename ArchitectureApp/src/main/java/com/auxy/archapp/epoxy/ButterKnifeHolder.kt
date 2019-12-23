package com.auxy.archapp.epoxy

import android.view.View
import androidx.annotation.CallSuper
import butterknife.ButterKnife
import com.airbnb.epoxy.EpoxyHolder

open class ButterKnifeHolder : EpoxyHolder() {
    @CallSuper
    override fun bindView(itemView: View) {
        ButterKnife.bind(this, itemView)
    }
}