/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import Socket.SendImgClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 *
 * @author admin
 */
public class BlockSoftware {

    String exec_command = "tasklist.exe /FO LIST";
    ArrayList<String> solftwareList;

    public BlockSoftware(String bansoftwareList) {
        
        Gson gson = new Gson();
        
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arrayList = gson.fromJson(bansoftwareList, listType);
        this.solftwareList = arrayList;
        Timer timer = new Timer();
        timer.schedule(blockprogram, 0, 60 * 1000);
    }

    TimerTask blockprogram = new TimerTask() {

        CaptureImage captureImage = new CaptureImage();
        SendImgClient scalaberniClient = new SendImgClient();

        @Override
        public void run() {
            ArrayList<String> Data = GetProcessListData();
            ArrayList<String> Ban = solftwareList;
            for (int i = 0; i < Ban.size(); i++) {
                for (int j = 0; j < Data.size(); j++) {
                    if (Data.get(j).toUpperCase().matches(Ban.get(i).toUpperCase() + ".*")) {
                        try {
                            System.out.println("blockprogram : " + Ban.get(i));
                            String capImg = captureImage.CapImg();
                            System.out.println(capImg);
                            scalaberniClient.sendImgToServer(capImg, Ban.get(i));
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

    private ArrayList<String> GetProcessListData() {
        ArrayList<String> data = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec(exec_command);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
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

    public void shutdown() {
        try {
            Runtime.getRuntime().exec("cmd /c C:\\Controllab\\shutdown.bat");
        } catch (IOException e) {
        }
    }

}
