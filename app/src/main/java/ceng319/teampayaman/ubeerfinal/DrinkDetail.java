package ceng319.teampayaman.ubeerfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import ceng319.teampayaman.ubeerfinal.Database.Database;
import ceng319.teampayaman.ubeerfinal.Model.Drinks;
import ceng319.teampayaman.ubeerfinal.Model.Order;

public class DrinkDetail extends AppCompatActivity {

    TextView drink_name,drink_price,drink_description;
    ImageView drink_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String drinkId="";

    FirebaseDatabase database;
    DatabaseReference drinks;

    Drinks currentDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        database = FirebaseDatabase.getInstance();
        drinks = database.getReference("Drinks");

        numberButton = (ElegantNumberButton)findViewById(R.id.ubeernumber_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addtoCart(new Order(
                        drinkId,
                        currentDrink.getName(),
                        numberButton.getNumber(),
                        currentDrink.getPrice(),
                        currentDrink.getDiscount()

                ));
                Toast.makeText(DrinkDetail.this,"Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        drink_description = (TextView)findViewById(R.id.drink_description);
        drink_name = (TextView)findViewById(R.id.drink_name);
        drink_price = (TextView)findViewById(R.id.drink_price);
        drink_image = (ImageView) findViewById(R.id.img_drink);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);

        if(getIntent() != null)
            drinkId = getIntent().getStringExtra("DrinkId");
        if(!drinkId.isEmpty())
        {
            getDetailDrink(drinkId);
        }
    }

    private void getDetailDrink(String drinkId) {
        drinks.child(drinkId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentDrink = dataSnapshot.getValue(Drinks.class);

                Picasso.with(getBaseContext()).load(currentDrink.getImage()).into(drink_image);

                collapsingToolbarLayout.setTitle(currentDrink.getName());
                drink_price.setText(currentDrink.getPrice());
                drink_name.setText(currentDrink.getName());
                drink_description.setText(currentDrink.getDescription());

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
