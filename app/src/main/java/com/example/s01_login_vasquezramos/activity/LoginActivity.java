package com.example.s01_login_vasquezramos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s01_login_vasquezramos.R;
import com.example.s01_login_vasquezramos.repository.AlumnoRepository;
import com.example.s01_login_vasquezramos.repository.DocenteRepository;

public class LoginActivity extends AppCompatActivity {

    private EditText codigoEditText, dniEditText, claveEditText;
    private RadioButton alumnoRadioButton, docenteRadioButton;
    private ImageView claveToggleImageView;

    private boolean isPasswordVisible = false;

    private AlumnoRepository alumnoRepo;
    private DocenteRepository docenteRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        codigoEditText = findViewById(R.id.codigoEditText);
        dniEditText = findViewById(R.id.dniEditText);
        claveEditText = findViewById(R.id.claveEditText);
        alumnoRadioButton = findViewById(R.id.alumnoRadioButton);
        docenteRadioButton = findViewById(R.id.docenteRadioButton);
        claveToggleImageView = findViewById(R.id.claveToggleImageView);
        RadioGroup userTypeGroup = findViewById(R.id.userTypeGroup);
        Button loginButton = findViewById(R.id.loginButton);
        TextView registerRedirect = findViewById(R.id.registerRedirect);

        alumnoRepo = new AlumnoRepository(this);
        docenteRepo = new DocenteRepository(this);

        userTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                change(checkedId);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        registerRedirect.setOnClickListener(new View.OnClickListener() {
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

    private void change(int checkedId) {
        // Primero limpia todos los checks
        alumnoRadioButton.setChecked(false);
        docenteRadioButton.setChecked(false);

        // Luego marca solo el seleccionado
        if (checkedId == R.id.alumnoRadioButton) {
            alumnoRadioButton.setChecked(true);
            codigoEditText.setHint("Código");
            dniEditText.setHint("DNI");
        } else if (checkedId == R.id.docenteRadioButton) {
            docenteRadioButton.setChecked(true);
            codigoEditText.setHint("Usuario");
            dniEditText.setHint("Tarjeta");
        }
    }

    private void login() {
        String campo1 = codigoEditText.getText().toString().trim();
        String campo2 = dniEditText.getText().toString().trim();
        String clave = claveEditText.getText().toString().trim();

        if (campo1.isEmpty() || campo2.isEmpty() || clave.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean credencialesValidas = alumnoRadioButton.isChecked()
                ? alumnoRepo.validarCredenciales(campo1, campo2, clave)
                : docenteRepo.validarCredenciales(campo1, campo2, clave);

        if (credencialesValidas) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }

    private void redirect() {
        startActivity(new Intent(this, RegisterActivity.class));
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