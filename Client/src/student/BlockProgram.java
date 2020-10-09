/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.Date;

/**
 *
 * @author admin
 */
public class BlockProgram {

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh mm ss a");
    String Viewer;
    String exec_command = "tasklist.exe /FO LIST";
    Timer timer;
    String host = "192.168.1.125";
    //String host = "localhost";

    public BlockProgram() {
        timer = new Timer();
        timer.schedule(blockprogram, 0, 5 * 1000);
    }

    private ArrayList<String> GetProcessListData() {
        ArrayList<String> data = new ArrayList<>();

        try {
            Process p = Runtime.getRuntime().exec(exec_command);
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = in.readLine();
            while (str != null) {
                if (str.startsWith("Image Name:")) {
                    String appName = str.substring(11).trim();
                    data.add(appName);
                }
                str = in.readLine();
            }
        } catch (IOException e) {
        }
        return data;
    }

    private ArrayList<String> GetBanList() {
        ArrayList<String> BanProgranmList = new ArrayList<>();
        Connection connection = getconnect();
        String sql = "SELECT * FROM `banprogram` WHERE `Co_Title` = '" + Student.Subject + "' AND `Co_Section` = '" + Student.Section + "'";
        PreparedStatement pre;
        ResultSet rs;
        try {
            pre = connection.prepareStatement(sql);
            rs = pre.executeQuery();
            String ban = "";
            while (rs.next()) {
                ban = rs.getString("Name");
                BanProgranmList.add(ban);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return BanProgranmList;
    }

    public Connection getconnect() {
        Connection con;
        String url = "jdbc:mysql://" + host + "/controllab";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
    }

    public void cap(String program) {
        try {
            Socket s1;
            Socket s;
            byte[] b;
            File f;
            int c;
            InputStream in;
            OutputStream out;
            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = dateFormat1.format(date);
            String time2 = dateFormat2.format(date);
            String name = Student.StudentID + "_" + time + ".jpg";
            String nm_file = "C:\\Controllab\\picture\\" + name;
            senddata(time2, program, name);

            ImageIO.write(screenShot, "JPG", new File(nm_file));
            
            BufferedImage image = ImageIO.read(new File(nm_file));
            Graphics g = image.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(90, 50, 310, 70);
            g.setColor(Color.red);
            g.setFont(g.getFont().deriveFont(40f));
            g.drawString(Student.StudentID, 100, 100);
            g.dispose();

            DataOutputStream out2 = new DataOutputStream(new FileOutputStream("C:\\Controllab\\picwithuser\\a.jpg", false));
            ImageIO.write((RenderedImage) image, "jpg", out2);
            image.flush();
            out2.close();

            try {
                s1 = new Socket(host, 12501);
                PrintWriter o2 = new PrintWriter(s1.getOutputStream());
                o2.println(name);
                o2.flush();
                s1.close();

                s = new Socket(host, 12500);
                f = new File("C:\\Controllab\\picwithuser\\a.jpg");
                b = new byte[16 * 1024];
                in = new FileInputStream(f);
                out = s.getOutputStream();
                while ((c = in.read(b)) > 0) {
                    out.write(b, 0, c);
                }
                out.close();
                in.close();
                s.close();
                f.delete();
            } catch (IOException ex) {
            }

        } catch (AWTException | HeadlessException | IOException ex) {
        }

    }

    TimerTask blockprogram = new TimerTask() {
        @Override
        public void run() {
            ArrayList<String> Data = GetProcessListData();
            ArrayList<String> Ban = GetBanList();
            for (int i = 0; i < Ban.size(); i++) {
                for (int j = 0; j < Data.size(); j++) {
                    if (Data.get(j).toUpperCase().matches(Ban.get(i).toUpperCase() + ".*")) {
                        try {
                            System.out.println(Ban.get(i));
                            Thread.sleep(3000);
                            cap(Ban.get(i));
                            Thread.sleep(500);
                            shutdown();
                            break;
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            }
        }

    };

    public void senddata(String time, String program, String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO `offender`(`St_Username`, `Time`, `Discription`, `Path_Image`, `Co_Title`, `Co_Section`) VALUES (?,?,?,?,?,?)";
        try {
            conn = getconnect();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Student.StudentID);
            stmt.setString(2, time);
            stmt.setString(3, "Open program " + program);
            stmt.setString(4, "C:\\Controllab\\student\\" + name);
            stmt.setString(5, Student.Subject);
            stmt.setString(6, Student.Section);
            stmt.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public void shutdown() {
        try {
            Runtime.getRuntime().exec("cmd /c C:\\Controllab\\shutdown.bat");
        } catch (IOException e) {
        }
    }

}
