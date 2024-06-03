/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tiketonline;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
/**
 *
 * @author fila sofiyati
 */
public interface Printable {
    public static final int PAGE_EXISTS = 0;
       public static final int NO_SUCH_PAGE= 1;
       public int print(Graphics grphcs, PageFormat pf, int i) throws PrinterException;
} 
