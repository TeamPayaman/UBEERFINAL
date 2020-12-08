package ceng319.teampayaman.ubeerfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ceng319.teampayaman.ubeerfinal.Common.Common;
import ceng319.teampayaman.ubeerfinal.Model.User;

public class SignIn extends AppCompatActivity{

    EditText ubeerphone,ubeerpassword;
    Button ubeerbuttonsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ubeerpassword = findViewById(R.id.password);
        ubeerphone = findViewById(R.id.Phone);
        ubeerbuttonsignin = findViewById(R.id.loginbtn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");

        ubeerbuttonsignin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();


                final ValueEventListener user_does_not_exist = table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(ubeerphone.getText().toString()).exists()) {

                            mDialog.dismiss();


                            User user = dataSnapshot.child(ubeerphone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(ubeerpassword.getText().toString())) {
                                Intent homeIntent = new Intent(SignIn.this,ubeermain.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();

                            } else {
                                Toast.makeText(SignIn.this, "Wrong Password Please Enter Again", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User Does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}