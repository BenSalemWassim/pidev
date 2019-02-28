/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author wassim
 */
public class Chat {
    
    
    String des ;
    String ip ;
String send ;

    public Chat(String des, String ip, String send) {
        this.des = des;
        this.ip = ip;
        this.send = send;
    }

    public String getSend() {
        return send;
    }

    public Chat() {
    }
   

    public void setDes(String des) {
        this.des = des;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDes() {
        return des;
    }

    public String getIp() {
        return ip;
    }
    
    
}
