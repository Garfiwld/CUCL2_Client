/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.json.simple.JSONObject;
import student.StudentLogin;

/**
 *
 * @author PC2
 */
public class sendImg {

    static int port = 25104;

    public static String encodeImage(byte[] imageByteArray) {
        return Base64.getEncoder().encodeToString(imageByteArray);
    }

    public static void sendImgToServer(String imgpath, String Des) {
        try {
            File file = new File(imgpath);
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            //Image conversion byte array in Base64 String
            String imageDataString = encodeImage(imageData);
            imageInFile.close();
            System.out.println("Image Successfully Manipulated!");

            //the object that will be send to Server
            JSONObject obj = new JSONObject();
            obj.put("filename", file.getName());
            //string obteined by the conversion of the image
            obj.put("image", imageDataString);
            obj.put("Username", StudentLogin.myStudent.getUsername());
            obj.put("Course", StudentLogin.myStudent.getCourse());
            obj.put("Des", Des);
            //connection to erver
            System.out.println("Connect");
            Socket socket = new Socket(StudentLogin.host, port);
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());

            //send data
            outToServer.writeBytes(obj.toString());
            System.out.println("File Sent!");

            //closing connection
            socket.close();

//        PrintWriter out = new PrintWriter(socket2.getOutputStream());
//        out.println(Des);
//        out.println(StudentLogin.myStudent.getUsername());
//        out.println(StudentLogin.myStudent.getCourse());
//        out.println(imgpath);
//        out.flush();
//        
//        OutputStream outputStream = socket.getOutputStream();
//        BufferedImage image = ImageIO.read(new File(imgpath));
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(image, "jpg", byteArrayOutputStream);
//        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
//        outputStream.write(size);
//        outputStream.write(byteArrayOutputStream.toByteArray());
//        outputStream.flush();
//        System.out.println("Flushed: " + System.currentTimeMillis());
//        Thread.sleep(120000);
//        socket.close();
        } catch (IOException ex) {
            Logger.getLogger(sendImg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
