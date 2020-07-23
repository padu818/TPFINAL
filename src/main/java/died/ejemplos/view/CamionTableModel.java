package died.ejemplos.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import died.ejemplos.dominio.Camion;

public class CamionTableModel extends AbstractTableModel {
	//https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#data
	
	public CamionTableModel(List<Camion> camiones) {
		this.data = camiones;
	}
    private String[] columnNames =  {"ID","Patente","Marca","Modelo","Kmts"};
    private List<Camion> data ;

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Camion cam = data.get(row);
        switch(col) {
	        case 0:
	        	return cam.getId();
	        case 1:
	        	return cam.getPatente(); 
	        case 2:
	        	return cam.getMarca(); 
	        case 3:
	        	return cam.getModelo(); 
	        case 4:
	        	return cam.getKm(); 
        }
        return null;
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
//        if (col < 2) {
//            return false;
//        } else {
//            return true;
//        }
    	return false;
    }

//    /*
//     * Don't need to implement this method unless your table's
//     * data can change.
//     */
//    public void setValueAt(Object value, int row, int col) {
//    	Camion c = data.get(row);
//        switch(col) {
//	        case 0:
//	        	return cam.getId();
//	        case 1:
//	        	return cam.getPatente(); 
//	        case 2:
//	        	return cam.getMarca(); 
//	        case 3:
//	        	return cam.getModelo(); 
//	        case 4:
//	        	return cam.getKm(); 
//        }
//    	c.setCostoHora(value);
//        // data[row][col] = value;
//        // fireTableCellUpdated(row, col);
//    }
    
}