package io.plus.shop.netty;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Description:
 *
 * @author Super_light
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1",6666);
        OutputStream os = s.getOutputStream();
        //将数据写入到socket
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("Hello,server!");
        //读取从服务端传回来的数据
        DataInputStream dis = new DataInputStream(s.getInputStream());
        System.out.println(dis.readUTF());
    }
}

