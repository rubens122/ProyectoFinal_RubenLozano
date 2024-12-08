package com.example.proyectofinal_rubenlozano.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.proyectofinal_rubenlozano.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class EventosFragment : Fragment(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_eventos, container, false)
        iniciarMapa()
        return view
    }

    private fun iniciarMapa() {
        val fragment = SupportMapFragment()
        childFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fm_maps, fragment)
        }
        fragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true

        marcarSitiosMusica()
        gestionarLocalizacion()
    }

    private fun marcarSitiosMusica() {
        val sitios = listOf(
            LatLng(40.452157, -3.688344) to "WiZink Center, Madrid",
            LatLng(41.364798, 2.155806) to "Palau Sant Jordi, Barcelona",
            LatLng(37.380271, -5.986842) to "Estadio La Cartuja, Sevilla",
            LatLng(39.482635, -0.345233) to "Ciudad de las Artes y las Ciencias, Valencia",
            LatLng(43.262985, -2.934991) to "Bilbao Arena, Bilbao",
            LatLng(36.721302, -4.421637) to "Auditorio Municipal, Málaga",
            LatLng(40.416775, -3.703790) to "Teatro Real, Madrid",
            LatLng(39.860052, -4.027339) to "Festival Viña Rock, Villarrobledo"
        )

        for ((location, name) in sitios) {
            val marker = MarkerOptions().position(location).title(name)
            map.addMarker(marker)
        }

        val espania = LatLng(40.416775, -3.703790)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(espania, 5.5f))
    }

    private fun gestionarLocalizacion() {
        if (!::map.isInitialized) return
        if (
            ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            map.isMyLocationEnabled = true
        }
    }
}
