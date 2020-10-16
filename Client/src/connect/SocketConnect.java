/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author PC2
 */
public class SocketConnect {
    
    private final String host = "192.168.1.101";
    private final int portMatchMac = 25101;
    private final int portLogin = 25102;
    private final int portResetPassword = 25103;
    private final int portImg = 25104;

    public Socket socketMatchMac() {
        Socket sock = null;
        try {
            sock = new Socket(host, portMatchMac);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return sock;
    }
    public Socket socketLogin() {
        Socket sock = null;
        try {
            sock = new Socket(host, portLogin);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return sock;
    }
    public Socket socketUpdatePassword() {
        Socket sock = null;
        try {
            sock = new Socket(host, portResetPassword);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return sock;
    }
    public Socket socketImg() {
        Socket sock = null;
        try {
            sock = new Socket(host, portImg);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return sock;
    }
    
}
