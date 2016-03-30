package com.devesh.ecell_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Contact_us extends ActionBarActivity {

    private ListView listView;
    private DrawerLayout drawerLayout;
    //private DrawerListener drawerListener;
    private ActionBarDrawerToggle drawerListener;
    private String[] options;
    static ProgressDialog pDialog;
    JSONParser jparser = new JSONParser() ;
    JSONArray response=null;
    private static int check;
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        EditText editText=(EditText)findViewById(R.id.name);
        EditText editText2=(EditText)findViewById(R.id.number);
        EditText editText3=(EditText)findViewById(R.id.email);
        EditText editText4=(EditText)findViewById(R.id.reason);
        Button button=(Button)findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText;
                EditText editText2;
                EditText editText3;
                EditText editText4;


                editText = (EditText) findViewById(R.id.name);
                editText2 = (EditText) findViewById(R.id.email);
                editText3 = (EditText) findViewById(R.id.number);
                editText4 = (EditText) findViewById(R.id.reason);
                String name=editText.getText().toString();
                String number=editText2.getText().toString();
                String email=editText3.getText().toString();
                String reason=editText4.getText().toString();
                new LoadAll(name,number,email,reason).execute();
            }
        });
        Button button2=(Button)findViewById(R.id.back);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contact_us.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class LoadAll extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        String carname;
        String cartype;
        String carmodel;
        String carid;
        public LoadAll(String carname,String cartype,String carmodel,String carid)
        {
            this.carname=carname;
            this.cartype=cartype;
            this.carmodel=carmodel;
            this.carid=carid;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Contact_us.this);
            pDialog.setMessage("Loading . Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub

            System.out.println("THE NAme OF BODY TYPE"+carname);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL

            params.add(new BasicNameValuePair("name", carname));
            params.add(new BasicNameValuePair("number", cartype));
            params.add(new BasicNameValuePair("email", carmodel));
            params.add(new BasicNameValuePair("reason", carid));
            String url_ = "http://dk.site90.net/contact.php";
            //params.add(new BasicNameValuePair("car_type", car_name));
            JSONParser sh = new JSONParser();// a parser object to make service call



            // Making a request to url and getting response

            String jsonStr = sh.makeServiceCall(url_, JSONParser.POST,params);// POST method should be used, jsonstr has al the values availabel from api

            System.out.println("Entered doonback......");
            try {
                check = 0;

                JSONObject json = new JSONObject(jsonStr);

                System.out.println("Entered jsonobj......");

                success = 1;       // These throws an EXCEPTION e
                //              System.out.println("passes jsonarray......");
            }
            //        catch (JSONException e) {
            //          check=1;
            //        e.printStackTrace();
            //  }

            catch(Exception e)
            {
                System.out.println(e);
                check=2;
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread

            runOnUiThread(new Runnable() {
                public void run()
                {
                    if(check==1)
                    {
                        Toast.makeText(Contact_us.this, "Json Exception", Toast.LENGTH_LONG).show();
                    }

                    else if(check==2)
                    {
                        Toast.makeText(Contact_us.this, "Check Your Connection",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(success==1){
                            Toast.makeText(Contact_us.this, "Message is successfully sent", Toast.LENGTH_LONG).show();
                            Intent in= new Intent(Contact_us.this,MainActivity.class);
                            startActivity(in);}
                    }
                }
            });

        }


    }

}
