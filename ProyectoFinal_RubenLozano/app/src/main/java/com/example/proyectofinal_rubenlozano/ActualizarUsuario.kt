package com.example.proyectofinal_rubenlozano

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofinal_rubenlozano.databinding.ActivityActualizarUsuarioBinding
import com.example.proyectofinal_rubenlozano.models.UsuarioModel
import com.example.proyectofinal_rubenlozano.providers.db.CrudUsuarios

class ActualizarUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarUsuarioBinding
    private lateinit var usuario: UsuarioModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityActualizarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        usuario = intent.getSerializableExtra("USUARIO") as UsuarioModel

        cargarDatosUsuario()
        setListeners()
    }

    private fun cargarDatosUsuario() {
        binding.etEmail2.setText(usuario.email)
        binding.etPassword2.setText(usuario.password)
    }

    private fun setListeners() {
        binding.btnGuardar2.setOnClickListener { guardarCambios() }
        binding.btnLimpiar.setOnClickListener { limpiarCampos() }
        binding.btnCancelar.setOnClickListener { cancelarEdicion() }
    }

    private fun guardarCambios() {
        val nuevoEmail = binding.etEmail2.text.toString().trim()
        val nuevaPassword = binding.etPassword2.text.toString().trim()

        if (nuevoEmail.isEmpty() || nuevaPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, llena todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        usuario.email = nuevoEmail
        usuario.password = nuevaPassword

        val resultado = CrudUsuarios().update(usuario)

        if (resultado) {
            Toast.makeText(this, "Usuario actualizado correctamente.", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Error al actualizar el usuario.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiarCampos() {
        binding.etEmail2.text.clear()
        binding.etPassword2.text.clear()
    }

    private fun cancelarEdicion() {
        Toast.makeText(this, "Edición cancelada.", Toast.LENGTH_SHORT).show()
        finish()
    }
}