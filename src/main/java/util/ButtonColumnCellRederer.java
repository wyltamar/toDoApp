
package util;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author wyltamar
 */
public class ButtonColumnCellRederer extends DefaultTableCellRenderer {
    
    private String buttonType;
    
    
    public ButtonColumnCellRederer(String buttonType){
        this.buttonType = buttonType;
    }
    
    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    
    
    @Override
     public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column){
         
        JLabel label;
        label = (JLabel)super.getTableCellRendererComponent(table, 
                        value, isSelected, hasFocus, row, column);
        
        label.setHorizontalAlignment(CENTER); 
        
        label.setIcon(new ImageIcon(getClass().getResource("/"+getButtonType()+".png")));
         
        return label;
         
     }
    
}
