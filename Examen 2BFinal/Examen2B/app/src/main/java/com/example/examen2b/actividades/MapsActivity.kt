package com.example.examen2b.actividades

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.examen2b.R
import com.example.examen2b.valoresEstaticos.Datos

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var tienePermisosLocalizacion = false
    private var idBiblioteca = -1

    fun generateBitmapDescriptorFromRes(
        context: Context?, resId: Int
    ): BitmapDescriptor? {
        val drawable = context?.let { ContextCompat.getDrawable(it, resId) }
        drawable!!.setBounds(
            0,
            0,
            drawable.intrinsicWidth,
            drawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.draw(canvas)
        return fromBitmap(bitmap)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        idBiblioteca = this.intent.getIntExtra("idBiblioteca", -1)
        solicitarPermisosLocalizacion()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        establecerConfiguracionMapa(mMap)

        Datos.listaLibroCompleta.forEach { libro ->
            Log.i("http", "${libro.nombre}")
            var latitud = libro.latitud.toDouble()
            var longitud = libro.longitud.toDouble()
            anadirMarcador(LatLng(latitud, longitud), libro.nombre)
        }

        mMap.setOnMarkerClickListener { marker ->
            irListaLibros(idBiblioteca)
            Toast.makeText(
                this,
                "${marker.title}\n${marker.position}",
                Toast.LENGTH_LONG
            ).show()
            true

        }
    }

    fun solicitarPermisosLocalizacion() {
        val permisoFineLocation = ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )

        val tienePermiso = permisoFineLocation == PackageManager.PERMISSION_GRANTED

        if (tienePermiso) {
            Log.i("mapa", "Tiene permisos de FINE_LOCATION")
            this.tienePermisosLocalizacion = true
        } else {
            // Codigo que vamos a esperar (1)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

    }

    fun establecerConfiguracionMapa(mapa: GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisoFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )

            val tienePermiso = permisoFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermiso) {
                mapa.isMyLocationEnabled = true
            }

            this.uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }

    }

    fun anadirMarcador(latLng: LatLng, title: String) {
        // mMap.addMarker(MarkerOptions().position(latLng).title(title))
        //mMap.addMarker(MarkerOptions().icon(fromResource(R.mipmap.ic_marcador)).anchor(0.0f,1.0f)
        //    .position(latLng).title(title))
        mMap.addMarker(MarkerOptions().position(latLng).title(title)
            .icon(generateBitmapDescriptorFromRes(this, R.mipmap.ic_marcador)))
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID)

    }

    fun irListaLibros(idBiblioteca: Int) {
        val intent = Intent(
            this,
            ListaLibros::class.java
        )
        intent.putExtra("idBiblioteca", idBiblioteca)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
