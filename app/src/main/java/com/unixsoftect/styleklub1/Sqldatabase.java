package com.unixsoftect.styleklub1;

import android.database.SQLException;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;

public class Sqldatabase {
    public Connection con(){
        String username="sa";
        String password="Sakib@123";
        String Database="TheStyleKlub_Dummy";
//      String server_ip="192.168.0.100:1433";
        String server_ip="103.209.65.222:1433";
//      String server_ip="192.168.0.110:1433";
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn=null;
        String ConnURL;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
//            ConnURL = "jdbc:jtds:sqlserver://"+server_ip+";" +
//                    "instanceName=SQLEXPRESS;databaseName="+Database+";" +
//                    "integratedSecurity=true;";
            ConnURL = "jdbc:jtds:sqlserver://"+server_ip+";" +
                    "instanceName=SQLEXPRESS;databaseName="+Database+";" +
                    "integratedSecurity=true;user="+username+";" +
                    "password="+password+";";
            conn = DriverManager.getConnection(ConnURL);

//        Log.d("connection_stablish","successfull");
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}
