package com.example.kalkulatorodleglosci;

import static com.example.kalkulatorodleglosci.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.Locale;
import butterknife.BindString;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindString(string.toastMessLackM)
    String toastMessLackM;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.toastNumber)
    String toastNumber;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.toastLackSelectedUnit)
    String toastLackSelectedUnit;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.distMM)
    String distMM;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.distML)
    String distML;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.distCa)
    String distCa;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.distJa)
    String distJa;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.distSt)
    String distSt;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.cMM)
    String cMM;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.cML)
    String cML;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.cCa)
    String cCa;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.cJa)
    String cJa;
    @SuppressLint("NonConstantResourceId")
    @BindString(string.cSt)
    String cSt;

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale locale = new Locale("pl");
        Locale.setDefault(locale);
        Resources resources = this.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, null);
        setContentView(layout.activity_main);
        ButterKnife.bind(this);

        RadioGroup radioGroup = findViewById(id.rGroup);
        RadioButton mileM = findViewById(id.rbMilaM);
        RadioButton mileL = findViewById(id.rbMilaL);
        RadioButton yard = findViewById(id.rbJard);
        RadioButton cale = findViewById(id.rbCal);

        TextView result = findViewById(id.tvWynik);
        Button button = findViewById(id.btnOdl);
        EditText dist = findViewById(id.etMetry);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == id.rbMilaM) {
                Toast.makeText(getApplicationContext(), cMM, Toast.LENGTH_SHORT).show();
            } else if (checkedId == id.rbMilaL) {
                Toast.makeText(getApplicationContext(), cML, Toast.LENGTH_SHORT).show();
            } else if (checkedId == id.rbCal) {
                Toast.makeText(getApplicationContext(), cCa, Toast.LENGTH_SHORT).show();
            } else if (checkedId == id.rbStopa) {
                Toast.makeText(getApplicationContext(), cSt, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), cJa, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(v -> {
            if (dist.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), toastMessLackM, Toast.LENGTH_SHORT).show();
                result.setText("");
            } else if (!isNumeric(dist.getText().toString())) {
                Toast.makeText(getApplicationContext(), toastNumber, Toast.LENGTH_SHORT).show();
                result.setText("");
            } else {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(getApplicationContext(), toastLackSelectedUnit, Toast.LENGTH_SHORT).show();
                    result.setText("");
                } else {
                    final double ds = Double.parseDouble(dist.getText().toString());
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(3);
                    df.setMinimumFractionDigits(0);
                    if (selectedId == mileM.getId()) {
                        MileMorskie mm = new MileMorskie(ds);
                        result.setText(String.format("%s: %s", distMM, df.format(mm.policzOdl())));
                    } else if (selectedId == mileL.getId()) {
                        MilaLad ml = new MilaLad(ds);
                        result.setText(String.format("%s: %s", distML, df.format(ml.policzOdl())));
                    } else if (selectedId == cale.getId()) {
                        Cal cl = new Cal(ds);
                        result.setText(String.format("%s: %s", distCa, df.format(cl.policzOdl())));
                    } else if (selectedId == yard.getId()) {
                        Jard ya = new Jard(ds);
                        result.setText(String.format("%s: %s", distJa, df.format(ya.policzOdl())));
                    } else {
                        Stopa st = new Stopa(ds);
                        result.setText(String.format("%s: %s", distSt, df.format(st.policzOdl())));
                    }
                }
            }
        });
    }
}