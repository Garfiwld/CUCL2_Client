/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import com.google.gson.Gson;

/**
 *
 * @author pc
 */
public class BlockWeb {

    ArrayList<String> WebList = null;

    public BlockWeb(String banWebList) {

        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arrayList = gson.fromJson(banWebList, listType);
        this.WebList = arrayList;
        Timer timer = new Timer();
//        timer.schedule(blockweb, 0, 60 * 1000);
//    }
//
//    TimerTask blockweb = new TimerTask() {
//        @Override
//        public void run() {
        ArrayList<String> BanWebList = WebList;
        if (BanWebList != null) {
            File file = new File("C:\\Controllab\\hosts");
            FileWriter writer;
            try {
                writer = new FileWriter(file, false);
                for (int i = 0; i < BanWebList.size(); i++) {
                    writer.write(" 	0.0.0.0         http://www." + BanWebList.get(i) + " \r\n");
                    writer.write(" 	0.0.0.0 	http://" + BanWebList.get(i) + " \r\n");
                    writer.write(" 	0.0.0.0 	www." + BanWebList.get(i) + " \r\n");
                    writer.write(" 	0.0.0.0 	" + BanWebList.get(i) + " \r\n");
                    System.out.println("Write " + BanWebList.get(i) + " success!");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            try {

                Runtime.getRuntime().exec("cmd /c C:\\Controllab\\a.lnk");
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
//};
}
