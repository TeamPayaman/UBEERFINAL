package ceng319.teampayaman.ubeerfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import ceng319.teampayaman.ubeerfinal.Model.User;

public class SignUp extends AppCompatActivity {

    EditText edtphone,edtname,edtpassword;
    Button btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtname = (EditText) findViewById(R.id.fullname);
        edtpassword = (EditText) findViewById(R.id.password);
        edtphone = (EditText) findViewById(R.id.Phone);

        btnsignup = (Button)findViewById(R.id.registerBtn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please Wait..");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(edtphone.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone Number Already Registered",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mDialog.dismiss();
                            User user = new User(edtname.getText().toString(),edtpassword.getText().toString());
                            table_user.child(edtphone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign Up Successfully",Toast.LENGTH_SHORT).show();
                            finish();
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