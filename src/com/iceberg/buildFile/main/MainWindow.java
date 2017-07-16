package com.iceberg.buildFile.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import com.iceberg.buildFile.server.AContextServer;
import com.iceberg.buildFile.service.StartService;
import com.iceberg.buildFile.util.PropUtil;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.SwingConstants;

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
	
	/**
	 * 初始化
	 */
	private void init(){
		templateText.setText(Setting.scanfFilePath);
		produceText.setText(Setting.produceRoot);
		showMSLable.setText(Setting.showMS);
		setPath();
	}
	private void setPath(){
		alertPath();
		String scanfFilePath = PropUtil.properties.getProperty("scanfFilePath");
		String produceRoot = PropUtil.properties.getProperty("produceRoot");
		if(!"".equals(scanfFilePath)){
			//scanfFilePath.replaceAll("\\", "\\\\");
			Setting.scanfFilePath = scanfFilePath;
		}
		if(!"".equals(produceRoot)){
			Setting.produceRoot = produceRoot;
		}
	}
	private void alertPath(){
		PropUtil.properties.setProperty("scanfFilePath", templateText.getText());
		PropUtil.properties.setProperty("produceRoot", produceText.getText());
		File templateFile = new File(produceText.getText());
		if(!templateFile.isDirectory()){
			templateFile.mkdir();
		}
		File produceFile = new File(templateText.getText());
		if(!produceFile.isDirectory()){
			produceFile.mkdir();
		}
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
		frmBdf.setTitle("BDF");
		frmBdf.setBounds(100, 100, 504, 299);
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
		templateText.setBounds(109, 61, 347, 33);
		panel.add(templateText);
		templateText.setColumns(12);
		
		JLabel lblNewLabel = new JLabel("模板路径：");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 16));
		lblNewLabel.setForeground(SystemColor.control);
		lblNewLabel.setBounds(19, 60, 80, 33);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("开 始");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showMSLable.setText("正在生成文件...");
			}
		});
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.setFont(new Font("SimSun", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPath();
				try{
					StartService startService = (StartService) AContextServer.aContextServer.getAppContext().getBean("BDF.startService");
					startService.start();
				}catch(Exception exception){
					exception.printStackTrace();
				}finally {
					showMSLable.setText(Setting.showMS+"生成完毕！");
				}
			}
		});
		btnNewButton.setBounds(199, 193, 110, 33);
		panel.add(btnNewButton);
		
		produceText = new JTextField();
		produceText.setForeground(SystemColor.control);
		produceText.setBackground(new Color(0, 0, 0));
		produceText.setColumns(12);
		produceText.setBounds(109, 127, 347, 33);
		panel.add(produceText);
		
		JLabel label_1 = new JLabel("生成路径：");
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
	}
}
