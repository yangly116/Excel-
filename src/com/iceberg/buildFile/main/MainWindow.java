package com.iceberg.buildFile.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iceberg.buildFile.server.AContextServer;
import com.iceberg.buildFile.service.StartService;

/** 
 * 
 * @author 作者：杨文培 
 * @version 创建时间：Jul 16, 2017 8:31:53 PM  
 */

public class MainWindow {

	private JFrame frmBdf;
	private JTextField templateText;
	private JTextField produceText;
	private JPanel panel;
	private JLabel showMSLable;
	private StartService startService = (StartService) AContextServer.aContextServer.getAppContext().getBean("BDF.startService");
	private int res = -100;
	private Log log = LogFactory.getLog(MainWindow.class);
	/**
	 * 初始化
	 */
	private void init(){
		System.setProperty("log_home", Setting.logDir);
		log.info("SYS_log_home"+Setting.logDir);
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包 
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸 
		int screenWidth = screenSize.width/2; // 获取屏幕的宽
 		int screenHeight = screenSize.height/2; // 获取屏幕的高
 		int height = frmBdf.getHeight(); int width = frmBdf.getWidth(); 
 		frmBdf.setLocation(screenWidth-width/2, screenHeight-height/2);
		templateText.setText(Setting.scanfFilePath);
		produceText.setText(Setting.scriptPath);
		showMSLable.setText(Setting.getShowMS());
	}
	private void setPath(){
		Setting.setScriptPath(produceText.getText());
		Setting.setScanfFilePath(templateText.getText());
		System.out.println("setPath();");
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmBdf.setVisible(true);
					window.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBdf = new JFrame();
		frmBdf.setTitle("YWP-BDF-1.0");
		frmBdf.setBounds(100, 100, 504, 281);
		frmBdf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBdf.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(51, 51, 51));
		frmBdf.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		templateText = new JTextField();
		templateText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		templateText.setForeground(SystemColor.control);
		templateText.setBackground(new Color(0, 0, 0));
		templateText.setBounds(97, 61, 359, 33);
		panel.add(templateText);
		templateText.setColumns(12);
		
		JLabel lblNewLabel = new JLabel("输入目录：");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 16));
		lblNewLabel.setForeground(SystemColor.control);
		lblNewLabel.setBounds(19, 60, 80, 33);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("生成脚本");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showMSLable.setText("正在生成文件...");
			}
		});
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					setPath();
					startService.start();
				}catch(Exception exception){
					exception.printStackTrace();
				}finally {
					showMSLable.setText(Setting.getShowMS()+" 生成完成！");
				}
			}
		});
		btnNewButton.setBounds(203, 193, 95, 27);
		panel.add(btnNewButton);
		
		produceText = new JTextField();
		produceText.setForeground(SystemColor.control);
		produceText.setBackground(new Color(0, 0, 0));
		produceText.setColumns(12);
		produceText.setBounds(97, 127, 359, 33);
		panel.add(produceText);
		
		JLabel label_1 = new JLabel("输出目录：");
		label_1.setForeground(SystemColor.menu);
		label_1.setFont(new Font("SimSun", Font.PLAIN, 16));
		label_1.setBounds(19, 126, 80, 33);
		panel.add(label_1);
		
		showMSLable = new JLabel("");
		showMSLable.setHorizontalAlignment(SwingConstants.CENTER);
		showMSLable.setForeground(SystemColor.menu);
		showMSLable.setFont(new Font("SimSun", Font.PLAIN, 16));
		showMSLable.setBounds(32, 10, 424, 33);
		panel.add(showMSLable);
		
		JButton button = new JButton("清空脚本");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try{
					res = JOptionPane.showConfirmDialog(null, "清空后不可恢复，确定清空输出目录？", "确认/取消", JOptionPane.YES_NO_OPTION); 
					if(res == JOptionPane.YES_NO_OPTION){
						showMSLable.setText("正在清空脚本...");
						setPath();
						startService.cleanScript();
						showMSLable.setText("清空完成！");
					}else{
						showMSLable.setText("欢迎使用BDF！");
					}
				}catch(Exception exception){
					exception.printStackTrace();
				}finally {
					res = -100;
				}
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setFont(new Font("SimSun", Font.PLAIN, 14));
		button.setBackground(SystemColor.menu);
		button.setBounds(89, 193, 95, 27);
		panel.add(button);
		
		JButton button_1 = new JButton("刷新脚本");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showMSLable.setText("正在刷新脚本...");
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					setPath();
					startService.refreshScript();
				}catch(Exception exception){
					exception.printStackTrace();
				}finally {
					showMSLable.setText("刷新完成！");
				}
			}
		});
		button_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		button_1.setBackground(SystemColor.menu);
		button_1.setBounds(326, 193, 95, 27);
		panel.add(button_1);
	}
}
