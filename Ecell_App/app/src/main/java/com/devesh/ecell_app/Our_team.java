package com.devesh.ecell_app;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;


public class Our_team extends ActionBarActivity {
    GridView gv;
    Context context;
    ArrayList prgmName;
    public static String [] prgmNameList={"Aman Dewangan","Harshit Dewangan","Ashish Soni","Manish Kumar jha","Priyasnshu Thakur"};
    public static String [] list={"Core Member","Core Member","Core Member","Core Member","Core Member"};
    public static int [] prgmImages={R.drawable.sir,R.drawable.sir2,R.drawable.sir4,R.drawable.sir6,R.drawable.sir8};
    public static ArrayList<Integer> id_members=new ArrayList<>();
    public static ArrayList<String> name_members=new ArrayList<>();
    public static ArrayList<String> designation=new ArrayList<>();
    public static ArrayList<String> domain=new ArrayList<>();
    public static ArrayList<String> mobile=new ArrayList<>();
    public static ArrayList<String> member_image=new ArrayList<>();
    public String[] member_name;
    public String[] designation_members;
    public String[] image_member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_team);
        member_name=new String[name_members.size()];
                name_members.toArray(member_name);
        designation_members=new String[designation.size()];
                designation.toArray(designation_members);
        image_member=new String[member_image.size()];
                member_image.toArray(image_member);
        gv=(GridView) findViewById(R.id.gridView1);
        gv.setAdapter(new CustomAdapter(Our_team.this, member_name, image_member,designation_members));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_our_team, menu);
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
}
