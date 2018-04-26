package com.test;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Log extends JFrame  {
	public static void main(String[] args) {
	        Log log = new Log();
	        
	    }
	    private JButton qr_no_icon;//不生成ico
	    private JButton qr_icon; //生程ico
	    private JTextField qr_name; //名称
	    private JTextField qr_size; //大小
	    private JTextField qr_bg_c; //背景色
	    private JTextField qr_front_c;//前景色
//	    private JPasswordField tfPwd;
	    private JCheckBox qr_is_transparent;//是否透明
//	    private JTextField tfUser;
		private JComboBox adminType;
		private JTextField qr_text;//二维码内容
		private JLabel names;
		private JLabel size;
		private JLabel bg;
		private JLabel front;
		private JLabel texts;
		private JFileChooser qr_is_icon;

		public Log() {
	        super("测试");
	        super.setSize(380, 500);
	        ImageIcon icon = new ImageIcon("img/001.png");  
	        super.setIconImage(icon.getImage());
	        super.setVisible(true);
	        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        centered(this);
	        qr_no_icon = new JButton("没有icon生成");
	        qr_icon =new JButton("有icon生成");
	        qr_no_icon.setBounds(new Rectangle(93, 380, 180, 30));//参数分别是坐标x，y，宽，高
	        qr_icon.setBounds(new Rectangle(93, 420, 180, 30));//参数分别是坐标x，y，宽，高
	        this.setLayout(null);//设置布局管理器为空
	        this.add(qr_no_icon);
	        this.add(qr_icon);
	        qr_name = new JTextField();
	        names=new JLabel();
	        names.setText("二维码名称");
	        names.setBounds(10, 15, 1000, 25);
	        this.add(names);
	        texts=new JLabel();
	        texts.setBounds(10, 40, 1000, 25);
	        texts.setText("二维码内容");
	        this.add(texts);
	        size=new JLabel();
	        size.setBounds(10, 65, 1000, 25);
	        size.setText("二维码尺寸");
	        this.add(size);
	        bg=new JLabel();
	        bg.setBounds(10, 90, 1000, 25);
	        bg.setText("背景色");
	        this.add(bg);
	        front=new JLabel();
	        front.setBounds(10, 115, 1000, 25);
	        front.setText("前景色");
	        this.add(front);
	        qr_name.setBounds(new Rectangle(90, 15, 220, 25));
	        this.add(qr_name);
	        qr_text = new JTextField();
	        qr_text.setBounds(new Rectangle(90, 40, 220, 25));
	        this.add(qr_text);
	        qr_size = new JTextField();
	        
	        qr_size.setBounds(new Rectangle(90, 65, 220, 25));
	        this.add(qr_size);
	        
	        qr_bg_c = new JTextField();
	        
	        qr_bg_c.setBounds(new Rectangle(90, 90, 220, 25));
	        this.add(qr_bg_c);
	        
	        qr_front_c = new JTextField();
	        
	        
	          
//	        qr_bg_c.setText("背景色");
//	        qr_text.setText("生成内容");
	        qr_bg_c.setToolTipText("0xddddfff");
//	        qr_size.setText("300*900");
	        qr_size.setToolTipText("300*300");
//	        qr_name.setText("二维码名称"); 
	        qr_name.setToolTipText("名称输入xxx.png/jpg");
//	        qr_front_c.setText("前景色,透明图则忽略");
	        qr_front_c.setToolTipText("0xfdfdffd");
	        qr_front_c.setBounds(new Rectangle(90, 115, 220, 25));
	        this.add(qr_front_c);
	        
//	        tfPwd = new JPasswordField();
//	        tfPwd.setBounds(new Rectangle(73, 150, 220, 25));
//	        this.add(tfPwd);
	        qr_is_transparent = new JCheckBox("是否透明");
	        qr_is_transparent.setBounds(new Rectangle(118, 350, 110, 25));
	        this.add(qr_is_transparent);
//	        qr_is_icon = new JFileChooser();
//	        qr_is_icon.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
//	        qr_is_icon.showDialog(new JLabel(), "选择");  
//	        File file=qr_is_icon.getSelectedFile();  
//	        this.add(qr_is_icon);
	        
	    
	        
	        
//	        adminType = new JComboBox<Object>(new String[] { "普通职员", "管理员", "高级管理员" });
//	        adminType.setBounds(new Rectangle(183, 185, 100, 25));
//	        this.add(adminType);
	        qr_icon.addActionListener(new ActionListener() {  
	            
	            @Override  
	            public void actionPerformed(ActionEvent e) {  
	                // TODO Auto-generated method stub  
	            	String qr_name_1=qr_name.getText();
	            	String qr_size_1=qr_size.getText();
	            	String qr_bg_c_1=qr_bg_c.getText();
	            	String qr_front_c_1=qr_front_c.getText();
	            	String qr_text_1=qr_text.getText();
	            	boolean ss=qr_is_transparent.isSelected();
	            	if (qr_name_1.equals("") || qr_size_1.equals("")||qr_bg_c_1.equals("") || qr_front_c_1.equals("")||qr_text_1.equals("") ){
	            		System.out.println("二维码名称等都不能为空");
	            		JOptionPane.showMessageDialog(null, "二维码名称等都不能为空", "警告", JOptionPane.ERROR_MESSAGE); 
	            	}
	            	else{
	            		String[] s=qr_size_1.split("\\*");
	                    int x = Integer.parseInt(s[0]);
	                    int y = Integer.parseInt(s[1]);
	            		QRCode.encode(qr_text_1,x,y, "E:\\测试\\6608.jpg","E:\\测试\\"+qr_name_1);
//	            		for (int i=0;i<s.length;i++){
//	            			System.out.println("300*400".split("\\*").toString());
//	            		}
//	            			
//	            		System.out.println();
//	            		System.out.println(qr_front_c_1);
//		                System.out.println(qr_name_1);
//		                System.out.println(qr_size_1);
//		                System.out.println(qr_bg_c_1);
//		                System.out.println(qr_text_1);
	            	}
//	                System.out.println("点了生成icon按钮");  
//	                System.exit(0);  
	                
//	                
	            }  
	        });  
	        qr_no_icon.addActionListener(new ActionListener() {  
	            
	            @Override  
	            public void actionPerformed(ActionEvent e) {  
	                // TODO Auto-generated method stub  
	                System.out.println("点了生成无icon按钮");  
//	                System.exit(0);  
	            }  
	        });  
	    
	    }
	//布局居中方法
	    public void centered(Container container) {
	        Toolkit toolkit = Toolkit.getDefaultToolkit();
	        Dimension screenSize = toolkit.getScreenSize();
	        int w = container.getWidth();
	        int h = container.getHeight();
	        container.setBounds((screenSize.width - w) / 2,
	                (screenSize.height - h) / 2, w, h);
	    }
	    

}
