package com.example.ageinminutes

import android.R.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Tomamos el Nombre de Usuario de la Activity anterior
        val Userlogin = intent.getStringExtra("UserLogin")
        // Tomamos el CodUser de la Activity anterior
        val CodUser = intent.getStringExtra("CodUser")
        // Seteamos el Nombre de Usuario en el TextView
        val titulo = findViewById<TextView>(R.id.titulo2)
        titulo.text = "Bienvenido $Userlogin"
        // Cargamos el listado DE SUCURSALES
        loadSucursales()

        // OBTENEMOS EL LISTADO DE SUCURSALES
        val listView = findViewById<ListView>(R.id.sucursalesLista)

        // ON CLICK LISTENER PARA CADA ITEM DE LA LISTA
        listView.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as String
            val sucursales = mapSucursales()
            var codSucursal = if (sucursales.containsValue(item)) sucursales.filterValues { it == item }.keys.first() else "0"
            Toast.makeText(this, "Seleccionaste $item y su codigo es $codSucursal", Toast.LENGTH_SHORT).show()

        }

    }
// CARGAMOS datos de las Sucursales para mostrar en el listado
    fun loadSucursales() {

    // CONECTAMOS CON EL SERVIDOR
        val bd = connectionClass().dbConn()
    // CREAMOS UNA CONSULTA SQL PARA OBTENER LOS DATOS DE LAS SUCURSALES
        val sql = "Select CodSucursal , DescSucursal from Inventario.CatSucursales where CodSucursal < 100"
        val stmt = bd?.createStatement()
        val rs = stmt?.executeQuery(sql)
    // CREAMOS UN ARRAYLIST PARA GUARDAR LOS DATOS DE LAS SUCURSALES
        val sucursalesList = mutableListOf<String>()
        while (rs?.next() == true){
            val DescSucursal = rs.getString("DescSucursal")
            sucursalesList.add(DescSucursal)
        }
    // CREAMOS UN ADAPTER PARA MOSTRAR LOS DATOS EN EL LISTVIEW
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sucursalesList)
        val listView = findViewById<ListView>(R.id.sucursalesLista)
    // SETEAMOS EL ADAPTER EN EL LISTVIEW
        listView.adapter = adapter
    }

    // CREAMOS UN MAPA PARA OBTENER EL CODIGO DE LA SUCURSAL
    fun mapSucursales(): MutableMap<String, String> {
        // CONECTAMOS CON EL SERVIDOR
        val bd = connectionClass().dbConn()
        // CREAMOS UNA CONSULTA SQL PARA OBTENER LOS DATOS DE LAS SUCURSALES
        val sql = "Select CodSucursal , DescSucursal from Inventario.CatSucursales where CodSucursal < 100"
        val stmt = bd?.createStatement()
        val rs = stmt?.executeQuery(sql)
        // CREAMOS UN MAPA PARA GUARDAR LOS DATOS DE LAS SUCURSALES
        val sucursales = mutableMapOf<String,String>()
        while (rs?.next() == true){
            val CodSucursal = rs.getString("CodSucursal")
            val DescSucursal = rs.getString("DescSucursal")
            sucursales[CodSucursal] = DescSucursal
        }
        // RETORNAMOS EL MAPA
        return sucursales
    }


}

