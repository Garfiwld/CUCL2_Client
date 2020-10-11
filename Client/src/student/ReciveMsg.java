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
import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static student.StudentLogin.a;

/**
 *
 * @author PC2
 */
public class ReciveMsg {
     public ServerSocket ss;
    public Socket r, s;
    public BufferedReader read;
    PrintWriter out;
    public String msg;
    static int port;
    String host = "192.168.1.125";
    ReciveMsg(int port){
        this.port = port;
    }
    
    public void recive() {
        
        ReciveMsg.start();

    }
    Thread ReciveMsg = new Thread(new Runnable() {
        BufferedReader bufferRead = null;

        @Override
        public void run() {
            String msg;
            try {
                System.out.print("Start"+port);
                ss = new ServerSocket(port);
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
                    }
                }
            } catch (IOException e) {
                
            }
        }
    });
}

