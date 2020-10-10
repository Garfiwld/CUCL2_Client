/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author PC2
 */
public class captureImage {
    public static String CapImg(){
        try{
        byte[] b;
            File f;
            int c;
            InputStream in;
            OutputStream out;
            String StudentID = StudentLogin.myStudent.getUsername();
            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = dateFormat1.format(date);
            String time2 = dateFormat2.format(date);
            String name = StudentID + "_" + time + ".jpg";
            String nm_file = "C:\\Controllab\\picture\\" + name;
            ImageIO.write(screenShot, "JPG", new File(nm_file));
            BufferedImage image = ImageIO.read(new File(nm_file));
            Graphics g = image.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(90, 50, 310, 70);
            g.setColor(Color.red);
            g.setFont(g.getFont().deriveFont(40f));
            g.drawString(StudentID, 100, 100);
            g.dispose();
            return nm_file;
        } catch (AWTException ex) {
            Logger.getLogger(captureImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(captureImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
