package com.example.lab5_20203651_iot;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab5_20203651_iot.R;

public class CrearTarea extends AppCompatActivity {

    // Constante para el nombre de la clave del extra
    public static final String EXTRA_CODIGO_PUCP = "codigoPUCP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_tarea);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Retrieve the extra data from the intent
        String codigoPUCP = getIntent().getStringExtra(EXTRA_CODIGO_PUCP);

        // Find the TextView and set its text
        TextView textView = findViewById(R.id.textView);
        textView.setText("Bienvenido @" + codigoPUCP);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            // Handle the back button click here
            onBackPressed();
            return true;
        } else if (id == R.id.action_create_task) {
            // Create an intent to start CrearTareaForm activity
            Intent intent = new Intent(this, CrearTareaForm.class);
            intent.putExtra(EXTRA_CODIGO_PUCP, getIntent().getStringExtra(EXTRA_CODIGO_PUCP));
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}

