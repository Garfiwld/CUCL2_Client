/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import Model.StudentModel;
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

public class CaptureImage {
//    StudentModel studentModel = new StudentModel();
    public String CapImg(){
        StudentModel studentModel = new StudentModel();
        try{
        byte[] b;
            File f;
            int c;
            InputStream in;
            OutputStream out;
            String StudentID = studentModel.getStudentid();
            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            Date date = new Date();
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String datetime = dateFormat1.format(date);
            studentModel.setDatetime(datetime);
            String filename = StudentID + "_" + datetime + ".jpg";
            String pathFile = "C:\\Controllab\\picture\\" + filename;
            ImageIO.write(screenShot, "JPG", new File(pathFile));
            BufferedImage image = ImageIO.read(new File(pathFile));
            Graphics g = image.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(90, 50, 310, 70);
            g.setColor(Color.red);
            g.setFont(g.getFont().deriveFont(40f));
            g.drawString(StudentID, 100, 100);
            g.dispose();
            return pathFile;
        } catch (AWTException ex) {
            Logger.getLogger(CaptureImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CaptureImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
