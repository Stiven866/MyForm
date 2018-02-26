package com.stivenduque.myform;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainForm extends AppCompatActivity {

    EditText etUser, etEmail, etPswd, etPswdConf;
    RadioButton rbtnMale, rbtnFamale;
    CheckBox cbSwim, cbRead, cbCook, cbDance;
    Button btnEnterDate, btnSave;
    TextView tvText;
    Spinner spinnerCitys;
    Calendar myCalendar;
    ArrayAdapter<CharSequence> adapter;
    private String nameUser, email, pswd, pswdConf, swim, read, cook, dance, city;
    private int  year, month, day;
    public MainForm() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);
        defineVariable();
        spinnerCitys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setCity("Medellín");
                        break;
                    case 1:
                        setCity("Bogota");
                        break;
                    case 2:
                        setCity("Bucaramanga");
                        break;
                    case 3:
                        setCity("Cali");
                        break;
                    case 4:
                        setCity("Barranquilla");
                        break;
                    case 5:
                        setCity("Cartagena");
                        break;
                    case 6:
                        setCity("Pereira");
                        break;
                    case 7:
                        setCity("Cúcuta");
                        break;
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNameUser(etUser.getText().toString());
                setEmail(etEmail.getText().toString());
                setPswd(etPswd.getText().toString());
                setPswdConf(etPswdConf.getText().toString());
                if (checked() == false) {
                    tvText.setText("Señor(a) usuario por favor llene todos lo campos para hacer efectivo " +
                            "su registro");
                } else {
                    if (getPswd().equals(getPswdConf())) {
                        checkedCheckBox();
                        if (rbtnMale.isChecked() == true) {
                            tvText.setText("Hola!!! " + "señor " + getNameUser().toString() + "\n" + "Su correo electronico es: " +
                                    getEmail().toString() + "\n" + "Su contraseña es: " + getPswd().toString() + "\n" + "Su ciudad de origen es " +
                                    getCity() + "\n" + "Su fecha de nacimiento es: " + String.valueOf(getDay()) + "/" + String.valueOf(getMonth()) + "/" + String.valueOf(getYear()));
                        } else if (rbtnFamale.isChecked() == true) {
                            tvText.setText("Hola!!! " + "señora " + getNameUser().toString() + "\n" + "Su correo electronico es: " +
                                    getEmail().toString() + "\n" + "Su contraseña es: " + getPswd().toString() + "\n" + "su ciudad de origen es " +
                                    getCity()+ "\n" + "Su fecha de nacimiento es: " + String.valueOf(getDay()) + "/" + String.valueOf(getMonth()) + "/" + String.valueOf(getYear()));
                        }

                        if (cbCook.equals("")) {
                            tvText.setText(tvText.getText() + "\n" + getCook());
                        } else {
                            tvText.setText(tvText.getText() + "\n" + getCook());
                        }

                        if (cbDance.equals("")) {
                        } else {
                            tvText.setText(tvText.getText() + "\n" + getDance());
                        }
                        if (cbRead.equals("")) {
                        } else {
                            tvText.setText(tvText.getText() + "\n" + getRead());
                        }
                        if (cbSwim.equals("")) {
                        } else {
                            tvText.setText(tvText.getText() + "\n" + getSwim());
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        btnEnterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(etUser.getWindowToken(), 0);
                inputMethodManager.hideSoftInputFromWindow(etPswdConf.getWindowToken(), 0);
                inputMethodManager.hideSoftInputFromWindow(etPswd.getWindowToken(), 0);
                inputMethodManager.hideSoftInputFromWindow(etEmail.getWindowToken(), 0);
                setDay(myCalendar.get(Calendar.DAY_OF_MONTH));
                setMonth(myCalendar.get(Calendar.MONTH));
                setYear(myCalendar.get(Calendar.YEAR));
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    setYear(year);
                    setDay(dayOfMonth);
                    setMonth(month + 1);
                    }
                }, getYear(),getMonth(),getDay());
                datePickerDialog.show();
            }
        });

    }

    public void defineVariable() {
        etUser = findViewById(R.id.etUser);
        etEmail = findViewById(R.id.etEmail);
        etPswd = findViewById(R.id.etPswd);
        etPswdConf = findViewById(R.id.etPswdconf);
        rbtnFamale = findViewById(R.id.rbFamale);
        rbtnMale = findViewById(R.id.rbMale);
        cbSwim = findViewById(R.id.chbSwim);
        cbCook = findViewById(R.id.chbCook);
        cbDance = findViewById(R.id.chbDancing);
        cbRead = findViewById(R.id.chbRead);
        btnEnterDate = findViewById(R.id.btnDate);
        btnSave = findViewById(R.id.btnSave);
        tvText = findViewById(R.id.tvText);
        spinnerCitys = findViewById(R.id.citysSpinner);

        adapter = ArrayAdapter.createFromResource(this, R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCitys.setAdapter(adapter);
        myCalendar = Calendar.getInstance();

    }

    private void checkedCheckBox() {
        if (cbRead.isChecked() == true)
            setRead("Te gusta leer");
        else
            setRead("No te gusta leer");
        if (cbDance.isChecked() == true)
            setDance("Te gusta bailar");
        else
            setDance("No te gusta bailar");
        if (cbCook.isChecked())
            setCook("Te gusta cocinar");
        else
            setCook("No te gusta cocinar");
        if (cbSwim.isChecked() == true)
            setSwim("Te gusta Nadar");
        else
            setSwim("No te gusta nadar");
    }

    private boolean checked() {
        if (getNameUser().equals("")) {
            Toast.makeText(getApplicationContext(), "No has ingresado el nombre de usuario",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (getEmail().equals("")) {
            Toast.makeText(getApplicationContext(), "No has ingresado el correo",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (getPswd().equals("")) {
            Toast.makeText(getApplicationContext(), "No has ingresado la contraseña",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (getPswdConf().equals("")) {
            Toast.makeText(getApplicationContext(), "No has ingresado la confirmación de la contraseña",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (rbtnMale.isChecked() == false && rbtnFamale.isChecked() == false) {
            Toast.makeText(getApplicationContext(), "No has selecionado el sexo",
                    Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }

    }

    public String getNameUser() {
        return nameUser;
    }
    

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getPswdConf() {
        return pswdConf;
    }

    public void setPswdConf(String pswdConf) {
        this.pswdConf = pswdConf;
    }

    public String getSwim() {
        return swim;
    }

    public void setSwim(String swim) {
        this.swim = swim;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getCook() {
        return cook;
    }

    public void setCook(String cook) {
        this.cook = cook;
    }

    public String getDance() {
        return dance;
    }

    public void setDance(String dance) {
        this.dance = dance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getYear() {return year;}

    public void setYear(int year) {this.year = year;}

    public int getMonth() {return month;}

    public void setMonth(int month) {this.month = month;}

    public int getDay() {return day;}

    public void setDay(int day) {this.day = day;}
}
