package ceng319.teampayaman.ubeerfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import ceng319.teampayaman.ubeerfinal.Common.Common;
import ceng319.teampayaman.ubeerfinal.Interface.ItemClickListener;
import ceng319.teampayaman.ubeerfinal.Model.Category;
import ceng319.teampayaman.ubeerfinal.R;
import ceng319.teampayaman.ubeerfinal.ViewHolder.MenuViewHolder;

public class ubeermain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseDatabase database;
    DatabaseReference category;
    TextView txtfullname;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
<<<<<<< HEAD
=======
    FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;
>>>>>>> cc987d7dac12d1729c17d95ea6743ae64923d3c0


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubeermain);

        toolbar = findViewById(R.id.ubeerfinaltoolbar);
        setSupportActionBar(toolbar);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        //set name for user
        View headerView = navigationView.getHeaderView(0);
        txtfullname = (TextView)headerView.findViewById(R.id.txtFullname);
        txtfullname.setText(Common.currentUser.getName());

        //load menu
        recycler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        loadMenu();
    }

    private void loadMenu(){
<<<<<<< HEAD
        FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.list_item_beer,MenuViewHolder.class,category) {
=======
         adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.list_item_beer,MenuViewHolder.class,category) {
>>>>>>> cc987d7dac12d1729c17d95ea6743ae64923d3c0
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, Category model, int i) {
                menuViewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(menuViewHolder.imageView);
                Category clickItem = model;
                menuViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
<<<<<<< HEAD
                        Toast.makeText(ubeermain.this,""+clickItem.getName(),Toast.LENGTH_SHORT).show();
=======
                        //Get CategoryID and send to ubeerdrinklist
                        Intent drinklist = new Intent(ubeermain.this,UbeerDrinkList.class);
                        drinklist.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(drinklist);
>>>>>>> cc987d7dac12d1729c17d95ea6743ae64923d3c0
                    }
                });
            }
        };
        recycler_menu.setAdapter(adapter);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
            Intent i = new Intent(ubeermain.this, ubeermain.class);
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