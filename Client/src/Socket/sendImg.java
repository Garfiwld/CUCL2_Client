/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author PC2
 */
public class sendImg {
    public void sendImgToServer(String imgpath){
    try{
        Socket socket;
        socket = new Socket("192.168.1.125", 12501);
        Socket socket2 = new Socket("192.168.1.125", 12502);
        PrintWriter out = new PrintWriter(socket2.getOutputStream());
        out.println(imgpath);
        out.flush();
        
        OutputStream outputStream = socket.getOutputStream();
        BufferedImage image = ImageIO.read(new File(imgpath));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
        System.out.println("Flushed: " + System.currentTimeMillis());
        Thread.sleep(120000);
        socket.close();
    }catch (IOException ex) {
            Logger.getLogger(sendImg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(sendImg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
