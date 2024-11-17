package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.dataclass.ListEmpresas

private lateinit var myListEmpresas:ArrayList<ListEmpresas>
class empresas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_empresas)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //ARRAY DE RECICLE VIEW PARA AS EMPRESAS
        myListEmpresas = ArrayList<ListEmpresas>()

        myListEmpresas.add(ListEmpresas("Borgwarner","avenida 123","Viana do castelo","borg@ipvc.pt","900000000"))
        myListEmpresas.add(ListEmpresas("celpower","avenida 1212","Viana do castelo","pw@ipvc.pt","900000000"))
        myListEmpresas.add(ListEmpresas("porsche","avenida 23233","Viana do castelo","ps@ipvc.pt","900000000"))
        myListEmpresas.add(ListEmpresas("Siemens", "Rua da Inovação, 10", "Lisboa", "contact@siemens.pt", "910000001"))
        myListEmpresas.add(ListEmpresas("Bosch", "Estrada Industrial, 50", "Braga", "info@bosch.pt", "910000002"))
        myListEmpresas.add(ListEmpresas("Continental", "Avenida do Automóvel, 100", "Porto", "contato@continental.pt", "910000003"))
        myListEmpresas.add(ListEmpresas("Intel", "Praça da Tecnologia, 15", "Aveiro", "support@intel.pt", "910000004"))
        myListEmpresas.add(ListEmpresas("Microsoft", "Rua da Computação, 80", "Lisboa", "ms@portugal.pt", "910000005"))
        myListEmpresas.add(ListEmpresas("Apple", "Boulevard Inovação, 12", "Porto", "contacto@apple.pt", "910000006"))
        myListEmpresas.add(ListEmpresas("Amazon", "Parque Logístico, 5", "Coimbra", "logistica@amazon.pt", "910000007"))
        myListEmpresas.add(ListEmpresas("Efacec", "Zona Industrial, 75", "Guimarães", "efacec@energy.pt", "910000008"))
        myListEmpresas.add(ListEmpresas("Vodafone", "Avenida das Comunicações, 30", "Lisboa", "info@vodafone.pt", "910000009"))
        myListEmpresas.add(ListEmpresas("Google", "Rua do Saber, 1000", "Porto", "portugal@google.pt", "910000010"))


        val recycler_view: RecyclerView = findViewById(R.id.recycler_view)
        recycler_view.adapter = NotaAdapter(myListEmpresas)
        recycler_view.layoutManager = LinearLayoutManager(this)

    }

    fun inicio(view: View) {
        val intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        //animação transição
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}