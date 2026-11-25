package es.studium.miavatar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnDialogoListener {

    Button btnCrear, btnReiniciar;
    LinearLayout layoutAvatar;
    TextView tvBienvenida, tvNombre, tvEspecie, tvProfesion;
    TextView tvVida, tvMagia, tvFuerza, tvVelocidad;
    ImageView imgEspecie, imgSexo, imgProfesion;

    DialogoNombre dialogoNombre;
    DialogoSexo dialogoSexo;
    DialogoEspecie dialogoEspecie;
    DialogoProfesion dialogoProfesion;

    String nombre;
    int sexo, especie, profesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCrear = findViewById(R.id.btnCrear);
        btnReiniciar = findViewById(R.id.btnReiniciar);
        layoutAvatar = findViewById(R.id.layoutAvatar);
        tvBienvenida = findViewById(R.id.tvBienvenida);
        tvNombre = findViewById(R.id.tvNombre);
        tvEspecie = findViewById(R.id.tvEspecie);
        tvProfesion = findViewById(R.id.tvProfesion);
        tvVida = findViewById(R.id.tvVida);
        tvMagia = findViewById(R.id.tvMagia);
        tvFuerza = findViewById(R.id.tvFuerza);
        tvVelocidad = findViewById(R.id.tvVelocidad);
        imgEspecie = findViewById(R.id.imgEspecie);
        imgSexo = findViewById(R.id.imgSexo);
        imgProfesion = findViewById(R.id.imgProfesion);

        btnCrear.setOnClickListener(v -> {
            empezar();
        });
        btnReiniciar.setOnClickListener(v -> {
            reiniciar();
        });
    }

    // Primer diálogo (Nombre)
    private void empezar() {
        dialogoNombre = new DialogoNombre();
        dialogoNombre.setCancelable(false);
        dialogoNombre.show(getSupportFragmentManager(), "nombre");
    }

    // Volver al principio
    private void reiniciar() {
        layoutAvatar.setVisibility(View.GONE);
        tvBienvenida.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNombreElegido(String texto) {
        if (texto.trim().isEmpty()) {
            Toast.makeText(this, R.string.mensaje_nombre_vacio, Toast.LENGTH_SHORT).show();
            empezar();
        } else {
            nombre = texto;
            dialogoSexo = new DialogoSexo();
            dialogoSexo.setCancelable(false);
            dialogoSexo.show(getSupportFragmentManager(), "sexo");
        }
    }

    @Override
    public void onSexoElegido(int s) {
        sexo = s;
        dialogoEspecie = new DialogoEspecie();
        dialogoEspecie.setCancelable(false);
        dialogoEspecie.show(getSupportFragmentManager(), "especie");
    }

    @Override
    public void onEspecieElegida(int e) {
        especie = e;
        dialogoProfesion = new DialogoProfesion();
        dialogoProfesion.setCancelable(false);
        dialogoProfesion.show(getSupportFragmentManager(), "profesion");
    }

    @Override
    public void onProfesionElegida(int p) {
        profesion = p;
        mostrarAvatar();
    }

    @Override
    public void onDialogoCancelado() {
        Toast.makeText(this, R.string.mensaje_cancelado, Toast.LENGTH_SHORT).show();
        reiniciar();
    }

    private void mostrarAvatar() {
        // Capa del sexo
        if (sexo == 0) {
            imgSexo.setImageResource(R.drawable.ic_sexo_hombre);
        } else {
            imgSexo.setImageResource(R.drawable.ic_sexo_mujer);
        }

        // Capa de la especie
        if (especie == 0) {
            imgEspecie.setImageResource(R.drawable.ic_especie_elfo);
            tvEspecie.setText(R.string.especie_elfo);
        } else if (especie == 1) {
            imgEspecie.setImageResource(R.drawable.ic_especie_enano);
            tvEspecie.setText(R.string.especie_enano);
        } else if (especie == 2) {
            imgEspecie.setImageResource(R.drawable.ic_especie_hobbit);
            tvEspecie.setText(R.string.especie_hobbit);
        } else {
            imgEspecie.setImageResource(R.drawable.ic_especie_humano);
            tvEspecie.setText(R.string.especie_humano);
        }

        // Capa de la profesión
        if (profesion == 0) {
            imgProfesion.setImageResource(R.drawable.ic_profesion_arquero);
            tvProfesion.setText(R.string.profesion_arquero);
        } else if (profesion == 1) {
            imgProfesion.setImageResource(R.drawable.ic_profesion_guerrero);
            tvProfesion.setText(R.string.profesion_guerrero);
        } else if (profesion == 2) {
            imgProfesion.setImageResource(R.drawable.ic_profesion_mago);
            tvProfesion.setText(R.string.profesion_mago);
        } else if (profesion == 3) {
            imgProfesion.setImageResource(R.drawable.ic_profesion_herrero);
            tvProfesion.setText(R.string.profesion_herrero);
        } else {
            imgProfesion.setImageResource(R.drawable.ic_profesion_minero);
            tvProfesion.setText(R.string.profesion_minero);
        }

        tvNombre.setText(nombre);

        // Poderes aleatorios
        Random aleatorio = new Random();
        int vida = aleatorio.nextInt(101);      // 0 a 100
        int magia = aleatorio.nextInt(11);      // 0 a 10
        int fuerza = aleatorio.nextInt(21);     // 0 a 20
        int velocidad = aleatorio.nextInt(6);   // 0 a 5
        tvVida.setText(getString(R.string.vida) + " " + vida);
        tvMagia.setText(getString(R.string.magia) + " " + magia);
        tvFuerza.setText(getString(R.string.fuerza) + " " + fuerza);
        tvVelocidad.setText(getString(R.string.velocidad) + " " + velocidad);

        tvBienvenida.setVisibility(View.GONE);
        layoutAvatar.setVisibility(View.VISIBLE);
    }
}