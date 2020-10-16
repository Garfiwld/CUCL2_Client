/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import Socket.ReciveStudentLogin;
import Socket.ReciveMsg;
import Model.StudentModel;
import connect.SocketConnect;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class StudentLogin extends javax.swing.JFrame {

    static StudentModel studentModel = new StudentModel();
    SocketConnect socketConnect = new SocketConnect();
    public static StudentLogin studentLogin;
    
    public StudentLogin() {
        
        //setUndecorated(true);
        setExtendedState(this.MAXIMIZED_BOTH);
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        initComponents();

        Dimension position = Toolkit.getDefaultToolkit().getScreenSize();
        jTextField1.setBounds(position.width / 2 - (jTextField1.getPreferredSize().width / 2), position.height / 2, jTextField1.getPreferredSize().width, jTextField1.getPreferredSize().height);
        jPasswordField1.setBounds(position.width / 2 - (jTextField1.getPreferredSize().width / 2), position.height / 2 + jTextField1.getPreferredSize().height + 10, jTextField1.getPreferredSize().width, jTextField1.getPreferredSize().height);
        jButton1.setBounds(position.width / 2 - (jButton1.getPreferredSize().width / 2), position.height / 2 + 100, jButton1.getPreferredSize().width, jButton1.getPreferredSize().height);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        getContentPane().setLayout(null);

        jTextField1.setColumns(15);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(10, 70, 126, 20);

        jPasswordField1.setColumns(15);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(10, 100, 126, 20);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(80, 130, 57, 23);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 30, 0, 0);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {
            System.out.println("Login Press");
            Socket socketLogin = new SocketConnect().socketLogin();
            PrintWriter out = new PrintWriter(socketLogin.getOutputStream());
            
            out.println("login");
            out.println(studentModel.getIpv4());
            String StudentID = jTextField1.getText();
            studentModel.setStudentid(StudentID);
            out.println(StudentID);
            String sPassword = String.valueOf(jPasswordField1.getPassword());
            out.println(sPassword);
            out.println(studentModel.getMacaddress());
            
            out.flush();
            out.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ReciveStudentLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
//    public static void a() {
//        a.setVisible(false);
//    }

    public static void main(String args[]) throws IOException, InterruptedException {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /*
        *   MatchMac
        */
        InetAddress localhost = InetAddress.getLocalHost();
        InetAddress hostaddress = InetAddress.getByName(localhost.getHostAddress());
        NetworkInterface ni = NetworkInterface.getByInetAddress(hostaddress);
        byte[] mac = ni.getHardwareAddress();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        studentModel.setIpv4(localhost.getHostAddress());
        System.out.println("StudentLogin#inetAddress.getHostAddress() : "+localhost.getHostAddress());
        studentModel.setMacaddress(sb.toString());
        System.out.println("StudentLogin#sb.toString() : "+sb.toString());
        
        Socket socketMatchMac = new SocketConnect().socketMatchMac();
        PrintWriter out = new PrintWriter(socketMatchMac.getOutputStream());
        out.println(localhost.getHostAddress());
        out.println(sb.toString());
        out.flush();
        socketMatchMac.close();
        
        /*
        *
        */
        ReciveStudentLogin student = new ReciveStudentLogin();
        student.study();
        ReciveMsg rm = new ReciveMsg();
        rm.recive();
        
        studentLogin = new StudentLogin();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                studentLogin.setVisible(true);
                studentLogin.getContentPane().setBackground(Color.DARK_GRAY);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    public static javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
