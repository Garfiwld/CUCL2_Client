/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import Model.StudentModel;
import connect.SocketConnect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.Base64;

public class SendImgClient {

    SocketConnect socketConnect = new SocketConnect();
    StudentModel studentModel = new StudentModel();
    
    public String encodeImage(byte[] imageByteArray) {
        return Base64.getEncoder().encodeToString(imageByteArray);
    }

    public void sendImgToServer(String pathImg, String softwareName) {

        //the file to convert is in the same folder as the source code
        File file = new File(pathImg);

        try {
            //Image conversion to byte array
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            //Image conversion byte array in Base64 String
            String imageDataString = encodeImage(imageData);
            imageInFile.close();
            System.out.println("Image Successfully Manipulated!");

            //the object that will be send to Server
            JSONObject obj = new JSONObject();

            //name of the image
            obj.put("filename", file.getName());
            //string obteined by the conversion of the image
            obj.put("image", imageDataString);
            System.out.println("SendImgClient#studentModel.getStudentid() : "+studentModel.getStudentid());
            obj.put("studentid", studentModel.getStudentid());
            System.out.println("SendImgClient#studentModel.getCourse() : "+studentModel.getCourse());
            obj.put("course", studentModel.getCourse());
            obj.put("softwarename", softwareName);
            obj.put("datetime", studentModel.getDatetime());

            //connection to erver
//            Socket clientSocket= new Socket ("localhost", 7777);
            Socket clientSocket = socketConnect.socketImg();
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            //send data
            outToServer.writeBytes(obj.toJSONString());
            System.out.println("File Sent!");

            //closing connection
            clientSocket.close();

        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }

    }

}
