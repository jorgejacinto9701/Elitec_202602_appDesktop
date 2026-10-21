package vista.transaccion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entity.Boleta;
import entity.Cliente;
import entity.DetalleBoleta;
import entity.Producto;
import model.BoletaModel;

public class FrmRegistraBoleta extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombreCliente;
    private JTextField txtDniCliente;
    private JButton btnBuscaCliente;
    private JButton btnBuscaProducto;
    private JButton btnRegistraBoleta;
    private JTable tableDetalle;

    private Cliente clienteSeleccionado = null;
    private List<DetalleBoleta> listaDetalle = new ArrayList<>();
    
    BoletaModel boletaModel = new BoletaModel();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmRegistraBoleta frame = new FrmRegistraBoleta();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmRegistraBoleta() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Registro de Boleta");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitulo.setBounds(30, 20, 720, 35);
        contentPane.add(lblTitulo);
        
        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(30, 80, 80, 20);
        contentPane.add(lblCliente);
        
        btnBuscaCliente = new JButton("Buscar");
        btnBuscaCliente.addActionListener(this);
        btnBuscaCliente.setBounds(120, 80, 100, 25);
        contentPane.add(btnBuscaCliente);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 120, 80, 20);
        contentPane.add(lblNombre);
        
        txtNombreCliente = new JTextField();
        txtNombreCliente.setEditable(false);
        txtNombreCliente.setBounds(120, 120, 250, 22);
        contentPane.add(txtNombreCliente);
        txtNombreCliente.setColumns(10);
        
        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(400, 120, 50, 20);
        contentPane.add(lblDni);
        
        txtDniCliente = new JTextField();
        txtDniCliente.setEditable(false);
        txtDniCliente.setBounds(460, 120, 150, 22);
        contentPane.add(txtDniCliente);
        txtDniCliente.setColumns(10);
        
        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(30, 170, 80, 20);
        contentPane.add(lblProducto);
        
        btnBuscaProducto = new JButton("Buscar");
        btnBuscaProducto.addActionListener(this);
        btnBuscaProducto.setBounds(120, 170, 100, 25);
        contentPane.add(btnBuscaProducto);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 210, 720, 250);
        contentPane.add(scrollPane);
        
        tableDetalle = new JTable();
        tableDetalle.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Código", "Nombre", "Marca", "Precio", "Cantidad", "Subtotal" }
        ));
        scrollPane.setViewportView(tableDetalle);
        
        btnRegistraBoleta = new JButton("Registra Boleta");
        btnRegistraBoleta.addActionListener(this);
        btnRegistraBoleta.setBounds(310, 485, 180, 35);
        contentPane.add(btnRegistraBoleta);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscaCliente) {
            handleBtnBuscaCliente();
        } else if (e.getSource() == btnBuscaProducto) {
            handleBtnBuscaProducto();
        } else if (e.getSource() == btnRegistraBoleta) {
            handleBtnRegistraBoleta();
        }
    }

    private void handleBtnBuscaCliente() {
        DlgBuscaCliente dlg = new DlgBuscaCliente(this);
        dlg.setVisible(true);
        clienteSeleccionado = dlg.getClienteSeleccionado();
        if (clienteSeleccionado != null) {
            txtNombreCliente.setText(clienteSeleccionado.getNombre());
            txtDniCliente.setText(clienteSeleccionado.getDni());
        }
    }

    private void handleBtnBuscaProducto() {
        DlgBuscaProducto dlg = new DlgBuscaProducto(this);
        dlg.setVisible(true);
        Producto productoSeleccionado = dlg.getProductoSeleccionado();
        if (productoSeleccionado != null) {
            String cantStr = JOptionPane.showInputDialog(this, "Ingrese cantidad para " + productoSeleccionado.getNombre() + ":");
            if (cantStr != null && !cantStr.trim().isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(cantStr.trim());
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.");
                        return;
                    }
                    if (cantidad > productoSeleccionado.getStock()) {
                        JOptionPane.showMessageDialog(this, "Stock insuficiente. Stock disponible: " + productoSeleccionado.getStock());
                        return;
                    }
                    
                    DetalleBoleta detalle = new DetalleBoleta();
                    detalle.setProducto(productoSeleccionado);
                    detalle.setCantidad(cantidad);
                    detalle.setPrecio(productoSeleccionado.getPrecio());
                    
                    listaDetalle.add(detalle);
                    listarDetalle();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un número válido para la cantidad.");
                }
            }
        }
    }

    private void listarDetalle() {
        DefaultTableModel modelo = (DefaultTableModel) tableDetalle.getModel();
        modelo.setRowCount(0);
        for (DetalleBoleta d : listaDetalle) {
            Object[] fila = new Object[6];
            fila[0] = d.getProducto().getIdProducto();
            fila[1] = d.getProducto().getNombre();
            fila[2] = d.getProducto().getMarca();
            fila[3] = d.getPrecio();
            fila[4] = d.getCantidad();
            fila[5] = d.getPrecio() * d.getCantidad();
            modelo.addRow(fila);
        }
    }

    private void handleBtnRegistraBoleta() {
        if (clienteSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente.");
            return;
        }
        if (listaDetalle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe agregar al menos un producto al detalle de la boleta.");
            return;
        }
        
        Boleta boleta = new Boleta();
        boleta.setCliente(clienteSeleccionado);
        boleta.setFechaEmision(LocalDate.now());
        
        int resultado = boletaModel.inserta(boleta, listaDetalle);
        if (resultado > 0) {
            JOptionPane.showMessageDialog(this, "Boleta registrada con éxito.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la boleta.");
        }
    }

    private void limpiar() {
        clienteSeleccionado = null;
        txtNombreCliente.setText("");
        txtDniCliente.setText("");
        listaDetalle.clear();
        listarDetalle();
    }
}