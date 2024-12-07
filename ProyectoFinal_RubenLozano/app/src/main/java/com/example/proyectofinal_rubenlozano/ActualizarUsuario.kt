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

        // Cargar datos iniciales del usuario
        cargarDatosUsuario()

        // Configurar los listeners de los botones
        setListeners()
    }

    // Método para cargar los datos del usuario en los campos
    private fun cargarDatosUsuario() {
        binding.etAcEmail.setText(usuario.email)
        binding.etAcPassword.setText(usuario.password)
    }

    // Método que configura los listeners de los botones
    private fun setListeners() {
        // Configurar listener para el botón de guardar
        binding.btnGuardar.setOnClickListener { guardarCambios() }

        // Configurar listener para el botón de limpiar
        binding.btnReset.setOnClickListener { limpiarCampos() }

        // Configurar listener para el botón de cancelar
        binding.btnCancelar.setOnClickListener { cancelarEdicion() }
    }

    // Método para guardar los cambios en el usuario
    private fun guardarCambios() {
        // Validar campos
        val nuevoEmail = binding.etAcEmail.text.toString().trim()
        val nuevaPassword = binding.etAcPassword.text.toString().trim()

        if (nuevoEmail.isEmpty() || nuevaPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, llena todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        // Actualizar objeto usuario
        usuario.email = nuevoEmail
        usuario.password = nuevaPassword

        // Guardar cambios en la base de datos
        val resultado = CrudUsuarios().update(usuario)

        if (resultado) {
            Toast.makeText(this, "Usuario actualizado correctamente.", Toast.LENGTH_SHORT).show()
            finish() // Finalizar actividad
        } else {
            Toast.makeText(this, "Error al actualizar el usuario.", Toast.LENGTH_SHORT).show()
        }
    }

    // Método para limpiar los campos de texto
    private fun limpiarCampos() {
        binding.etAcEmail.text.clear()
        binding.etAcPassword.text.clear()
    }

    // Método para cancelar la edición y cerrar la actividad
    private fun cancelarEdicion() {
        Toast.makeText(this, "Edición cancelada.", Toast.LENGTH_SHORT).show()
        finish()
    }
}