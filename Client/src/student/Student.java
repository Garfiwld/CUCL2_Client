/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import form.resetPassword;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static student.StudentLogin.StudentID;
import static student.StudentLogin.a;
import static student.StudentLogin.myStudent;

/**
 *
 * @author admin
 */
public class Student {

    public ServerSocket ss;
    public Socket r, s;
    public BufferedReader read;
    PrintWriter out;
    public String msg;
    int port;
    int sender;
    static String Subject, Section, StudentID;
    String host = "192.168.1.125";
    //String host = "localhost";
    boolean p;

    Student(int port){
        this.port = port;
       
    }
    public void study() {
       
        Reciver.start();

    }
    Thread Reciver = new Thread(new Runnable() {
        BufferedReader bufferRead = null;

        @Override
        public void run() {
            String msg;
            try {

                ss = new ServerSocket(26101);
                while (true) {
                    r = ss.accept();
                    read = new BufferedReader(new InputStreamReader(r.getInputStream()));
                    msg = read.readLine();
                    System.out.println(msg);
                    switch (msg) {
                        case "Shutdown":
                            try {
                                System.out.println("Shutdown");
//                                Runtime.getRuntime().exec("cmd /c C:\\Controllab\\shutdown.bat");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "Restart":
                            try {
                                System.out.println("Restart");
//                                Runtime.getRuntime().exec("cmd /c C:\\Controllab\\restart.bat");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "success":
                            System.out.println("inSuccess");
                            JOptionPane.showMessageDialog(a.getContentPane(), "login Successfully");
//                            Subject = read.readLine();
//                            System.out.println(Subject);
                            Section = read.readLine();
                            StudentID = StudentLogin.StudentID;
                            JTextField jpf = new JPasswordField(15);
                            JLabel jl = new JLabel("Please input your new password");
                            Object object[] = {jpf, jl};
                            String status = read.readLine();
                            String course = read.readLine();
                            StudentLogin.myStudent.setCourse(course);
                            if (status.equals("yes")) {
                                System.out.println("in");
                                resetPassword.setNewPassword();
                            }else a.setVisible(false);
                            String banSoftewareList = read.readLine();
                            String banWebList = read.readLine();
                            new BlockProgram(banSoftewareList);
                            new BlockWeb(banWebList);
//                            StudentLogin.a();
                            break;
                        case "failedC":
                            JOptionPane.showMessageDialog(a.getContentPane(), "Login Failed Course Not Exits");
                            break;
                         case "failedCre":
                            JOptionPane.showMessageDialog(a.getContentPane(), "Login Failed Credential Invalid");
                            break;
                    }
                }
            } catch (IOException e) {
            }
        }
    });
}
