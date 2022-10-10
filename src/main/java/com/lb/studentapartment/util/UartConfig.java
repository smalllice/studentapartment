package com.lb.studentapartment.util;

import gnu.io.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 * 标题：类名称:UartConfig
 * 说明：串口的相关配置类以及相关方法
 * 时间：2022/5/26 22:18
 * 作者 @author BGG
 */
public class UartConfig {

    //查找所有可用端口
    @SuppressWarnings("unchecked")
    public static final ArrayList<String> findPorts(){
        Enumeration<CommPortIdentifier> portlist=CommPortIdentifier.getPortIdentifiers(); //扫描可用端口
        ArrayList<String> portNameList=new ArrayList<String>();
        while(portlist.hasMoreElements()) {
            String portName=portlist.nextElement().getName(); //获取端口名字6
            portNameList.add(portName);
        }
        return portNameList;  //返回所有可用端口名字
    }

    //打开串口
    public static final SerialPort openPort(String portName, int baudrate) throws PortInUseException {
        try {
            //通过端口名识别端口
            CommPortIdentifier portIdentifier=CommPortIdentifier.getPortIdentifier(portName);
            //打开端口，给出超时时间
            CommPort commPort=portIdentifier.open(portName,2000);
            //判断是不是串口
            if(commPort instanceof SerialPort) {
                SerialPort serialPort=(SerialPort) commPort;
                try {
                    // 设置一下串口的波特率等参数
                    // 数据位：8
                    // 停止位：1
                    // 校验位：None
                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                }catch (UnsupportedCommOperationException e) {
                    e.printStackTrace();
                }
                return serialPort;
            }

        }catch (NoSuchPortException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    //关闭串口
    public static void closePort(SerialPort serialPort) {
        if(serialPort!=null) {
            serialPort.close();
        }
    }


    //往串口发送数据
    public static void sendToPort(SerialPort serialPort,char order) {
        OutputStream out=null;
        try {
            out=serialPort.getOutputStream();
            out.write(order);
            out.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null) {
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }



    //从串口读取数据
    public static byte[] readFromPort(SerialPort serialPort) {
        InputStream in=null;
        byte[] bs= {};
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            in =serialPort.getInputStream();
            byte[] readbuffer=new byte[1];
            int bytesNum=in.read(readbuffer);
            while(bytesNum>0) {
                os.write(readbuffer);
                bytesNum=in.read(readbuffer);
            }
            bs=os.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bs;
    }

    //添加监听器
    public static void addListener(SerialPort serialPort, SerialPortEventListener listener) {
        try {
            // 给串口添加监听器
            serialPort.addEventListener(listener);
            // 设置当有数据到达时唤醒监听接收线程
            serialPort.notifyOnDataAvailable(true);
            // 设置当通信中断时唤醒中断线程
            serialPort.notifyOnBreakInterrupt(true);
        }catch (TooManyListenersException e) {
            e.printStackTrace();
        }
    }
}
