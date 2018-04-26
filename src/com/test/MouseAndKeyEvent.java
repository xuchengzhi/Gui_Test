package com.test;

import java.awt.BorderLayout;
import java.awt.Button;  
import java.awt.Dimension;
import java.awt.FlowLayout;  
import java.awt.Frame;  
import java.awt.TextField;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.KeyAdapter;  
import java.awt.event.KeyEvent;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;  

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
  
public class MouseAndKeyEvent {  
  
    private Frame f;
    private Button but;  
    private Button but1; 
    private Button but2;
    private Button but3;
    private TextField tf;  
      
    public MouseAndKeyEvent() {  
        init();  
    }  
  
    private void init(){  
        f=new Frame("����");  
        f.setBounds(300, 200, 600, 500);  
        f.setLayout(new FlowLayout());  
          
        tf=new TextField(20);  
        but=new Button("��ʼ");
//        but.setPreferredSize(new Dimension(100,100));
        but1=new Button("����");
//        but1.setPreferredSize(new Dimension(100,100));
        but2=new Button("��ͣ"); 
//        but2.setPreferredSize(new Dimension(100,100));
        but3=new Button("�˳�");
//        but3.setPreferredSize(new Dimension(100,100));

        f.add(tf);  
        f.add(but);  
        f.add(but1);
        f.add(but2);
        f.add(but3);
        
        event();  
          
        f.setVisible(true);  
    }  
  
    private void event(){  
        f.addWindowListener(new WindowAdapter() {  
            @Override  
            public void windowClosing(WindowEvent e) {  
                // TODO Auto-generated method stub  
                System.exit(0);  
            }  
          
        });  
        tf.addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e){  
                int code=e.getKeyCode();  
                if(!(code>=KeyEvent.VK_0 && code<=KeyEvent.VK_9)){  
                    System.out.println(code+"....�Ƿ�������");  
                    e.consume();  //��ִ�м����ı���.  
                }  
                  
            }  
              
        });  
          
          
          
        but.addActionListener(new ActionListener() {  
              
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // TODO Auto-generated method stub  
                System.out.println("actionPerformed �һ��");  
            }  
        });  
          
        but.addMouseListener(new MouseAdapter() {  
            private int count=0;  
            private int clickCount=1;  
            public void mouseEntered(MouseEvent e){  
                System.out.println("�����뵽�����"+count++);
            }  
            public void mouseClicked(MouseEvent e){  
                if(e.getClickCount()==2){  
                    System.out.println("˫������");  
                }else  
                    System.out.println("�������"+clickCount++);  
                  
            }  
              
        });  
        //��� �����¼�  
        but.addKeyListener(new KeyAdapter() {  
          
            public void keyPressed(KeyEvent e){  
                System.out.println(e.getKeyChar()+"..."+e.getKeyCode());  
                System.out.println(KeyEvent.getKeyText(e.getKeyCode())+"..."+e.getKeyCode());  
                //enter  ���˳�  
                /*if(e.getKeyCode()==KeyEvent.VK_ENTER) 
                    System. 
                    exit(0);*/  
                  
                //ctrl + Enter ������Ϣ  
                if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_ENTER){  
                    System.out.println("��Ҫ������Ϣ!");  
                }  
                  
            }  
          
        });  
    }  
  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        new MouseAndKeyEvent();  
		
		
    }  
  
}
