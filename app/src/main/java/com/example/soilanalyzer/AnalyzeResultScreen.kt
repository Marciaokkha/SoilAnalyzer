package com.example.soilanalyzer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.soilanalyzer.databinding.ActivityAnalyzeResultScreenBinding

class AnalyzeResultScreen : AppCompatActivity() {

    private lateinit var databind: ActivityAnalyzeResultScreenBinding
    private lateinit var analyze: Analyze

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityAnalyzeResultScreenBinding.inflate(layoutInflater)
        setContentView(databind.root)

        val pH = intent.getIntExtra("pH",0)
        val temperature = intent.getIntExtra("temperature",0)
        val moisture = intent.getIntExtra("moisture",0)

        databind.phCurrentValueText.text = " $pH"
        databind.temperatureCurrentValueText.text = " $temperature°C"
        databind.moistureCurrentValueText.text = " $moisture%"

        if(pH >= 7 && pH <= 8) {
            databind.phActionValueText.text = " ${getString(R.string.ph_ok)}"
        } else {
            databind.phActionValueText.text = " ${getString(R.string.ph_not_ok)}"
        }

        if(moisture < 60 && temperature > 24) {
            databind.temperatureActionValueText.text = " ${getString(R.string.moistureTeperature_not_ok)}"
        } else if(moisture < 60) {
            databind.temperatureActionValueText.text = " ${getString(R.string.moisture_not_ok)}"
        } else {
            databind.temperatureActionValueText.text = " ${getString(R.string.moisture_ok)}"
        }

        databind.buttonResultScreen.setOnClickListener {
            Intent().apply {
                fun saveAnalyze() : Analyze {
                    return Analyze(">${databind.phActionValueText.text}" +
                            "\n>${databind.temperatureActionValueText.text}")
                }
                analyze = saveAnalyze()
                putExtra("ANALYZE", analyze)
                setResult(RESULT_OK, this)
            }
            this.finish()
        }
    }
}