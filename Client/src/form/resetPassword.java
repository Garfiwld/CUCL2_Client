/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import Model.StudentModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import student.Student;
import student.StudentLogin;
import static student.StudentLogin.a;


/**
 *
 * @author PC2
 */
public class resetPassword {
    static int port = 25103;
    public static void setNewPassword(){
        System.out.println("setpassword");
        JTextField jpf = new JPasswordField(15);
        JLabel jl = new JLabel("Please input your new password");
        Object object[] = {jpf, jl};
       
        int i = JOptionPane.showConfirmDialog(StudentLogin.a.getContentPane(), object, "First Login", JOptionPane.WARNING_MESSAGE);
        if (i == JOptionPane.OK_OPTION) {
           String result = jpf.getText();
           try{
           System.out.println("SocketNewpassword");
           Socket s = new Socket(StudentLogin.host,port);
           PrintWriter out = new PrintWriter(s.getOutputStream());
           out.println(StudentLogin.myStudent.getUsername());
           out.println(result);
           out.flush();
           out.close();
           a.setVisible(false);
            }catch (IOException e){
                
                
            }
        }
    }
}
