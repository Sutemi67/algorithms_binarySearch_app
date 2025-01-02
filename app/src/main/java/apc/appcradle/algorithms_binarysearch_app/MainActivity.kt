package apc.appcradle.algorithms_binarysearch_app

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import apc.appcradle.algorithms_binarysearch_app.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var count: Int = 0
    private var searchNumber: Int = 0
    private var summaryOfOne = 0
    private var summaryOfSecond = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.calculateButton.setOnClickListener {
            if (!binding.itemToSearchText.text.isNullOrEmpty() && !binding.numberOfArrayText.text.isNullOrEmpty()) {
                try {
                    count =
                        binding.numberOfArrayText.text.toString().toInt()
                    searchNumber =
                        binding.itemToSearchText.text.toString().toInt()
                    var i = -1
                    val array = Array(count, { i++ })
                    oneByOneSearch(array, searchNumber)

                } catch (e: Exception) {
                    binding.itemToSearchText.text?.clear()
                }
            }
        }
    }

    private fun oneByOneSearch(array: Array<Int>, searchItem: Int) {
        val start = System.currentTimeMillis()
        for (i in array.indices) {
            Log.d("TAG", "$searchItem ищем этот элемент, сейчас индекс равен $i")
            if (array[i] == searchItem) {
                break
            }
            summaryOfOne++
        }
        val resultTime = (System.currentTimeMillis() - start).toString()
        binding.oneCountText.text =
            String.format(Locale.getDefault(), "$summaryOfOne operations")
        binding.oneCountTextMs.text =
            String.format(Locale.getDefault(), "$resultTime ms")
        summaryOfOne = 0
    }

//    private fun binarySearch() {
//        var x = -1
//        val array2 = Array(count, { i++ })
//        var y = 0
//        val start2 = System.currentTimeMillis()
//        val low = 0
//        val high = array2.size
//        while (low <= high) {
//            val mid = (low + high) / 2
//            val guess = array2[mid]
//            if (guess == array2[count - 1])
//        }
//    }
}