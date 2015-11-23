/*
author		: Mr. Xymon
website		: https://sites.google.com/site/teachmemrxymon/
program		: JTableToExcel 1.2
description	: A simple demonstration on how to export data from JTable to MS Excel file (.xls)
update		: Added JFileChooser dialog for browsing when saving the excel file
date		: 2012.04.27
*/

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class JTableToExcel extends JFrame{
    String header[] = {"Firstname","Lastname","Email"};
	String data[][];
	DefaultTableModel model = new DefaultTableModel(data, header);
	JTable table = new JTable(model);
	JPanel pane = new JPanel();
	JPanel pane_input = new JPanel();
	JLabel lbl_firstname = new JLabel(" Firstname");
	JLabel lbl_lastname = new JLabel(" Lastname");
	JLabel lbl_email = new JLabel(" Email");
	JTextField txt_firstname = new JTextField(10);
	JTextField txt_lastname = new JTextField(10);
	JTextField txt_email = new JTextField(10);
	JButton btn_add = new JButton("Add");
	JButton btn_excel = new JButton("Export");
 
	public JTableToExcel(){
	    setSize(200,200);
		setPreferredSize(new Dimension(350,300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("JTable to Excel Demo");

		btn_add.setMnemonic(KeyEvent.VK_A);
		btn_excel.setMnemonic(KeyEvent.VK_X);

        pane_input.setLayout(new GridLayout(0,2));
		pane_input.add(lbl_firstname);
		pane_input.add(txt_firstname);
		pane_input.add(lbl_lastname);
		pane_input.add(txt_lastname);
		pane_input.add(lbl_email);
		pane_input.add(txt_email);
		pane_input.add(btn_excel);
		pane_input.add(btn_add);

		pane.setLayout(new BorderLayout());
        pane.add(pane_input, BorderLayout.NORTH);
		pane.add(new JScrollPane(table), BorderLayout.CENTER);

        add(pane);
		pack();
		setVisible(true);

		btn_add.addActionListener(new AksyonListener());
		btn_excel.addActionListener(new AksyonListener());
		txt_email.addActionListener(new AksyonListener());
	}

    public void toExcel(JTable table, File file){
		try{
			TableModel model = table.getModel();
			FileWriter excel = new FileWriter(file);

			for(int i = 0; i < model.getColumnCount(); i++){
				excel.write(model.getColumnName(i) + "\t");
			}

			excel.write("\n");

			for(int i=0; i< model.getRowCount(); i++) {
				for(int j=0; j < model.getColumnCount(); j++) {
					excel.write(model.getValueAt(i,j).toString()+"\t");
				}
				excel.write("\n");
			}

			excel.close();
		}catch(IOException e){ System.out.println(e); }
	}

	public static void main(String[] args){
        new JTableToExcel();
	}

    class AksyonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e){
			if(e.getSource() == btn_add || e.getSource() == txt_email){
				String fn = txt_firstname.getText();
				String ln = txt_lastname.getText();
				String mail = txt_email.getText();
				
				txt_firstname.setText("");
				txt_lastname.setText("");
				txt_email.setText("");
				txt_firstname.requestFocus();

				model.insertRow(model.getRowCount(), new Object[]{fn, ln, mail});
			}
                        else if(e.getSource() == btn_excel){
                        JFileChooser fc = new JFileChooser();
                        int option = fc.showSaveDialog(JTableToExcel.this);
                        if(option == JFileChooser.APPROVE_OPTION){
                            String filename = fc.getSelectedFile().getName(); 
                            String path = fc.getSelectedFile().getParentFile().getPath();

                                                int len = filename.length();
                                                String ext = "";
                                                String file = "";

                                                if(len > 4){
                                                        ext = filename.substring(len-4, len);
                                                }

                                                if(ext.equals(".xls")){
                                                        file = path + "\\" + filename; 
                                                }else{
                                                        file = path + "\\" + filename + ".xls"; 
                                                }
                                                toExcel(table, new File(file));
                                        }
                                }
                        }
	}
}