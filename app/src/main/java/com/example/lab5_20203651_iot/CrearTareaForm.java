package com.example.lab5_20203651_iot;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CrearTareaForm extends AppCompatActivity {

    private EditText editTextDate;
    private EditText editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea_form);
        EdgeToEdge.enable(this);

        // Set up WindowInsetsListener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize EditText fields
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);

        // Set OnClickListener for editTextDate
        editTextDate.setOnClickListener(v -> showDatePicker());

        // Set OnClickListener for editTextTime
        editTextTime.setOnClickListener(v -> showTimePicker());

        // Retrieve codigoPUCP from Intent extra
        String codigoPUCP = getIntent().getStringExtra(CrearTarea.EXTRA_CODIGO_PUCP);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_crear, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.createNewTarea) {
            // Handle the create new tarea action
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Method to show Date Picker
    private void showDatePicker() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Selecciona una fecha");
        MaterialDatePicker<Long> materialDatePicker = builder.build();
        materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER_TAG");

        // Listener for positive button click
        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            // Convert selection to a readable date format
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(selection);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            editTextDate.setText(dateFormat.format(calendar.getTime()));
        });
    }

    // Method to show Time Picker
    private void showTimePicker() {
        MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder();
        builder.setTitleText("Selecciona una hora");
        builder.setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK);
        MaterialTimePicker materialTimePicker = builder.build();
        materialTimePicker.show(getSupportFragmentManager(), "TIME_PICKER_TAG");

        // Listener for positive button click
        materialTimePicker.addOnPositiveButtonClickListener(dialog -> {
            // Get selected hour and minute
            int hour = materialTimePicker.getHour();
            int minute = materialTimePicker.getMinute();

            // Format hour and minute
            String time = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);

            // Set selected time to EditText
            editTextTime.setText(time);
        });
    }
}


