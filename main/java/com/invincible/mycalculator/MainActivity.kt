package com.invincible.mycalculator

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        if(menu != null){
            val searchItem: MenuItem = menu.findItem(R.id.nav_home)
            if(searchItem.itemId == R.id.nav_home){
                searchItem?.setVisible(false)

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
        setContentView(R.layout.activity_main)


        drawerLayout = findViewById(R.id.draw_main)
        navigationView = findViewById(R.id.navigation_main)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_bmi -> {
                    setVisible(false)
                    val intent = Intent(this@MainActivity, BMI::class.java)
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
                    val intent = Intent(this@MainActivity, app_version::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"App Version", Toast.LENGTH_SHORT).show()

                }
            }
            true
        }


        btn_clear.setOnClickListener {
            input.text = ""
            output.text = "0"
        }
        left_bracket.setOnClickListener {
            input.text = addToInputText("(")
        }
        right_bracket.setOnClickListener {
            input.text = addToInputText(")")
        }
        btn_zero.setOnClickListener {
            input.text = addToInputText("0")
        }
        btn_one.setOnClickListener {
            input.text = addToInputText("1")
        }
        btn_two.setOnClickListener {
            input.text = addToInputText("2")
        }
        btn_three.setOnClickListener {
            input.text = addToInputText("3")
        }
        btn_four.setOnClickListener {
            input.text = addToInputText("4")
        }
        btn_five.setOnClickListener {
            input.text = addToInputText("5")
        }
        btn_six.setOnClickListener {
            input.text = addToInputText("6")
        }
        btn_seven.setOnClickListener {
            input.text = addToInputText("7")
        }
        btn_eight.setOnClickListener {
            input.text = addToInputText("8")
        }
        btn_nine.setOnClickListener {
            input.text = addToInputText("9")
        }
        btn_dot.setOnClickListener {
            input.text = addToInputText(".")
        }
        btn_plus.setOnClickListener {
            input.text = addToInputText("+")
        }
        btn_minus.setOnClickListener {
            input.text = addToInputText("-")
        }
        btn_multiply.setOnClickListener {
            input.text = addToInputText("×")
        }
        btn_divide.setOnClickListener {
            input.text = addToInputText("÷")
        }
        btn_equal.setOnClickListener {
            showResult()
        }

    }

    private fun getInputExpression(): String {
        var expression = input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")

        return expression

    }
    private fun showResult() {
        try {
            var expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()){
                //show error message
                output.text = "Error"
                output.setTextColor(ContextCompat.getColor(this, R.color.Red))
            } else {
                //show result
                output.text = DecimalFormat("0.######").format(result).toString()
                output.setTextColor(ContextCompat.getColor(this, R.color.Output))

            }
        }catch (e: Exception){
            //show error message
            output.text = "Error"
            output.setTextColor(ContextCompat.getColor(this, R.color.Red))
        }
    }

    private fun addToInputText(buttonValue: String): String {
        return "${input.text}$buttonValue"
    }
}



