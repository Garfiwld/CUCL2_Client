/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.net.InetAddress;

/**
 *
 * @author PC2
 */
public class StudentModel {
    String ip,macaddress,username,Course;
    public void setMacaddress(String Macaddress){
        this.macaddress = Macaddress;
    }
    public String getMacaddress(){
        return this.macaddress;
    }
    public void setIp(String ip){
        this.ip = ip;
    }
    public String getIp(){
        return this.ip;
    }
    public void setUsername(String Username){
        this.username = Username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setCourse(String Course){
        this.Course = Course;
    }
    public String getCourse(){
        return this.Course;
    }
}
