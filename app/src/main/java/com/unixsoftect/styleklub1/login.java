package com.unixsoftect.styleklub1;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONException;
import org.json.JSONObject;
public class login extends AppCompatActivity {
    TextView sign;
    Button login;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextInputLayout userlayout, passwordlayout;
    TextInputEditText user, password;
    Sqldatabase sqldatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login1);
        userlayout = findViewById(R.id.emaillayout);
        passwordlayout = findViewById(R.id.passwordlayout);
        user = findViewById(R.id.user);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        password = findViewById(R.id.password1);
        sqldatabase = new Sqldatabase();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        sign = findViewById(R.id.sign);
        sign.setOnClickListener(view -> {
            Intent intent = new Intent(login.this, Signup.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        login.setOnClickListener(view -> {
            if(Validate_User() && Validate_password()) {
                Login(user.getText().toString(), password.getText().toString());
//                    Login login2 = new Login();
                //    login2.execute(user.getText().toString(), password.getText().toString());
            } else {
                Snackbar.make(view, "Fields cannot be empty", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
    private void Login(String user, String pass) {
        ProgressDialog pd = new ProgressDialog(login.this);
        pd.setMessage("Loading");
        pd.setCancelable(false);
        pd.show();
        Request request = new Request(login.this, "/api/login?user=" + user + "&pass=" + pass + "", com.android.volley.Request.Method.GET) {
            @Override
            public void onresponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("userexist")) {
                        editor = sharedPreferences.edit();
                        editor.putString("Username", user);
                        //editor.putString("Name", name);
                        editor.putString("Password", pass);
                        editor.apply();
                        Intent intent = new Intent(login.this, Drawer.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(login.this, "no user found", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    e.getMessage();
                }
                pd.dismiss();
            }
            @Override
            public void onError(VolleyError error) {
                Toast.makeText(login.this, "something went wrong", Toast.LENGTH_SHORT).show();
                error.getMessage();
                pd.dismiss();
            }
        };
    }
    boolean Validate_User() {
        if (user.getText().toString().isEmpty()) {
            userlayout.setError("email can't be empty");
            return false;
        } else {
            return true;
        }
    }
    boolean Validate_password() {
        if (password.getText().toString().isEmpty()) {
            passwordlayout.setError("password can't be empty");
            return false;
        } else {
            return true;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        String username = sharedPreferences.getString("Username", "");
        String password = sharedPreferences.getString("Password", "");
        if (!username.isEmpty() && !password.isEmpty()){
            Intent intent = new Intent(login.this, Drawer.class);
            startActivity(intent);

        }
    }
//    public class Login extends AsyncTask<String, Void, String> {
//        String user, password, name;
//        ProgressDialog pd = new ProgressDialog(login.this);
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pd.setMessage("Loading");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
////                Connection con = sqldatabase.con();
////                if (con !=null)
////                {
////                    PreparedStatement preparedStatement = con.prepareStatement("select users , password,email from users where users =? and password = ? ");
////                    preparedStatement.setString(1,strings[0]);
////                    preparedStatement.setString(2,strings[1]);
////                    ResultSet rs= preparedStatement.executeQuery();
////                    if (rs.isBeforeFirst())
////                    {
////                        while(rs.next())
////                        {
////                            user = rs.getString(1);
////                            password = rs.getString(2);
////                            name = rs.getString(3);
////                        }
////                        return "success";
////                    }
////                    else
////                    {
////                        return "no user found";
////                    }
////                }
////                else
////                {
////                    return "failed to connect";
//                final boolean[] userexist = {false};
//                Map<String, String> loginmap = new HashMap<String, String>();
//                loginmap.put("user", strings[0]);
//                loginmap.put("pass", strings[1]);
//                Request request = new Request(login.this, "/api/login?user=" + strings[0] + "&pass=" + strings[1] + "", com.android.volley.Request.Method.GET) {
//                    @Override
//                    public void onresponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            userexist[0] = obj.getBoolean("userexist");
//                            user = strings[0];
//                            password = strings[1];
//
////                            JSONArray array = new JSONArray(response);
////                            Log.e("login response", response.toString());
////                            ArrayList<String> data = new ArrayList<String>();
////                            JSONArray rearray = new JSONArray();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            e.getMessage();
//                        }
//                    }
//
//                    @Override
//                    public void onError(VolleyError error) {
//
//                    }
//                };
//                return userexist[0] ? "success" : "no user found";
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                return "failed to connect";
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            switch (s) {
//                case "success":
//                    editor = sharedPreferences.edit();
//                    editor.putString("Username", user);
//                    editor.putString("Name", name);
//                    editor.putString("Password", password);
//                    editor.apply();
//                    Intent intent = new Intent(login.this, Drawer.class);
//                    startActivity(intent);
//                    finish();
//                    break;
//                case "no user found":
//                    Toast.makeText(login.this, "no user found", Toast.LENGTH_SHORT).show();
//                    break;
//                case "failed to connect":
//                    Toast.makeText(login.this, "failed to connect", Toast.LENGTH_SHORT).show();
//                    break;
//                default:
//                    Toast.makeText(login.this, "something went wrong", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            pd.dismiss();
//        }
//    }
}