package ceng319.teampayaman.ubeerfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import ceng319.teampayaman.ubeerfinal.R;

public class ubeermain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubeermain);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
            Intent i = new Intent(ubeermain.this, HomeActivity.class);
            startActivity(i);
            break;
            case R.id.nav_account:
                Intent h = new Intent(ubeermain.this, ActivityAccount.class);
                startActivity(h);
                break;
            case R.id.nav_payment:
                break;

            case R.id.nav_exit:
              FirebaseAuth.getInstance().signOut();
                Intent a = new Intent(ubeermain.this, MainActivity.class);
                startActivity(a);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}