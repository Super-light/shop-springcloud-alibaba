package io.plus.shop.netty;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description: Socket服务端Demo
 *
 * @author Super_light
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        //创建一个ServverSocket监听6666端口
        ServerSocket ss = new ServerSocket(6666);
        while (true) {
            Socket s = ss.accept();
            System.out.println("A client connected!");
            //从socket中获取数据流
            DataInputStream dis = new DataInputStream(s.getInputStream());
            //将数据流中的数据写入到socket
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            String str = dis.readUTF();
            if (!str.isEmpty()) {
                //读取从客户端传来的数据
                System.out.println(str);
                System.out.println("from" + s.getInetAddress() + ",port #" + s.getPort());
            }
            //写入数据
            dos.writeUTF("Hello," + s.getInetAddress() + ",port#" + s.getPort());
            dis.close();
            dos.close();
            s.close();
        }
    }
}

