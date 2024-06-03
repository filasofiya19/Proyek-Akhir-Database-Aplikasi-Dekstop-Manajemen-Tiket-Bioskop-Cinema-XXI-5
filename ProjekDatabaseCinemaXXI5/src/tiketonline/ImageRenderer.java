/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiketonline;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author fila sofiyati
 */
public class ImageRenderer extends JLabel implements TableCellRenderer {
    public ImageRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null) {
            ImageIcon imageIcon = new ImageIcon((byte[]) value);
            setIcon(imageIcon);
        } else {
            setIcon(null);
        }
        
        return this;
    }
}
