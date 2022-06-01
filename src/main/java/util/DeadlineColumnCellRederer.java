
package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Task;

/**
 *
 * @author wyltamar
 */
public class DeadlineColumnCellRederer extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                          boolean isSelected, boolean hasFocus, int row, int column) {
        
        JLabel label;
        label = (JLabel)super.getTableCellRendererComponent(table, 
                        value, isSelected, hasFocus, row, column);
        label.setHorizontalAlignment(CENTER);
        
        TaskTableModel taskModel = (TaskTableModel)table.getModel();
        Task task = taskModel.getTasks().get(row);
        
        if(task.getDeadline().after(new Date())){
            label.setBackground(Color.GREEN);
        } else {
            label.setBackground(Color.RED);
        }
        
        return label;
        
    }
    
}
