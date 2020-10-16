/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author PC2
 */
public class ServerSocketCreate {
    
    private final int portStudent = 26101;
    private final int portReciveMsg = 26103;
    
    public ServerSocket serversocketStudent() {
        ServerSocket sock = null;
        try {
            sock = new ServerSocket(portStudent);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return sock;
    }
    public ServerSocket serversocketReciveMsg() {
        ServerSocket sock = null;
        try {
            sock = new ServerSocket(portReciveMsg);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return sock;
    }
    
}
