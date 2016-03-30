package com.devesh.ecell_app;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

@SuppressLint("DefaultLocale")
public class ChangePrice extends Activity implements View.OnClickListener{
 
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private EditText editTextStartUpName;
    private EditText editTextStartUpDescription;
    private EditText editTextOccupation;
    private Button Proceed;
    private static final String REGISTER_URL ="http://collegebox.esy.es/ECell/Incubation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changerate);

        editTextName = (EditText) findViewById(R.id.name);
        editTextPhone = (EditText) findViewById(R.id.phone);
        editTextStartUpName = (EditText) findViewById(R.id.startUpName);
        editTextStartUpDescription = (EditText) findViewById(R.id.startUpDescribtion);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextOccupation = (EditText) findViewById(R.id.occupation);
        Proceed = (Button) findViewById(R.id.Proceed);
        Proceed.setOnClickListener(this);
    }
 
    @Override
    public void onClick(View v) {
        if(v == Proceed){
            registerUser();

        }
    }
 
    @SuppressLint("DefaultLocale")
	private void registerUser() {

        String Name = editTextName.getText().toString();
        String Phone = editTextPhone.getText().toString();
        String StartUpName = editTextStartUpName.getText().toString();
        String StartUpDescription = editTextStartUpDescription.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Occupation = editTextOccupation.getText().toString();

        register(Name,Phone,StartUpName,StartUpDescription,Email,Occupation);
    }
 
    public void register(String Name, String Phone,String StartUpName,String StartUpDescription,String Email,String Occupation) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();
 
 
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ChangePrice.this, "Please Wait",null, true, true);
            }
 
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
              /*  Intent inttt = new Intent(ChangePrice.this,JsonParsing.class);
                startActivity(inttt);
                */
            }
 
            @Override
            protected String doInBackground(String... params) {
 
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("Name",params[0]);
                data.put("Phone",params[1]);
                data.put("StartUpName",params[2]);
                data.put("StartUpDescription",params[3]);
                data.put("Email",params[4]);
                data.put("Occupation",params[5]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);
 
                return  result;
            }
        }
 
        RegisterUser ru = new RegisterUser();
        ru.execute(Name,Phone,StartUpName,StartUpDescription,Email,Occupation);
    }
}