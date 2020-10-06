package fr.labard.androidtestexample

class Stats(private val array: Array<Int>) {
    fun getMean(): Double {
        return array.sum() / array.size.toDouble()
    }
    fun getSum() = array.sum()
}