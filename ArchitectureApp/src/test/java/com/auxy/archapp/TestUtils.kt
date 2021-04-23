package com.auxy.archapp

import com.auxy.archapp.moshi.MoshiInstance
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.TestScheduler
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.reflect.Field
import java.lang.reflect.Modifier

object TestUtils {
    private const val ASSET_BASE_PATH = "src/main/assets/"
    val moshi = MoshiInstance.create()

    fun readJsonFile(filename: String): String {
        val sb = StringBuilder()
        var br: BufferedReader? = null
        try {
            br = BufferedReader(InputStreamReader(FileInputStream(ASSET_BASE_PATH + filename)))
            var line = br.readLine()
            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }
        } catch (exp: Exception) {
            exp.printStackTrace()
        } finally {
            try {
                br?.close()
            } catch (e: Exception) {

            }
        }
        return sb.toString()
    }

    fun convertAllSchedulersToTest(testScheduler: TestScheduler?) {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { testScheduler }
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }
        RxJavaPlugins.setSingleSchedulerHandler { testScheduler }
    }

    fun resetSchedulers() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Throws(Exception::class)
    fun setFinalStatic(field: Field, newValue: Any?) {
        field.isAccessible = true
        val modifiersField: Field = Field::class.java.getDeclaredField("modifiers")
        modifiersField.isAccessible = true
        modifiersField.setInt(field, field.modifiers and Modifier.FINAL.inv())
        field.set(null, newValue)
    }
}
