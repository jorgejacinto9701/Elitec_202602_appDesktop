package vista.transaccion;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Producto;
import model.ProductoModel;

public class DlgBuscaProducto extends JDialog implements KeyListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtFiltroNombre;
    private JTable table;
    private Producto productoSeleccionado = null;
    
    ProductoModel productoModel = new ProductoModel();

    public DlgBuscaProducto(JFrame parent) {
        super(parent, "Buscar Producto", true);
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 60, 20);
        getContentPane().add(lblNombre);
        
        txtFiltroNombre = new JTextField();
        txtFiltroNombre.setBounds(100, 30, 250, 22);
        txtFiltroNombre.addKeyListener(this);
        getContentPane().add(txtFiltroNombre);
        txtFiltroNombre.setColumns(10);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 75, 525, 260);
        getContentPane().add(scrollPane);
        
        table = new JTable();
        table.addMouseListener(this);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Código", "Nombre", "Marca", "Precio", "Stock" }
        ));
        scrollPane.setViewportView(table);
        
        listar("");
    }

    public void listar(String filtro) {
        List<Producto> lista = productoModel.listaProductoPorNombreLike(filtro);
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        for (Producto p : lista) {
            Object[] fila = new Object[5];
            fila[0] = p.getIdProducto();
            fila[1] = p.getNombre();
            fila[2] = p.getMarca();
            fila[3] = p.getPrecio();
            fila[4] = p.getStock();
            modelo.addRow(fila);
        }
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == txtFiltroNombre) {
            listar(txtFiltroNombre.getText().trim());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table) {
            int fila = table.getSelectedRow();
            if (fila >= 0) {
                int id = Integer.parseInt(table.getValueAt(fila, 0).toString());
                String nombre = table.getValueAt(fila, 1).toString();
                String marca = table.getValueAt(fila, 2).toString();
                double precio = Double.parseDouble(table.getValueAt(fila, 3).toString());
                int stock = Integer.parseInt(table.getValueAt(fila, 4).toString());
                
                productoSeleccionado = new Producto();
                productoSeleccionado.setIdProducto(id);
                productoSeleccionado.setNombre(nombre);
                productoSeleccionado.setMarca(marca);
                productoSeleccionado.setPrecio(precio);
                productoSeleccionado.setStock(stock);
                
                dispose();
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}