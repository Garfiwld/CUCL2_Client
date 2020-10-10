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
import static student.StudentLogin.a;

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
    static int port = 35501;
    static String Subject, Section, StudentID;
    String host = "192.168.1.125";
    //String host = "localhost";
    boolean p;

    public void study() {
        con();
        Reciver.start();

    }
    Thread Reciver = new Thread(new Runnable() {
        BufferedReader bufferRead = null;

        @Override
        public void run() {
            String msg;
            try {
                System.out.print("Start"+port);
                ss = new ServerSocket(25555);
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
                            Connection con = getconnect();
                            JOptionPane.showMessageDialog(a.getContentPane(), "login Successfully");
//                            Subject = read.readLine();
//                            System.out.println(Subject);
                            Section = read.readLine();
                            StudentID = StudentLogin.StudentID;
                            JTextField jpf = new JPasswordField(15);
                            JLabel jl = new JLabel("Please input your new password");
                            Object object[] = {jpf, jl};
                            String status = read.readLine();
                            if (status.equals("yes")) {
                                System.out.println("in");
                                resetPassword.setNewPassword();
                            }else a.setVisible(false);
                            String banSoftewareList = read.readLine();
                            String banWebList = read.readLine();
//                            new BlockProgram();
                            //new BlockWeb();
//                            StudentLogin.a();
                            break;
                        case "failedC":
                            JOptionPane.showMessageDialog(a.getContentPane(), "Login Failed Course Not Exits");
                            break;
                         case "failedCre":
                            JOptionPane.showMessageDialog(a.getContentPane(), "Login Failed Credential Invalid");
                            break;
                        default:
                            port = Integer.parseInt(msg);
                            recon();
                            System.out.println("recon");
                            break;
                    }
                }
            } catch (IOException e) {
            }
        }

        private Connection getconnect() {
            Connection con;
            String url = "jdbc:mysql://localhost/controllab";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, "root", "");
                return con;
            } catch (ClassNotFoundException | SQLException ex) {
                return null;
            }
        }
    });

    Thread Boardcast = new Thread(new Runnable() {

        FileOutputStream fos;
        DataOutputStream dos;
        Process process = null;
        BufferedReader bufferRead = null;
        String path, myip;

        InetAddress ip;

        @Override
        public void run() {
            try {
                byte[] b = new byte[256];
                DatagramSocket s = new DatagramSocket(8888);
                DatagramPacket p = new DatagramPacket(b, b.length);
                while (true) {
                    s.receive(p);
                    String m = new String(b, 0, b.length);
                    m = m.substring(0, m.indexOf('#'));
                    System.out.println(m);

//                    switch (m) {
//                        case "blockinternet":
//                            File file;
//                            path = "C:\\Controllab\\set.bat";
//                            file = new File(path);
//                            FileWriter writer;
//                            try {
//                                ip = InetAddress.getLocalHost();
//                                myip = ip.getHostAddress();
//                                writer = new FileWriter(file, false);
//                                writer.write("@echo off\r\n");
//                                writer.write("netsh interface ip set address \"Ethernet\" static " + myip + " 255.255.255.0 192.168.14.0 1\r\n");
//                                writer.write("netsh interface ip add dns \"Ethernet\" 192.168.14.1\r\n");
//                                writer.write("netsh interface ip add dns \"Ethernet\" 192.168.14.1 index=1\r\n");
//                                writer.write("netsh interface ip add dns \"Ethernet\" 202.44.32.29 index=2\r\n");
//                                writer.close();
//
//                                System.out.println("Write success!");
//
//                            } catch (IOException e) {
//                            }
//                            break;
//                        case "unblockinternet":
//                            File file2;
//                            path = "C:\\Controllab\\set.bat";
//                            file2 = new File(path);
//                            FileWriter writer2;
//                            try {
//                                ip = InetAddress.getLocalHost();
//                                myip = ip.getHostAddress();
//                                writer2 = new FileWriter(file2, false);
//                                writer2.write("@echo off\r\n");
//                                writer2.write("netsh interface ip set address \"Ethernet\" static " + myip + " 255.255.255.0 192.168.14.1 1\r\n");
//                                writer2.write("netsh interface ip add dns \"Ethernet\" 192.168.14.1\r\n");
//                                writer2.write("netsh interface ip add dns \"Ethernet\" 192.168.14.1 index=1\r\n");
//                                writer2.write("netsh interface ip add dns \"Ethernet\" 202.44.32.29 index=2\r\n");
//                                writer2.close();
//                                System.out.println("Write success!");
//
//                            } catch (IOException e) {
//                            }
//                            break;
//                    }
//                    Thread.sleep(2000);
//
//                    try {
//                        Runtime.getRuntime().exec("cmd /c C:\\Controllab\\set.lnk");
//                    } catch (IOException e) {
//                        System.out.println(e);
//                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    });
    
    Thread Sender = new Thread(new Runnable() {
        Timer myTimer = new Timer();

        @Override
        public void run() {
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        System.out.println("testPrint");
                        s = new Socket("192.168.1.125", 35501);
                        PrintWriter out = new PrintWriter(s.getOutputStream());
                        InetAddress inetAddress = InetAddress.getLocalHost();
                        InetAddress address = InetAddress.getByName(inetAddress.getHostAddress());
                        NetworkInterface ni =  NetworkInterface.getByInetAddress(address);
                        byte[] mac = ni.getHardwareAddress();
                        StringBuilder sb = new StringBuilder();
                        
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }
                        System.out.println(inetAddress.getHostAddress());
                        System.out.println(sb.toString());
                        out.println(inetAddress.getHostAddress());
                        out.println(sb.toString());
                        out.flush();
                        s.close();
                    } catch (IOException ex) {
                    }
                }
            }, 0, 8000);
        }
    });

    public void con() {
        try {
            s = new Socket(host, port);
            s.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server Down", "Inane error", JOptionPane.ERROR_MESSAGE);
            p = false;
            try {
                System.out.println("Server Down");
//                Runtime.getRuntime().exec("cmd /c C:\\Controllab\\shutdown.bat");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void recon() {
        Sender.start();
        Boardcast.start();
    }
}
