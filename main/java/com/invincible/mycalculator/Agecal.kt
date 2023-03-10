package com.invincible.mycalculator

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.util.*

class Agecal : AppCompatActivity() {
    private lateinit var selectDate:TextView
    private lateinit var calbtn:Button
    private lateinit var showAge:TextView

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
        setContentView(R.layout.activity_agecal)
        selectDate = findViewById(R.id.selectdate)
        calbtn = findViewById(R.id.calbtn)
        showAge = findViewById(R.id.showage)

        drawerLayout = findViewById(R.id.draw_age)
        navigationView = findViewById(R.id.navigation_main)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_bmi -> {
                    setVisible(false)
                    val intent = Intent(this@Agecal, BMI::class.java)
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
                    val intent = Intent(this@Agecal, app_version::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"App Version", Toast.LENGTH_SHORT).show()

                }
            }
            true
        }

    }

    fun selectDate(view: View) {
        var c = Calendar.getInstance()
        var cDay = c.get(Calendar.DAY_OF_MONTH)
        var cMonth = c.get(Calendar.MONTH)
        var cYear = c.get(Calendar.YEAR)

        val CalendarDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
            view, year, month, dayOfMonth ->
            cDay = dayOfMonth
            cMonth = month
            cYear = year
            calbtn.visibility = View.VISIBLE
            textMessage("You select :$cDay/${cMonth+1}/$cYear")
            calbtn.setOnClickListener {
                val currentYear = Calendar.getInstance()
                    .get(Calendar.YEAR)
                val age = currentYear - cYear
                showAge.visibility = View.VISIBLE
                showAge.text = "Your Age is $age year"
                textMessage("Your Age is $age year")
            }
            selectDate.text = "Your selected Date :$cDay/${cMonth+1}/$cYear"
        },cYear,cMonth,cDay)
        CalendarDialog.show()
    }

    private fun textMessage(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
    }

}