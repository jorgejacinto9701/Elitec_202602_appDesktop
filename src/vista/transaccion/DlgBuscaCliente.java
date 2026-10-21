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

import entity.Cliente;
import model.ClienteModel;

public class DlgBuscaCliente extends JDialog implements KeyListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtFiltroNombre;
    private JTable table;
    private Cliente clienteSeleccionado = null;
    
    ClienteModel clienteModel = new ClienteModel();

    public DlgBuscaCliente(JFrame parent) {
        super(parent, "Buscar Cliente", true);
        setBounds(100, 100, 550, 400);
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
        scrollPane.setBounds(30, 75, 475, 260);
        getContentPane().add(scrollPane);
        
        table = new JTable();
        table.addMouseListener(this);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Código", "Nombre", "DNI", "Estado" }
        ));
        scrollPane.setViewportView(table);
        
        listar("");
    }

    public void listar(String filtro) {
        List<Cliente> lista = clienteModel.listaClientePorNombreLike(filtro);
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        for (Cliente c : lista) {
            Object[] fila = new Object[4];
            fila[0] = c.getIdCliente();
            fila[1] = c.getNombre();
            fila[2] = c.getDni();
            fila[3] = c.getEstado() == 1 ? "Activo" : "Inactivo";
            modelo.addRow(fila);
        }
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
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
                String dni = table.getValueAt(fila, 2).toString();
                String estadoStr = table.getValueAt(fila, 3).toString();
                
                clienteSeleccionado = new Cliente();
                clienteSeleccionado.setIdCliente(id);
                clienteSeleccionado.setNombre(nombre);
                clienteSeleccionado.setDni(dni);
                clienteSeleccionado.setEstado(estadoStr.equals("Activo") ? 1 : 0);
                
                dispose();
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}