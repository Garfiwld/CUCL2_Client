/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author PC2
 */
public class StudentModel {
    protected static String ipv4,macaddress,studentid,course,section,datetime;

    public static String getDatetime() {
        return datetime;
    }

    public static void setDatetime(String datetime) {
        StudentModel.datetime = datetime;
    }

    public static String getSection() {
        return section;
    }

    public static void setSection(String section) {
        StudentModel.section = section;
    }

    public StudentModel() {
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
}
