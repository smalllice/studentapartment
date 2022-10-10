package com.lb.studentapartment.util;

import com.lb.studentapartment.bean.AddInformation;
import com.lb.studentapartment.bean.Student;
import com.lb.studentapartment.dao.UserMapper;
import gnu.io.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 标题：类名称:UartServer
 * 说明：主要用于监听串口有数据时的相关操作
 * 时间：2022/5/28 14:58
 * 作者 @author BGG
 */

public class UartServer {
    private static String ID;
    public static String getID() {
        return ID;
    }
    public static void setID(String ID) {
        UartServer.ID = ID;
    }



    public static void find(SerialPort serialPort, String data){
        UserMapper userMapper = SpringUtil.getBean(UserMapper.class);

        System.out.println(serialPort);

        String flag=data.substring(0,2);

        System.out.println(flag);

        String StringID=data.substring(2,data.length());

        System.out.println(StringID);

        if(flag.equals("04")){
                ID=StringID;
            UartConfig.sendToPort(serialPort,'1');
        }else if(flag.equals("01")){
            //System.out.println("产生出行记录");

           Student student = userMapper.getStudentBycard(StringID);
            System.out.println(student);
            if(student!=null){
             //   System.out.println("OK");
                //封装插入数据所要的对象
                UUID uuid = UUID.randomUUID();
                System.out.println(uuid.toString());

                AddInformation addInformation = new AddInformation();
                addInformation.setNumber(uuid.toString());
                addInformation.setTid(student.getTid());
                addInformation.setHid(student.getHid());
                addInformation.setHname(student.getHname());
                addInformation.setSid(student.getSid());

                SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String datetime = tempDate.format(new Date());
                System.out.println(datetime);

                addInformation.setHtime(datetime);

                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
                Date now =null;
                Date beginTime = null;
                Date endTime = null;

                try {
                    now = df.parse(df.format(new Date()));
                    beginTime = df.parse("22:00:00");
                    endTime = df.parse("23:59:59");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Hstate插入0为进入公寓,插入1为出公寓，插入2为晚归公寓，
                if(student.getState()==0) {//学生0现在在公寓
                    addInformation.setHsate(1);
                    //把学生状态改为1，在公寓
                    userMapper.updateStudentState(StringID,1);
                }else if(student.getState()==1){
                    Boolean t = belongCalendar(now, beginTime, endTime);
                   // System.out.println("t是什么："+t);
                   // System.out.println("ok");
                    if(t){
                        System.out.println("1");
                        addInformation.setHsate(2);
                        //把学生状态改为0，不在公寓
                        userMapper.updateStudentState(StringID,0);
                    }else {
                        addInformation.setHsate(0);
                       // System.out.println("2");
                        //把学生状态改为0，在公寓
                        userMapper.updateStudentState(StringID,0);
                    }
                }
                System.out.println(addInformation);
              //  int g=0;
                userMapper.addinfor2(addInformation.getNumber(),addInformation.getTid(),addInformation.getHid(),addInformation.getHname(),addInformation.getSid(),addInformation.getHtime(),addInformation.getHsate());
           //     g = userMapper.addinfor(addInformation);
             //   System.out.println(g);
            }else {
                UartConfig.sendToPort(serialPort,'2');
            }

            UartConfig.sendToPort(serialPort,'1');
        }

    }

    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
