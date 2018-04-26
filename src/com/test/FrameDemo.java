package com.test;

import java.awt.Button;  
import java.awt.FlowLayout;  
import java.awt.Frame;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;  
  
public class FrameDemo {  
  
    //�����ͼ��������Ҫ�����������  
    private Frame f;  
    private Button but;  
      
    FrameDemo(){  
        init();  
          
    }  
      
    public void init(){  
          
        f=new Frame("my freame");  
        f.setBounds(300,100,600,500);  
        f.setLayout(new FlowLayout());  //������ʽ����  
          
        but=new  Button("my button");  
        //�������ӵ� frame��  
        f.add(but);  
          
        //����һ�´����ϵ��¼�.  
        myEvent();  
        //��ʾ����  
        f.setVisible(true);  
    }  
    private void myEvent(){  
        f.addWindowListener(new WindowAdapter(){  
            @Override  
            public void windowClosing(WindowEvent e) {  
                // TODO Auto-generated method stub  
                //super.windowClosing(e);  
                System.exit(0);  
            }  
        });  
          
        //�ð�ť�߱��˳�����Ĺ���  
        /* 
         * ��ť�����¼�Դ 
         * ��ôѡ���Ǹ���������? 
         * ͨ���رմ���ʵ���˽⵽,�¸�Ҫ֪���Ǹ�����߱�ʲô�������м�����, 
         * ��Ҫ�鿴���������Ĺ���. 
         */  
        //���һ�������  
        but.addActionListener(new ActionListener() {  
              
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // TODO Auto-generated method stub  
                System.out.println("�˳�, ��ť�ɵ�");  
                System.exit(0);  
            }  
        });  
          
    }  
      
    public static void main(String[] args) {  
        FrameDemo f=new FrameDemo();  
    }  
  
}