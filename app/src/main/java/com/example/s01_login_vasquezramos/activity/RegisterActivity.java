package com.example.s01_login_vasquezramos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s01_login_vasquezramos.R;
import com.example.s01_login_vasquezramos.model.Alumno;
import com.example.s01_login_vasquezramos.repository.AlumnoRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText codigoEditText, dniEditText, claveEditText;
    private ImageView claveToggleImageView;

    private boolean isPasswordVisible = false;

    private AlumnoRepository alumnoRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        codigoEditText = findViewById(R.id.codigoEditText);
        dniEditText = findViewById(R.id.dniEditText);
        claveEditText = findViewById(R.id.claveEditText);
        claveToggleImageView = findViewById(R.id.claveToggleImageView);
        Button registerButton = findViewById(R.id.registerButton);
        TextView loginRedirect = findViewById(R.id.loginRedirect);

        alumnoRepo = new AlumnoRepository(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect();
            }
        });

        claveToggleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
    }

    private void register() {
        String codigo = codigoEditText.getText().toString().trim();
        String dni = dniEditText.getText().toString().trim();
        String clave = claveEditText.getText().toString().trim();

        if (codigo.isEmpty() || dni.isEmpty() || clave.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setCodigo(codigo);
        nuevoAlumno.setDni(dni);
        nuevoAlumno.setClave(clave);

        boolean exito = alumnoRepo.registrarAlumno(nuevoAlumno);
        if (exito) {
            Toast.makeText(this, "Alumno registrado correctamente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Error: código o DNI ya registrado", Toast.LENGTH_LONG).show();
        }
    }

    private void redirect() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Ocultar la contraseña
            claveEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            claveToggleImageView.setImageResource(R.drawable.baseline_visibility_24);
        } else {
            // Mostrar la contraseña
            claveEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            claveToggleImageView.setImageResource(R.drawable.baseline_visibility_off_24);
        }
        // Mover el cursor al final del texto
        claveEditText.setSelection(claveEditText.length());
        isPasswordVisible = !isPasswordVisible;
    }
}