package com.test;

import java.awt.Button;  
import java.awt.FlowLayout;  
import java.awt.Frame;  
import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;  
  
/** 
 * GUI(ͼ���û�����) 
 *  Graphical User Interface(ͼ���û��ӿ�) 
 *  ��ͼ�εķ�ʽ,����ʾ����������Ľ���,�����������ֱ��. 
 *  
 * CLI 
 *  Command Line User Interface(�������û��ӿ�) 
 *  ���ǳ��õ�Dos�����в���. 
 *  ��Ҫ����һЩ���õ�����.������ֱ��. 
 *  
 * ����: 
 *   ����:�����ļ���,����ɾ���ļ��е�  
 *   md haha   del haha   
 *    
 *    
 * Java��GUI�ṩ�Ķ��󶼴��� java.Awt �� javax.Swing ��������. 
 *  
 * java.Awt:Abstract Window ToolKit(���� ���ڹ��߰�) 
 *    ��Ҫ���ñ���ϵͳ����ʵ�ֹ���.���������ؼ� (��ƽ̨����ǿ) 
 *   
 * java.Swing:��AWT�Ļ�����,������һ��ͼ�ν���ϵͳ,�����ṩ�˸�������, 
 *   ������ȫ��javaʵ��,��ǿ����ֲ��,�����������ؼ�.(��ƽ̨�ܺ�) 
 *    
 * java.swt: IBM ��˾���� Eclipse �õ�������� ����Eclipse��վ���غ�Ϳ���ʹ����. 
 *  
 *  
 * ���ֹ����� 
 * 1)�����е�������ŷŷ�ʽ,���ǲ���. 
 * 2)�����Ĳ��ֹ����� 
 *   FlowLayout(��ʽ���ֹ�����) 
 *     �����ҵ�˳������ 
 *     PanelĬ�ϵĲ��ֹ����� 
 *   BorderLayout(��Ⲽ�ֹ�����) 
 *     ��  ��  ��  ��   �� 
 *     Frame Ĭ�ϵĲ��ֹ����� 
 *     ��ָ�����ַ�ʽ,Ĭ�� ��������,�����һ�� Ҳ�� �������� 
 *   GridLayout (���񲼾ֹ�����) 
 *     ����ľ��� 
 *   CardLayout  (��Ƭ���ֹ�����) 
 *     ѡ� 
 *   GridBagLayout(��������ֹ�����) 
 *    �ǹ���ľ��� 
 *     
 * �¼������������ 
 *  �¼�Դ:   
 *  �¼�:Event 
 *  ������:Listener 
 *  ʱ�䴦��:(�����¼�����ʽ) 
 *   
 *  �¼�Դ:����awt������swing���е���Щͼ��������. 
 *  �¼�:ÿ���¼�Դ�����Լ��ض��Ķ�Ӧʱ��͹���ʱ��. 
 *  ������:���Գ���ĳһ���¼��Ķ������Ѿ���װ����������. 
 */  
  
  
public class GuiDemo {  
    public static void main(String[] args) {  
        Frame f=new Frame("my awt");  
        f.setSize(500,400);  
        f.setLocation(300,200);  
        f.setLayout(new FlowLayout());  
          
        Button b=new Button("����һ����ť");  
          
        f.add(b);  
          
        f.addWindowListener(new MyWin());  
          
        f.setVisible(true);  
        System.out.println("Hello world!");  
    }  
  
}  
  
//��Ϊ�ӿ�WindowLinstener�е����з����������� WindowAdapterʵ����,.  
//���Ҹ��������е����з���,��ô����ֻ�ܼ̳� WindowAdapter �������ǵķ�������  
class MyWin extends WindowAdapter{  
      
    @Override  
    public void windowClosing(WindowEvent e) {  
        // TODO Auto-generated method stub  
        //System.out.println("Window closing"+e.toString());  
        System.out.println("�ҹ���");  
        System.exit(0);  
    }  
    @Override  
    public void windowActivated(WindowEvent e) {  
        //ÿ�λ�ý��� �ͻᴥ��  
        System.out.println("�һ���");    
        //super.windowActivated(e);  
    }  
    @Override  
    public void windowOpened(WindowEvent e) {  
        // TODO Auto-generated method stub  
        System.out.println("�ҿ���");  
        //super.windowOpened(e);  
    }  
      
}