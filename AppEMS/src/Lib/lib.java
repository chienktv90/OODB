/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lib;

import java.io.File;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author chienktv90
 */
public class lib {
    
    public lib()
    {
    }
    
    public void toExcel(JTable table, File file){
            try{
                    TableModel model = table.getModel();
                    java.io.FileWriter excel = new java.io.FileWriter(file);

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
}
