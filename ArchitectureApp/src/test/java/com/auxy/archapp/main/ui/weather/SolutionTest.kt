package com.auxy.archapp.main.ui.weather

import org.junit.Test

class SolutionTest {

    @Test
    fun mainTest() {
        //val binString = "011100"
        //val binString = "0000111"
        val binString = "01110011"
        println("${solution(binString)} operations were required to reduce ${binString.toLong()} to 0")
    }

    @ExperimentalUnsignedTypes
    private fun solution(binString: String): Int {
        var steps = 0
        var binValue = binString.toULong(2)
        while (binValue != 0UL) {
            if (binValue.rem(2UL) == 0UL) {
                binValue /= 2UL
            } else {
                binValue--
            }
            steps++
            println(binValue)
        }
        return steps
    }
}