package lk.nibm.hdse222f004

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnconvert= findViewById<n>(R.id.btn_enter)
        val txt_loan_amount = findViewById<EditText>(R.id.txt_loan_amount)
        val spn_interest_rate = findViewById<Spinner>(R.id.spn_interest_rate)
        val spn_period = findViewById<Spinner>(R.id.spn_period)

        // Define arrays for interest rates and periods
        val interestRates = arrayOf("5%", "10%", "15%")
        val periods = arrayOf("3 months", "6 months", "1 year")

        // Create ArrayAdapters to populate Spinners
        val interestRateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, interestRates)
        val periodAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, periods)

        // Set dropdown views for Spinners
        interestRateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set the adapters for the Spinners
        spn_interest_rate.adapter = interestRateAdapter
        spn_period.adapter = periodAdapter

        btn_enter.setOnClickListener {
            val loanAmountStr = txt_loan_amount.text.toString()
            val interestRateStr = spn_interest_rate.selectedItem.toString()
            val periodStr = spn_period.selectedItem.toString()

            // Convert user input to numerical values
            val loanAmount = loanAmountStr.toDouble()
            val interestRate = when (interestRateStr) {
                "5%" -> 0.05
                "10%" -> 0.10
                "15%" -> 0.15
                else -> 0.05 // Default to 5% if not recognized
            }

            val period = when (periodStr) {
                "3 months" -> 3
                "6 months" -> 6
                "1 year" -> 12
                else -> 3 // Default to 3 months if not recognized
            }

            // Calculate total interest and total payable amount
            val totalInterest = loanAmount * interestRate * period
            val totalPayableAmount = loanAmount + totalInterest

            val intent = Intent(this, Exercise2::class.java)
            intent.putExtra("loanAmount", loanAmount)
            intent.putExtra("interestRate", interestRate)
            intent.putExtra("period", period)
            intent.putExtra("totalInterest", totalInterest)
            intent.putExtra("totalPayableAmount", totalPayableAmount)

            startActivity(intent)
        }
    }
}

}
}
