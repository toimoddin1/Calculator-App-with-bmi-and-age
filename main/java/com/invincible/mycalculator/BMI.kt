package com.invincible.mycalculator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class BMI : AppCompatActivity() {

    lateinit var weightText: EditText
    lateinit var heightText: EditText
    lateinit var calbtn: Button
    lateinit var infobtn: ImageButton

    lateinit var resultindex: TextView
    lateinit var resultdes: TextView
    lateinit var info: TextView

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        if(menu != null){
            val searchItem: MenuItem = menu.findItem(R.id.nav_home)
            if(searchItem.itemId == R.id.nav_home){
                searchItem?.setVisible(false)
//                Toast.makeText(this@MainActivity,"This is Test",Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Navigation View Open and Close function
        if(toggle.onOptionsItemSelected(item)){
            return true
        }


        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        weightText = findViewById(R.id.etWeight)
        heightText = findViewById(R.id.etHeight)
        calbtn = findViewById(R.id.btncalculate)
        resultindex = findViewById(R.id.tvIndex)
        resultdes = findViewById(R.id.tvResult)
        info = findViewById(R.id.tvInfo)
        infobtn = findViewById(R.id.infoButton)

        drawerLayout = findViewById(R.id.draw_bmi)
        navigationView = findViewById(R.id.navigation_main)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_bmi -> {
                    setVisible(false)
                    val intent = Intent(this@BMI, BMI::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"BMI Calculator", Toast.LENGTH_SHORT).show()

                }
            }
            when(it.itemId){
                R.id.nav_home->{
                    setVisible(false)
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"Home", Toast.LENGTH_SHORT).show()

                }
            }
            when(it.itemId){
                R.id.nav_age->{
                    setVisible(false)
                    val intent = Intent(this,Agecal::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"Age Calculator", Toast.LENGTH_SHORT).show()

                }
            }
            when(it.itemId){
                R.id.privacy->{
                    setVisible(false)
                    val openURL = Intent(android.content.Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://pages.flycricket.io/my-calculator-3/privacy.html")
                    startActivity(openURL)
                    Toast.makeText(this,"Privacy Policy", Toast.LENGTH_SHORT).show()
                }
            }
            when(it.itemId){
                R.id.terms_cond->{
                    setVisible(false)
                    val openURL = Intent(android.content.Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://pages.flycricket.io/my-calculator-3/terms.html")
                    startActivity(openURL)
                    Toast.makeText(this,"Terms & Condition", Toast.LENGTH_SHORT).show()

                }
            }
            when(it.itemId){
                R.id.other_app->{
                    setVisible(false)
                    val openURL = Intent(android.content.Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://play.google.com/store/apps/developer?id=INVINCIBLE+TDN&hl=en_IN&gl=US")
                    startActivity(openURL)
                    Toast.makeText(this,"Opening Play Store", Toast.LENGTH_SHORT).show()

                }
            }
            when(it.itemId){
                R.id.version->{
                    setVisible(false)
                    val intent = Intent(this@BMI, app_version::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"App Version", Toast.LENGTH_SHORT).show()

                }
            }
            true
        }



        infobtn.setOnClickListener {
            val intent = Intent(this@BMI,BMI_Table::class.java)
            startActivity(intent)
            Toast.makeText(this,"BMI Table", Toast.LENGTH_SHORT).show()

        }
        calbtn.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput(weight,height)) {

                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                //get result with two decimal places

                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displayresult(bmi2digits)
            }
        }
    }

    private fun validateInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this,"Height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayresult(bmi:Float) {
        resultindex.text = bmi.toString()
        info.text = "(Normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi<18.50 ->{
                resultText = "You are Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resultText = "You are Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText = "You are Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 ->{
                resultText = "You are Obese"
                color = R.color.obese
            }
        }
        resultdes.setTextColor(ContextCompat.getColor(this,color))
        resultdes.text = resultText
    }
}