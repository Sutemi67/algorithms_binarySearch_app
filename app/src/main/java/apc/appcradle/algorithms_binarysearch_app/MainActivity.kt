package apc.appcradle.algorithms_binarysearch_app

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import apc.appcradle.algorithms_binarysearch_app.databinding.ActivityMainBinding
import java.util.Locale
import kotlin.system.measureTimeMillis

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
//                    val array = Array(count, { i++ })
                    val array = List(count, { i++ })
                    oneByOneSearch(array, searchNumber)
                    binarySearch(array, searchNumber)
                } catch (e: Exception) {
                    binding.itemToSearchText.text?.clear()
                    binding.numberOfArrayText.text?.clear()
                }
            }
        }
    }

    private fun oneByOneSearch(array: List<Int>, searchItem: Int) {
        val time = measureTimeMillis {
            for (i in array.indices) {
                if (array[i] == searchItem) {
                    break
                }
                summaryOfOne++
            }
        }
        binding.oneCountText.text =
            String.format(Locale.getDefault(), "$summaryOfOne operations")
        binding.oneCountTextMs.text =
            String.format(Locale.getDefault(), "$time ms")
        summaryOfOne = 0
    }

    private fun binarySearch(array: List<Int>, searchItem: Int) {
        val time = measureTimeMillis {
            var low = 0
            var high = array.size
            var mid = 0
            while (low <= high) {
                mid = (low + high) / 2
                val guess = array[mid]
                summaryOfSecond++
                if (guess == searchItem) {
                    Log.i(
                        "TAG",
                        "Поиск завершен, искомое число $guess, введенное число: $searchItem"
                    )
                    break
                } else if (guess > searchItem) {
                    high = mid - 1
                } else {
                    low = mid + 1
                }
            }
        }
        binding.algoCountText.text =
            String.format(Locale.getDefault(), "$summaryOfSecond operations")
        binding.algoTimeText.text =
            String.format(Locale.getDefault(), "$time ms")
        summaryOfSecond = 0
    }
}