package com.auxy.archapp.utils

class TemperatureUtil {
    companion object {
        fun w2c(tW: Double): Double {
            return (tW - 32) * 5 / 9
        }
    }
}