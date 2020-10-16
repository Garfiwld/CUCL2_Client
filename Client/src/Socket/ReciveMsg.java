/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import connect.SocketConnect;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author PC2
 */
public class ReciveMsg {

    SocketConnect socketConnect = new SocketConnect();

    public void recive() {

        ReciveMsg.start();

    }
    Thread ReciveMsg = new Thread(new Runnable() {
        
        @Override
        public void run() {
            try {
//                ServerSocket serversocketStudent = socketConnect.serversocketReciveMsg();
                ServerSocket serversocketStudent = new ServerSocket(26103);
                while (true) {
                    Socket readAccept = serversocketStudent.accept();
                    BufferedReader read = new BufferedReader(new InputStreamReader(readAccept.getInputStream()));
                    String msg = read.readLine();
                    System.out.println("ReciveMsg msg : " + msg);
                    switch (msg) {
                        case "Shutdown":
                            System.out.println("case : Shutdown");
                            try {
//                                Runtime.getRuntime().exec("cmd /c C:\\Controllab\\shutdown.bat");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "Restart":
                            System.out.println("case : Restart");
                            try {
//                                Runtime.getRuntime().exec("cmd /c C:\\Controllab\\restart.bat");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "LockScreen":
                            System.out.println("case : LockScreen");
                            try {
//                                Runtime.getRuntime().exec("cmd /c C:\\Controllab\\restart.bat");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    });
}
