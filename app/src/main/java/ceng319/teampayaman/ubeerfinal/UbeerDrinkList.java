package ceng319.teampayaman.ubeerfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import ceng319.teampayaman.ubeerfinal.Interface.ItemClickListener;
import ceng319.teampayaman.ubeerfinal.Model.Drinks;
import ceng319.teampayaman.ubeerfinal.ViewHolder.DrinkViewHolder;

public class UbeerDrinkList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference drinklist;

    String categoryId = "";
    FirebaseRecyclerAdapter<Drinks, DrinkViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubeer_drink_list);

        database = FirebaseDatabase.getInstance();
        drinklist = database.getReference("Drinks");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_drink);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent here
        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId != null)
        {
            loadListDrinks(categoryId);
        }
    }

    private void loadListDrinks(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Drinks, DrinkViewHolder>(Drinks.class,
                R.layout.drink_item,DrinkViewHolder.class,drinklist.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(DrinkViewHolder drinkViewHolder, Drinks model, int i) {
                drinkViewHolder.drink_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(drinkViewHolder.drink_image);
                final Drinks local = model;
                drinkViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(UbeerDrinkList.this,""+local.getName(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);
    }
}