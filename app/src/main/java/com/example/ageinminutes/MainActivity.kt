package com.example.ageinminutes

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //find txt1 label and set text
        val txt1 = findViewById<TextView>(R.id.txt1)
        txt1.text = "Hello World"
        //find txt2 label
        val txt2 = findViewById<TextView>(R.id.txt2)
        //set text2
        txt2.text = "Hello World 2"
        //find input1 field value
        val input1 = findViewById<TextView>(R.id.input1)
        //set button listener
    }
    //function to connect to bd an prepare statement
    @SuppressLint("SetTextI18n")
    fun getData(view: View) {
        val input1 = findViewById<TextView>(R.id.input1)
        //connect to bd
        val bd = connectionClass().dbConn()
        //prepare statement
        val sql = "SELECT UserLogin FROM Util.CatUsuarios where CodUser = ?"
        val stmt = bd?.prepareStatement(sql)
        //set parameters
        stmt?.setString(1, "${input1.text}")
        //execute query
        val rs = stmt?.executeQuery()
        // get data
        if (rs?.next() == true) {
            val txt2 = findViewById<TextView>(R.id.txt2)
            txt2.text = rs.getString("UserLogin")
        } else {
            val txt2 = findViewById<TextView>(R.id.txt2)
            txt2.text = "No data"
        }
        //close connection
        bd?.close()
    }
}