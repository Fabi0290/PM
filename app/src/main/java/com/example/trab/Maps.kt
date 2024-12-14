package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.trab.api.Empresa
import com.example.trab.api.EndPoints
import com.example.trab.api.ServiceBuilder
import com.example.trab.databinding.ActivityMapsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Maps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    // Variável para armazenar o nome da empresa selecionada
    private var nomeEmpresaSelecionada: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar o nome da empresa passada pela Intent
        nomeEmpresaSelecionada = intent.getStringExtra("nome")

        // Initialize the SupportMapFragment
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Centralizar no norte de Portugal
        val nortePortugal = LatLng(41.6946, -8.8302) // Coordenadas aproximadas do norte de Portugal
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nortePortugal, 8f))

        // Configurar o listener para cliques no InfoWindow (configurado uma vez)
        mMap.setOnInfoWindowClickListener { marker ->
            val empresa = marker.tag as? Empresa
            if (empresa != null) {
                val intent = Intent(this@Maps, EmpresaDetailsActivity::class.java).apply {
                    putExtra("nome", empresa.nome)
                    putExtra("descricao", empresa.descricao)
                    putExtra("cidade", empresa.cidade)
                    putExtra("num_alunos", empresa.num_alunos)
                    putExtra("num_vagas", empresa.num_vagas)
                }
                startActivity(intent)
            }
        }

        // Buscar empresas
        fetchEmpresas()
    }

    private fun fetchEmpresas() {
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getTodasEmpresas()

        call.enqueue(object : Callback<List<Empresa>> {
            override fun onResponse(call: Call<List<Empresa>>, response: Response<List<Empresa>>) {
                if (response.isSuccessful) {
                    val empresas = response.body()
                    if (empresas != null) {
                        for (empresa in empresas) {
                            val position = LatLng(empresa.latitude, empresa.longitude)
                            val marker = mMap.addMarker(
                                MarkerOptions()
                                    .position(position)
                                    .title(empresa.nome)
                                    .snippet("${empresa.descricao} - ${empresa.cidade}")
                            )
                            marker?.tag = empresa

                            // Centralizar na empresa selecionada, se o nome corresponder
                            if (empresa.nome == nomeEmpresaSelecionada) {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,15f))
                                marker?.showInfoWindow() // Mostra o InfoWindow automaticamente
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        this@Maps,
                        "Erro ao carregar empresas: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Empresa>>, t: Throwable) {
                Toast.makeText(
                    this@Maps,
                    "Erro ao conectar com o servidor: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun inicio(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
