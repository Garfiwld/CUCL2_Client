/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import form.resetPassword;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
    int port;
    int sender;
    static String Subject, Section, StudentID;
    String host = "192.168.1.103";
    //String host = "localhost";
    boolean p;

    Student(int port) {
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
                    System.out.println("Student msg : " + msg);
                    switch (msg) {
                        case "LoginSuccess":
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

                            if (status.equals("Yes")) {
                                resetPassword.setNewPassword();
                            } else {
                                a.setVisible(false);
                            }

                            String banSoftewareList = read.readLine();
                            String banWebList = read.readLine();
                            if (!banSoftewareList.equals("")) {
                                new BlockProgram(banSoftewareList);
                            }
                            if (!banWebList.equals("")) {
                                new BlockWeb(banWebList);
                            }

                            break;
                        case "StudentNotInCourse":
                            JOptionPane.showMessageDialog(a.getContentPane(), "You not register in class.");
                            break;
                        case "LoginFailed":
                            JOptionPane.showMessageDialog(a.getContentPane(), "Username oe Password Invalid.");
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    });
}
