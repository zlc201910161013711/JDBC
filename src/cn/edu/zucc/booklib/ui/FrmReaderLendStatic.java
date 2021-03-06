package cn.edu.zucc.booklib.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.booklib.control.BookLendManager;
import cn.edu.zucc.booklib.control.BookManager;
import cn.edu.zucc.booklib.control.ReaderManager;
import cn.edu.zucc.booklib.model.BeanBook;
import cn.edu.zucc.booklib.model.BeanBookLendRecord;
import cn.edu.zucc.booklib.model.BeanReader;
import cn.edu.zucc.booklib.model.StaticBeanReaderLend;
import cn.edu.zucc.booklib.util.BaseException;
import cn.edu.zucc.booklib.util.DbException;

public class FrmReaderLendStatic extends JDialog {
	
	private Object tblTitle[]={"读者证号","姓名","借阅数量","罚金总额"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	
	private void reloadTable(){
		try {
			List<StaticBeanReaderLend> records=(new BookLendManager()).staticReaderLend();
			tblData =new Object[records.size()][4];
			for(int i=0;i<records.size();i++){
				tblData[i][0]=records.get(i).getReaderId();
				tblData[i][1]=records.get(i).getReaderName();
				tblData[i][2]=""+records.get(i).getCount();
				tblData[i][3]=records.get(i).getPenalSum()+"";
			}
			
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FrmReaderLendStatic(Frame f, String s, boolean b) {
		super(f, s, b);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
	}
}
