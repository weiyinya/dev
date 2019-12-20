package com.wy.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author weiyin
 * @date 2019-04-20 13:38
 */
public class Client {
    public static void main(String[] args) {
        try {
            //1.创建socket用来与服务器端进行通信，发送请求建立连接，指定服务器地址和端口
            Socket socket=new Socket("localhost",8888);
            //2.获取输出流用来向服务器端发送登陆的信息
            OutputStream os=socket.getOutputStream();//字节输出流
            PrintWriter pw=new PrintWriter(os);//将输出流包装成打印流
            pw.write("用户名：admin；密码：123");
            pw.flush();
            socket.shutdownOutput();//关闭输出流
            //3.获取输入流，用来读取服务器端的响应信息
            InputStream is=socket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));//添加缓冲
            String info=null;
            while((info=br.readLine())!=null){
                System.out.println("我是客户端，服务器端说"+info);
            }

            //4.关闭其他相关资源
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {


        }
    }
}
