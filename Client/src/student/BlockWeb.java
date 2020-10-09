/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author pc
 */
public class BlockWeb {

    Timer timer;
    String host = "192.168.1.125";
    //String host = "localhost";

    public BlockWeb() {
        timer = new Timer();
        timer.schedule(blockweb, 0, 60 * 10 * 1000);
    }

    private ArrayList<String> GetBanList() {
        ArrayList<String> BanWebList = new ArrayList<>();
        Connection connection = getconnect();
        String sql = "SELECT * FROM `banweb` WHERE `Co_Title` = '" + Student.Subject + "' AND `Co_Section` = '" + Student.Section + "'";
        PreparedStatement pre;
        ResultSet rs;
        try {
            pre = connection.prepareStatement(sql);
            rs = pre.executeQuery();
            String ban;
            while (rs.next()) {
                ban = rs.getString("Name");
                BanWebList.add(ban);
            }
        } catch (SQLException ex) {
        }
        return BanWebList;
    }

    public Connection getconnect() {
        Connection con;
        String url = "jdbc:mysql://"+host+"/controllab";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
    }

    TimerTask blockweb = new TimerTask() {
        @Override
        public void run() {
            ArrayList<String> BanWebList = GetBanList();
            File file = new File("C:\\Controllab\\hosts");
            FileWriter writer;
            try {
                writer = new FileWriter(file, false);
                for (int i = 0; i < BanWebList.size(); i++) {
                    writer.write(" 	0.0.0.0         http://www." + BanWebList.get(i) + " \r\n");
                    writer.write(" 	0.0.0.0 	http://" + BanWebList.get(i) + " \r\n");
                    writer.write(" 	0.0.0.0 	www." + BanWebList.get(i) + " \r\n");
                    writer.write(" 	0.0.0.0 	" + BanWebList.get(i) + " \r\n");
                    System.out.println("Write " + BanWebList.get(i) + "success!");
                }
                writer.close();
            } catch (IOException e) {
            }
            try {

                Runtime.getRuntime().exec("cmd /c C:\\Controllab\\a.lnk");
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    };
}
