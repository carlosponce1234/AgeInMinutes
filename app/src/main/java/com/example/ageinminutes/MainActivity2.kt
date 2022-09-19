package com.example.ageinminutes

import android.R.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val Userlogin = intent.getStringExtra("UserLogin")
        val titulo = findViewById<TextView>(R.id.titulo2)
        titulo.text = "Bienvenido $Userlogin"
        loadSucursales()
        val listView = findViewById<ListView>(R.id.sucursalesLista)
        listView.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as String
            Toast.makeText(this, "Seleccionaste $item", Toast.LENGTH_SHORT).show()
        }

    }

    fun loadSucursales() {
        val bd = connectionClass().dbConn()
        val sql = "Select CodSucursal , DescSucursal from Inventario.CatSucursales where CodSucursal < 100"
        val stmt = bd?.createStatement()
        val rs = stmt?.executeQuery(sql)
        val sucursales = mutableMapOf<String,String>()
        val sucursalesList = mutableListOf<String>()
        while (rs?.next() == true){
            val CodSucursal = rs.getString("CodSucursal")
            val DescSucursal = rs.getString("DescSucursal")
            sucursales[CodSucursal] = DescSucursal
            sucursalesList.add(DescSucursal)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sucursalesList)
        val listView = findViewById<ListView>(R.id.sucursalesLista)
        listView.adapter = adapter
    }


}

