package com.lb.studentapartment;

import com.lb.studentapartment.util.UartConfig;
import com.lb.studentapartment.util.UartServer;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@MapperScan("com.lb.studentapartment.dao")
@SpringBootApplication
public class StudentapartmentApplication {
    static SerialPort serialPort;

    public static SerialPort getSerialPort() {
        return serialPort;
    }

    public static void setSerialPort(SerialPort serialPort) {
        StudentapartmentApplication.serialPort = serialPort;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentapartmentApplication.class, args);

        try {
            serialPort = UartConfig.openPort("COM3", 115200);

        } catch (PortInUseException e) {
            e.printStackTrace();
        }

        UartConfig.addListener(serialPort,new SerialListener());
    }


    static class SerialListener implements SerialPortEventListener {

        // 处理监控到的串口事件
        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {

            switch (serialPortEvent.getEventType()) {

                case SerialPortEvent.BI: // 10 通讯中断
                    break;

                case SerialPortEvent.OE: // 7 溢位（溢出）错误

                case SerialPortEvent.FE: // 9 帧错误

                case SerialPortEvent.PE: // 8 奇偶校验错误

                case SerialPortEvent.CD: // 6 载波检测

                case SerialPortEvent.CTS: // 3 清除待发送数据

                case SerialPortEvent.DSR: // 4 待发送数据准备好了

                case SerialPortEvent.RI: // 5 振铃指示

                case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
                    break;

                case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
                {
                    byte[] rec_data = {};
                    rec_data = UartConfig.readFromPort(serialPort);
                    String light_data=new String(rec_data);//原始数据
                    System.out.println(light_data);
                    System.out.println("data:"+light_data);
                    UartConfig.sendToPort(serialPort,'1');
                  //  System.out.println("发送数据");
                    UartServer.find(serialPort,light_data);
                }
                break;
            }
        }
    }

}
