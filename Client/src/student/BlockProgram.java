/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import Socket.sendImg;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.Date;

/**
 *
 * @author admin
 */
public class BlockProgram {

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh mm ss a");
    String Viewer;
    String exec_command = "tasklist.exe /FO LIST";
    Timer timer;
    String host = "192.168.1.125";
    ArrayList<String> solftwareList;
    //String host = "localhost";

    public BlockProgram(String bansoftwareList) {
        String str[] = bansoftwareList.split(",");
	ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(str));
        this.solftwareList = arrayList;
        timer = new Timer();
        timer.schedule(blockprogram, 0, 3* 60 * 1000);
    }

    private ArrayList<String> GetProcessListData() {
        ArrayList<String> data = new ArrayList<>();

        try {
            Process p = Runtime.getRuntime().exec(exec_command);
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = in.readLine();
            while (str != null) {
                if (str.startsWith("Image Name:")) {
                    String appName = str.substring(11).trim();
                    data.add(appName);
                }
                str = in.readLine();
            }
        } catch (IOException e) {
        }
        return data;
    }


    TimerTask blockprogram = new TimerTask() {
        @Override
        public void run() {
            ArrayList<String> Data = GetProcessListData();
            ArrayList<String> Ban = solftwareList;
            for (int i = 0; i < Ban.size(); i++) {
                for (int j = 0; j < Data.size(); j++) {

                    if (Data.get(j).toUpperCase().matches(Ban.get(i).toUpperCase() + ".*")) {
                        try {
                            System.out.println(Ban.get(i));
                            String temp = captureImage.CapImg();
                            System.out.println(temp);
                            sendImg.sendImgToServer(temp,Ban.get(i));
                            Thread.sleep(5000);
//                            shutdown();
                            break;
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            }
        }

    };
    public void shutdown() {
        try {
            Runtime.getRuntime().exec("cmd /c C:\\Controllab\\shutdown.bat");
        } catch (IOException e) {
        }
    }

}
