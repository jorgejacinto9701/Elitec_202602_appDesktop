package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class FrmCrudDirector extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtEmail;
	private JTextField txtFechaNacimiento;
	private JButton btnListarTodos;
	private JButton btnBuscar;
	private JButton btnRegistar;
	private JButton btnActualizar;
	private JButton btnEliminarLgico;
	private JButton btnEliminarFsico;
	private JButton btnLimpiar;
	private JTable table;
	private JComboBox<String> cboTipoDirector;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudDirector frame = new FrmCrudDirector();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudDirector() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1520, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Mantenimiento de Director");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setBounds(58, 11, 1014, 47);
		contentPane.add(lblTitulo);
		
		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(37, 105, 103, 14);
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(183, 102, 151, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(37, 133, 103, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(183, 130, 206, 20);
		contentPane.add(txtNombre);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(37, 161, 103, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(183, 158, 151, 20);
		contentPane.add(txtDni);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(37, 189, 103, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(183, 186, 206, 20);
		contentPane.add(txtEmail);
		
		JLabel lblFechanacimiento = new JLabel("FechaNacimiento");
		lblFechanacimiento.setBounds(37, 217, 103, 14);
		contentPane.add(lblFechanacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(183, 214, 151, 20);
		contentPane.add(txtFechaNacimiento);
		
		cboTipoDirector = new JComboBox<String>();
		cboTipoDirector.setBounds(183, 245, 206, 22);
		contentPane.add(cboTipoDirector);
		
		JLabel lblTipoDirector = new JLabel("Tipo Director");
		lblTipoDirector.setBounds(37, 249, 103, 14);
		contentPane.add(lblTipoDirector);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(37, 281, 103, 14);
		contentPane.add(lblEstado);
		
		JCheckBox chkEstado = new JCheckBox("Activo");
		chkEstado.setBounds(182, 277, 97, 23);
		contentPane.add(chkEstado);
		
		btnListarTodos = new JButton("Listar Todos");
		btnListarTodos.addActionListener(this);
		btnListarTodos.setBounds(431, 101, 170, 23);
		contentPane.add(btnListarTodos);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(431, 129, 170, 23);
		contentPane.add(btnBuscar);
		
		btnRegistar = new JButton("Registrar");
		btnRegistar.addActionListener(this);
		btnRegistar.setBounds(431, 161, 170, 23);
		contentPane.add(btnRegistar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(431, 189, 170, 23);
		contentPane.add(btnActualizar);
		
		btnEliminarLgico = new JButton("Eliminar Lógico");
		btnEliminarLgico.addActionListener(this);
		btnEliminarLgico.setBounds(431, 217, 170, 23);
		contentPane.add(btnEliminarLgico);
		
		btnEliminarFsico = new JButton("Eliminar Físico");
		btnEliminarFsico.addActionListener(this);
		btnEliminarFsico.setBounds(431, 245, 170, 23);
		contentPane.add(btnEliminarFsico);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(431, 272, 170, 23);
		contentPane.add(btnLimpiar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(661, 90, 820, 348);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombres", "DNI", "Email", "Fecha Nacimiento", "Tipo", "Estado"
			}
		));
		scrollPane.setViewportView(table);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			handleBtnLimpiarActionPerformed(e);
		}
		if (e.getSource() == btnEliminarFsico) {
			handleBtnEliminarFsicoActionPerformed(e);
		}
		if (e.getSource() == btnEliminarLgico) {
			handleBtnEliminarLgicoActionPerformed(e);
		}
		if (e.getSource() == btnActualizar) {
			handleBtnActualizarActionPerformed(e);
		}
		if (e.getSource() == btnRegistar) {
			handleBtnRegistarActionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			handleBtnBuscarActionPerformed(e);
		}
		if (e.getSource() == btnListarTodos) {
			handleBtnListarTodosActionPerformed(e);
		}
	}
	protected void handleBtnListarTodosActionPerformed(ActionEvent e) {
	}
	protected void handleBtnBuscarActionPerformed(ActionEvent e) {
	}
	protected void handleBtnRegistarActionPerformed(ActionEvent e) {
	}
	protected void handleBtnActualizarActionPerformed(ActionEvent e) {
	}
	protected void handleBtnEliminarLgicoActionPerformed(ActionEvent e) {
	}
	protected void handleBtnEliminarFsicoActionPerformed(ActionEvent e) {
	}
	protected void handleBtnLimpiarActionPerformed(ActionEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			handleTableMouseClicked(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void handleTableMouseClicked(MouseEvent e) {
	}
}
