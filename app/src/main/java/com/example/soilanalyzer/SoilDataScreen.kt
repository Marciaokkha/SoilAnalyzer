package com.example.soilanalyzer

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.soilanalyzer.databinding.FragmentSoilDataScreenBinding

class SoilDataScreen : Fragment() {

    private lateinit var databind: FragmentSoilDataScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_soil_data_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databind = FragmentSoilDataScreenBinding.bind(view)

        databind.pHBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                databind.pHProgressText.text = " $progress"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        databind.temperatureBar.max = 70
        databind.temperatureBar.progress = 40
        databind.temperatureBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                val temperature = progress - 20
                databind.temperatureProgressText.text = " $temperature°C"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        databind.moistureBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                databind.moistureProgressText.text = " $progress%"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        databind.buttonStartAnalyze.setOnClickListener {
            val intent = Intent(requireContext(), AnalyzeResultScreen::class.java)
            intent.putExtra("pH", databind.pHBar.progress)
            intent.putExtra("temperature", (databind.temperatureBar.progress - 20))
            intent.putExtra("moisture", databind.moistureBar.progress)
            register.launch(intent)

        }
    }

    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                val analyzeExtra = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    data.getParcelableExtra("ANALYZE", Analyze::class.java)
                } else {
                    data.getParcelableExtra<Analyze>("ANALYZE")
                }
                analyzeExtra?.let {
                    val titleString = getString(R.string.soilAnalyzerScreenTitleResults)
                    databind.soilAnalyzedText.text = "$titleString\n${analyzeExtra.analyze}"
                }
            }
        }
    }
}