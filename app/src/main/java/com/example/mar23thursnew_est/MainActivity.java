package com.example.mar23thursnew_est;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Modify here to display what's changed in Helper and Control

    Spinner spinner;
    EditText nameEdit;
    EditText helloEdit;
    DatabaseControl control;
    Button addButton;
    Button getButton;
    TextView resultView;
    RecyclerView RecyclerView;
    Button getY;
    Button deleteButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control = new DatabaseControl(this);

        nameEdit = findViewById(R.id.nameEdit);
        helloEdit = findViewById(R.id.helloEdit);
        spinner = findViewById(R.id.spinner);
        addButton = findViewById(R.id.addButton);
        getButton = findViewById(R.id.getButton);
        resultView = findViewById(R.id.resultView);
        RecyclerView = findViewById(R.id.RecyclerView);
        getY = findViewById(R.id.getY);
        deleteButton = findViewById(R.id.deleteButton);

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            control.open();
            String sauce = control.getState(nameEdit.getText().toString());
            control.close();
            resultView.setText(sauce);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString();
                String state = ((TextView) spinner.getSelectedView()).getText().toString();
                String hello = helloEdit.getText().toString();
                control.open();
                boolean itWorked = control.insert(name, state, hello);
                control.close();
                if (itWorked){
                    Toast.makeText(getApplicationContext(), "Added " + name + " " +state+ " " + hello, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "FAILED " + name + " " +state+ " " + hello, Toast.LENGTH_LONG).show();
                }
                onResume();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString();
                control.open();
                control.delete(name);
                control.close();
                onResume();
            }
        });

        getY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                control.open();
                String sauce = control.getYear(nameEdit.getText().toString());
                control.close();
                resultView.setText(sauce);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        String[] tod;
        String[] wak;
        control.open();
        tod = control.getAllNames();
        wak = control.getAllYears();
        control.close();
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter adapter = new CustomAdapter(tod, wak);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomAdapter.ViewHolder viewHolder = (CustomAdapter.ViewHolder) view.getTag();
                TextView textView = viewHolder.getTextView();
                String name = textView.getText().toString();
                control.open();
                String state = control.getState(name);
                control.close();
                resultView.setText(name+": "+state);
            }
        });
        RecyclerView.setAdapter(adapter);

    }
}