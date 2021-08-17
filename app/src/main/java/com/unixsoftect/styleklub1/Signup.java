package com.unixsoftect.styleklub1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static android.os.Build.VERSION_CODES.P;

public class Signup extends AppCompatActivity {
    TextView login;
    Button signup;
    Sqldatabase sqldatabase;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextInputLayout namelayout, emaillayout, passwordlayout;
    EditText name , email , password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        namelayout = findViewById(R.id.namelayout1);
        emaillayout = findViewById(R.id.emaillayout1);
        passwordlayout = findViewById(R.id.passwordlayout1);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.lgbtn);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        sqldatabase = new Sqldatabase();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
            login = findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Signup.this,login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (Validate_password()&&Validate_email()&&Validate_name())
                {
                    createaccount create = new createaccount();
                    create.execute(name.getText().toString(),password.getText().toString(),email.getText().toString());
                }
                else
                {
                    Snackbar.make(view, "Fields cannot be empty", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

    }
    boolean Validate_name()
    {
        if (name.getText().toString().isEmpty())
        {
            namelayout.setError("enter the name ");
            return false;
        }
        else
        {
            return true;
        }
    }

    boolean Validate_email()
    {
        if (email.getText().toString().isEmpty()) {
            emaillayout.setError("Please enter the email ");
            return false;
        }
        else
        {
            return true;
        }
    }

    boolean Validate_password()
    {
        if (password.getText().toString().isEmpty())
        {
            passwordlayout.setError("Enter the password");
            return false;
        }
        else
        {
            return true;
        }
    }

    public class createaccount extends AsyncTask<String , Void ,String>
    {
        ProgressDialog pd = new ProgressDialog(Signup.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setCanceledOnTouchOutside(false);
            pd.setMessage("Creating account");
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try
            {
                Connection con = sqldatabase.con();
                if (con !=null)
                {
                    PreparedStatement preparedStatement = con.prepareStatement("insert into users (users,password,email) values (?,?,?) ");
                    preparedStatement.setString(1,strings[0]);
                    preparedStatement.setString(2,strings[1]);
                    preparedStatement.setString(3,strings[2]);
                    Log.e("users",strings[0] );
                    Log.e("password",strings[1] );
                    Log.e("email",strings[2] );
                    Boolean result = preparedStatement.execute();
                    Log.e("status" , result.toString());
                    if (result==true)
                    {
                        return "success";
                    }
                    else
                    {
                        return "not inserted";
                    }
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                return e.getMessage();
            }
            return "nothing";
        }

        @Override
        protected void onPostExecute(String s) {

            if (s.equals("success"))
            {
                Toast.makeText(Signup.this,"Account Created",Toast.LENGTH_SHORT).show();
            }
            pd.dismiss();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        String username = sharedPreferences.getString("Username","");
        String password = sharedPreferences.getString("Password","");
        if (!username.isEmpty() && !password.isEmpty())
        {
            Intent intent = new Intent(Signup.this,Drawer.class);
            startActivity(intent);
            finish();
        }
    }
}