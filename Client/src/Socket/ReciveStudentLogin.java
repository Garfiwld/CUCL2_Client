/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import Model.StudentModel;
import connect.SocketConnect;
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
import student.BlockProgram;
import student.BlockWeb;
import student.StudentLogin;

/**
 *
 * @author admin
 */
public class ReciveStudentLogin {

    SocketConnect socketConnect = new SocketConnect();
    StudentLogin studentLogin = new StudentLogin();
    StudentModel studentModel = new StudentModel();

    public void study() {
        Reciver.start();
    }
    Thread Reciver = new Thread(new Runnable() {
        BufferedReader bufferRead = null;

        @Override
        public void run() {

            try {
//                ServerSocket serversocketStudent = socketConnect.serversocketStudent();
                ServerSocket serversocketStudent = new ServerSocket(26101);
                while (true) {
                    Socket socketAccept = serversocketStudent.accept();
                    BufferedReader read = new BufferedReader(new InputStreamReader(socketAccept.getInputStream()));
                    String msg = read.readLine();
                    System.out.println("Student msg : " + msg);
                    switch (msg) {
                        case "LoginSuccess":
                            JOptionPane.showMessageDialog(studentLogin.getContentPane(), "login Successfully");
                            String section = read.readLine();
                            studentModel.setSection(section);
                            String course = read.readLine();
                            studentModel.setCourse(course);
                            String status = read.readLine();
                            System.out.println("Student#status : " + status);
                            if (status.equals("Yes")) {
                                JTextField jpf = new JPasswordField(15);
                                JLabel jl = new JLabel("Please input your new password");
                                Object object[] = {jpf, jl};
                                int i = JOptionPane.showConfirmDialog(studentLogin.getContentPane(), object, "First Login", JOptionPane.WARNING_MESSAGE);
                                if (i == JOptionPane.OK_OPTION) {
                                    String result = jpf.getText();
                                    try {
                                        System.out.println("SocketNewpassword");
                                        Socket socketUpdatePassword = socketConnect.socketUpdatePassword();
                                        PrintWriter out = new PrintWriter(socketUpdatePassword.getOutputStream());
                                        out.println(studentModel.getStudentid());
                                        out.println(result);
                                        out.flush();
                                        out.close();
                                        StudentLogin.studentLogin.setVisible(false);
                                    } catch (IOException e) {
                                        System.out.print(e);
                                    }
                                }
                            } else {
                                StudentLogin.studentLogin.setVisible(false);
                            }

                            String banSoftewareList = read.readLine();
                            System.out.println("banSoftewareList " + banSoftewareList);
                            String banWebList = read.readLine();
                            if (!banSoftewareList.equals("")) {
                                new BlockProgram(banSoftewareList);
                            }
                            if (!banWebList.equals("")) {
                                new BlockWeb(banWebList);
                            }

                            break;
                        case "StudentNotInCourse":
                            JOptionPane.showMessageDialog(studentLogin.getContentPane(), "You not register in class.");
                            break;
                        case "LoginFailed":
                            JOptionPane.showMessageDialog(studentLogin.getContentPane(), "Username oe Password Invalid.");
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    });
}
