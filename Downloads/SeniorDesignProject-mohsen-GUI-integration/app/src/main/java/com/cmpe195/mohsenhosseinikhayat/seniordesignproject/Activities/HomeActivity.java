package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Fragments.HomeFragment;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Fragments.PantryFragment;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Fragments.PantryFragment2;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Fragments.RecipeBookFragment;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Fragments.RecipeSearchFragment;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Fragments.SettingsFragment;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new HomeFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");


        NavigationView nv = (NavigationView)findViewById(R.id.nv1);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent;

                switch (menuItem.getItemId()) {
                    case(R.id.home_id):
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container , new HomeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case(R.id.id_pantryAssistant):
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container , new PantryFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Pantry Assistant");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case(R.id.id_pantry):
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container , new PantryFragment2());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Pantry");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case(R.id.id_Search):
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container , new RecipeSearchFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Search Recipe");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case(R.id.id_recipeBook):
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container , new RecipeBookFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Recipe Book");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case(R.id.id_settings):
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container , new SettingsFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Settings");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case(R.id.id_pantryManagerActivity):
                        intent = new Intent(HomeActivity.this, PantryManagerActivity.class);
                        startActivity(intent);
                        getSupportActionBar().setTitle("Pantry Activity");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case(R.id.id_SearchActivity):
                        intent = new Intent(HomeActivity.this, RecipeSearchActivity.class);
                        startActivity(intent);
                        getSupportActionBar().setTitle("Search Activity");
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;



                }
                return true;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}