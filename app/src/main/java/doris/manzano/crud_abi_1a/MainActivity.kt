package doris.manzano.crud_abi_1a

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.clase_conexion

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtPrecio = findViewById<EditText>(R.id.txtPrecio)
        val txtCantidad = findViewById<EditText>(R.id.txtCanidad)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val rcvDatos = findViewById<RecyclerView>(R.id.rcbDatos)

        rcvDatos.layoutManager = LinearLayoutManager(this)

        val miAdaptador = Adaptador(ListaDeDaos)

       btnAgregar.setOnClickListener {
           GlobalScope.launch(Dispatchers.IO){
               val objConexion= clase_conexion().cadenaConexion()
               val addProductos = objConexion?.prepareStatement("insert into tbproductos1 values(?,?,?)")!!
               addProductos.setString(1,txtNombre.text.toString())
               addProductos.setInt(2,txtPrecio.text.toString().toInt())
               addProductos.setInt(3, txtCantidad.text.toString().toInt())
               addProductos.executeUpdate()

           }
       }

        class Adaptador(private val Datos: Array<String>){



        }

    }
}