package com.example.ageinminutes

import android.os.StrictMode
import android.util.Log
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class connectionClass {

    private val ip = "192.168.1.3" // your database server ip
    private val db = "SFexNet" // your database name
    private val username = "appMovil" // your database username
    private val password = "12345@alvia" // your database password

    fun dbConn() : Connection? {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn : Connection? = null
        var connString: String? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
            conn = DriverManager.getConnection(connString)
        }catch (ex: SQLException){
            Log.e("Error1 : ", ex.message.toString())
        }catch (ex1: ClassNotFoundException){
            Log.e("Error2 : ", ex1.message.toString())
        }catch (ex2: Exception){
            Log.e("Error3 : ", ex2.message.toString())
        }

        return conn
    }
}