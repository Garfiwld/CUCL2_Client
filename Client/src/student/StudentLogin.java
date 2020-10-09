/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import Model.StudentModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static student.Student.port;

/**
 *
 * @author admin
 */
public class StudentLogin extends javax.swing.JFrame {

    /**
     * Creates new form StudentLogin
     */
    public static StudentModel myStudent = new StudentModel();
    public static StudentLogin a;
    static Student b;
    static boolean c = false;
    static String StudentID;
    String host = "192.168.1.125";
    //String host = "localhost";
    public StudentLogin() {
        System.out.println("loginna");
        //setUndecorated(true);
        setExtendedState(this.MAXIMIZED_BOTH);
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        initComponents();
        

        Dimension position = Toolkit.getDefaultToolkit().getScreenSize();
        jTextField1.setBounds(position.width / 2 - (jTextField1.getPreferredSize().width / 2), position.height / 2, jTextField1.getPreferredSize().width, jTextField1.getPreferredSize().height);
        jPasswordField1.setBounds(position.width / 2 - (jTextField1.getPreferredSize().width / 2), position.height / 2 + jTextField1.getPreferredSize().height + 10, jTextField1.getPreferredSize().width, jTextField1.getPreferredSize().height);
        jButton1.setBounds(position.width / 2 - (jButton1.getPreferredSize().width / 2), position.height / 2 + 100, jButton1.getPreferredSize().width, jButton1.getPreferredSize().height);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
            System.out.println("Loginpress");
            Socket s = new Socket(host, 37000);
            PrintWriter out1 = new PrintWriter(s.getOutputStream());
            out1.println("login");
            out1.flush();
            out1.println(jTextField1.getText());
            StudentID = jTextField1.getText();
            myStudent.setUsername(StudentID);
            out1.flush();
            out1.println(String.valueOf(jPasswordField1.getPassword()));
            out1.println(myStudent.getMacaddress());
            out1.flush();
            out1.close();
        } catch (IOException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public static void a() {
        a.setVisible(false);
    }

    /**
     * @param args the command line arguments
     */
   

    public static void Sendimg() throws IOException, InterruptedException {
        Socket socket = new Socket("192.168.1.125", 12501);
        OutputStream outputStream = socket.getOutputStream();

        BufferedImage image = ImageIO.read(new File("C:\\Users\\PC2\\Desktop\\classroom.png"));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
        System.out.println("Flushed: " + System.currentTimeMillis());

        Thread.sleep(120000);
        System.out.println("Closing: " + System.currentTimeMillis());
        socket.close();

    }
    public static void main(String args[]) throws IOException, InterruptedException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        InetAddress inetAddress = InetAddress.getLocalHost();
        InetAddress address = InetAddress.getByName(inetAddress.getHostAddress());
     
        myStudent.setIp(inetAddress.getHostAddress());
        
        NetworkInterface ni =  NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
        myStudent.setMacaddress(sb.toString());
        Socket s = new Socket("192.168.1.125", 35501);
        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println(inetAddress.getHostAddress());
        out.println(sb.toString());
        out.flush();
        s.close();
        
        //</editor-fold>
        /* Create and display the form */
        b = new Student();
        b.study();
        a = new StudentLogin();
        
//        b.Sender.start();
        
//        System.out.println("Sendimg");
//        a.Sendimg();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                a.setVisible(true);
                a.getContentPane().setBackground(Color.DARK_GRAY);
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
