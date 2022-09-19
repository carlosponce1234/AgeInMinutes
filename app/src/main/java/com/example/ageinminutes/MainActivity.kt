package com.example.ageinminutes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Seteamos el titulo de la app
        val txt1 = findViewById<TextView>(R.id.txt1)
        txt1.text = "Levantamiento de Inventarios"
        //seteamos la etiqueta para el input de Usuario
        val txt2 = findViewById<TextView>(R.id.txt2)
        txt2.text = "Usuario SFexNet"
    }
    //COMPROBAMOS QUE EL USUARIO EXISTA EN LA BASE DE DATOS
    @SuppressLint("SetTextI18n")
    fun getData(view: View) {
        //obtenemos el valor del input
        val input1 = findViewById<TextView>(R.id.input1)
        //CONECTAMOS A LA BASE DE DATOS
        val bd = connectionClass().dbConn()
        //CREAMOS UNA CONSULTA
        val sql = "SELECT UserLogin,CodUser FROM Util.CatUsuarios where UserLogin = ?"
        val stmt = bd?.prepareStatement(sql)
        //ASIGNAMOS EL VALOR DEL INPUT A LA CONSULTA
        stmt?.setString(1, "${input1.text}")
        val rs = stmt?.executeQuery()
        //SI EL USUARIO EXISTE EN LA BASE DE DATOS
        if (rs?.next() == true) {
            //CREAMOS UNA VARIABLE PARA GUARDAR EL NOMBRE DEL USUARIO
            //Y ASIGNAMOS EL VALOR DE LA CONSULTA
            //PREPARAMOS UN INTENT PARA PASAR A LA SIGUIENTE ACTIVIDAD
            val intent = Intent(this, MainActivity2::class.java).apply {
                //PASAMOS EL VALOR DE LA CONSULTA A LA SIGUIENTE ACTIVIDAD
                putExtra("UserLogin", rs.getString("UserLogin"))
                putExtra("CodUser", rs.getString("CodUser"))
            }
            //INICIAMOS LA SIGUIENTE ACTIVIDAD
            startActivity(intent)
        } else {
            //SI EL USUARIO NO EXISTE EN LA BASE DE DATOS
            //MOSTRAMOS UN MENSAJE DE ERROR
            val txt2 = findViewById<TextView>(R.id.txt2)
            txt2.text = "No existe el usuario"
        }
        //CERRAMOS LA CONEXION A LA BASE DE DATOS
        bd?.close()
    }
}