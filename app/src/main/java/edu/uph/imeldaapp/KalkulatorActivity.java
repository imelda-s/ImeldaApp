package edu.uph.imeldaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;

public class KalkulatorActivity extends AppCompatActivity {

    EditText edtNilaiA, edtNilaiB;
    Button btnTambah, btnKali, btnBagi;
    TextView txt, txtHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kalkulator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        setClickListeners();
    }

    public void initViews() {
        edtNilaiA = findViewById(R.id.edtNilaiA);
        edtNilaiB = findViewById(R.id.edtNilaiB);
        btnTambah = findViewById(R.id.btnTambah);
        btnKali = findViewById(R.id.btnKali);
        btnBagi = findViewById(R.id.btnBagi);
        txtHasil = findViewById(R.id.txtHasil);
    }

    public void setClickListeners() {
        //btnTambah
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformCalculation("tambah");
            }
        });

        //btnKali
        btnKali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformCalculation("kali");
            }
        });

        //btnBagi
        btnBagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformCalculation("bagi");
            }
        });
    }

    public void PerformCalculation(String operation) {
        //ambil nilai
        String strNilaiA = edtNilaiA.getText().toString().trim();
        String strNilaiB = edtNilaiB.getText().toString().trim();

        try {
            //ubah string ke integer
            int nilaiA = Integer.parseInt(strNilaiA);
            int nilaiB = Integer.parseInt(strNilaiB);

            //operasi
            switch (operation) {
                case "tambah":
                    int hasilTambah = nilaiA + nilaiB;
                    txtHasil.setText(String.valueOf(hasilTambah));
                    break;
                case "kali":
                    int hasilKali = nilaiA * nilaiB;
                    txtHasil.setText(String.valueOf(hasilKali));
                    break;
                case "bagi":
                    if (nilaiB == 0) {
                        Toast.makeText(this, "Tidak bisa membagi dengan nol", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int hasilBagi = nilaiA / nilaiB;
                    txtHasil.setText(String.valueOf(hasilBagi));
                    break;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Masukkan angaka bulat", Toast.LENGTH_SHORT).show();
        }
    }

}