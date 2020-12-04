package ceng319.teampayaman.ubeerfinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import ceng319.teampayaman.ubeerfinal.R;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;

    ListView listView;
    Button button;
    String mTitle[] = {"Hennessy", "Jack Daniel", "St.Remy", "Jagermeister", "Captain Morgan", "Absolut"};
    String mDescription[] = {"Liqours\n" +
            "VS Cognac 1L\n" +
            "$50",
            "\n" + "Whiskey 750ml\n" +
                    "$59.99 ",
            "VSOP Brandy 1140ml\n" +
                    "$41.75", "Jagermeister 750ml\n" + "$31.30", "\n" + "Spiced Rum 750 ml\n" + "$30.95", "Vodka 750ml\n" + "$29.25"};

    int images[] = {R.drawable.hennessy, R.drawable.jackdaniel, R.drawable.stremy,
            R.drawable.jagermeister, R.drawable.captainmorgan, R.drawable.absolut};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

        listView = findViewById(R.id.ubeerlistview);
        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[0]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", mTitle[0]);
                    intent.putExtra("description", mDescription[0]);
                    intent.putExtra("position", "" + 0);
                    button = findViewById(R.id.ubeerButtonBuy);
                    startActivity(intent);


                }
                if (position == 1) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[1]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", mTitle[1]);
                    intent.putExtra("description", mDescription[1]);
                    intent.putExtra("position", "" + 1);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[2]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", mTitle[2]);
                    intent.putExtra("description", mDescription[2]);
                    intent.putExtra("position", "" + 2);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[3]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", mTitle[3]);
                    intent.putExtra("description", mDescription[3]);
                    intent.putExtra("position", "" + 3);
                    startActivity(intent);
                }
            }
        });

    }



    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImages[];

        MyAdapter(Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.list_item_beer, R.id.ubeertextView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImages = imgs;
        }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = layoutInflater.inflate(R.layout.list_item_beer, parent, false);

        ImageView images = row.findViewById(R.id.ubeerimage);
        TextView myTitle = row.findViewById(R.id.ubeertextView1);
        TextView myDescription = row.findViewById(R.id.ubeertextView2);

        images.setImageResource(rImages[position]);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDescription[position]);

        return row;
    }

}

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu, menu);
        return true;
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
                Intent i = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(i);
                break;
            case R.id.nav_account:

                break;
            case R.id.nav_payment:
                break;

            case R.id.nav_exit:
                FirebaseAuth.getInstance().signOut();
                Intent a = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(a);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}