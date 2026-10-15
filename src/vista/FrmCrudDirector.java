package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

import entity.Director;
import entity.TipoDirector;
import model.DirectorModel;
import model.TipoDirectorModel;
import util.ValidateUtil;

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

	
	//Invocar al modelo de Director
	DirectorModel directorModel = new DirectorModel();	
	
	//Posicion del directro seleccionado de la tabla
	int posSeleccionado = -1;
	int idDirectorSeleccionado = -1;
	JCheckBox chkEstado;
	
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
		
		chkEstado = new JCheckBox("Activo");
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

		listar();
		cargarComboTipo();
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
		listar();
	}
	protected void handleBtnBuscarActionPerformed(ActionEvent e) {
		buscar();
	}
	protected void handleBtnRegistarActionPerformed(ActionEvent e) {
		registrar();
	}
	protected void handleBtnActualizarActionPerformed(ActionEvent e) {
		actualizar();
	}
	protected void handleBtnEliminarLgicoActionPerformed(ActionEvent e) {
		eliminarLogico();
	}
	protected void handleBtnEliminarFsicoActionPerformed(ActionEvent e) {
		eliminarFisico();
	}
	protected void handleBtnLimpiarActionPerformed(ActionEvent e) {
		limpiarFormulario();
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
		posSeleccionado = table.getSelectedRow();
		idDirectorSeleccionado = Integer.parseInt(table.getValueAt(posSeleccionado, 0).toString());
		
		System.out.println("Posición seleccionada: ==> " + posSeleccionado);
		System.out.println("ID Director seleccionado: ==> " + idDirectorSeleccionado);
		
		txtCodigo.setText(table.getValueAt(posSeleccionado, 0).toString());
		txtNombre.setText(table.getValueAt(posSeleccionado, 1).toString());
		txtDni.setText(table.getValueAt(posSeleccionado, 2).toString());
		txtEmail.setText(table.getValueAt(posSeleccionado, 3).toString());
		txtFechaNacimiento.setText(table.getValueAt(posSeleccionado, 4).toString());
		String tipo = table.getValueAt(posSeleccionado, 5).toString();
		for (int i = 0; i < cboTipoDirector.getItemCount(); i++) {
			if (cboTipoDirector.getItemAt(i).contains(tipo)) {
				cboTipoDirector.setSelectedIndex(i);
				break;
			}
		}
		chkEstado.setSelected(table.getValueAt(posSeleccionado, 6).toString().equals("Activo"));
	}
	
	public void listar() {
		//1 Invocar al modelo de Director
		List<Director> lista = directorModel.listaTodos();
		
		//2 Limpiar la tabla
		DefaultTableModel tabla = (DefaultTableModel) table.getModel();
		tabla.setRowCount(0);
		
		//3 Recorrer la lista y agregar los datos a la tabla
		for (Director director : lista) {
			Object[] fila = new Object[7];
			fila[0] = director.getIdDirector();
			fila[1] = director.getNombres();
			fila[2] = director.getDni();
			fila[3] = director.getEmail();
			fila[4] = director.getFechaNacimiento();
			fila[5] = director.getTipoDirector().getDescripcion();
			fila[6] = director.getEstado() == 1 ? "Activo" : "Inactivo";
			tabla.addRow(fila);
		}
	}
	
	public void cargarComboTipo() {
		// 1 Invocar al modelo de TipoDirector
		TipoDirectorModel model = new TipoDirectorModel();
		List<TipoDirector> tipos = model.listarTipoDirector();

		// 2 Limpiar el combo
		cboTipoDirector.removeAllItems();

		// 3 Agregar un item por defecto
		cboTipoDirector.addItem("[ Seleccione ]");

		// 4 Recorrer la lista y agregar los datos al combo
		for (TipoDirector tipo : tipos) {
			cboTipoDirector.addItem(tipo.getIdTipoDirector() + " - " + tipo.getDescripcion());
		}
	}

	public void registrar() {
		//1 Capturar los datos del formulario en variables locales de tipo String
		String nombre = txtNombre.getText().trim();
		String dni = txtDni.getText().trim();
		String email = txtEmail.getText().trim();
		String fechaNacimiento = txtFechaNacimiento.getText().trim();
		String tipoSeleccionado = cboTipoDirector.getSelectedItem().toString();
		
		//2 Validar con expresiones regulares
		if (!nombre.matches(ValidateUtil.TEXTO_30)) {
			JOptionPane.showMessageDialog(this, "El nombre es inválido. Debe contener solo letras y espacios, y tener entre 1 y 30 caracteres.");
			return;
		}
		if (!dni.matches(ValidateUtil.DNI)) {
			JOptionPane.showMessageDialog(this, "El DNI es inválido. Debe contener exactamente 8 dígitos.");
			return;
		}
		if (!email.matches(ValidateUtil.EMAIL)) {
            JOptionPane.showMessageDialog(this, "El email es inválido. Debe tener un formato válido .");
            return;
		}
		if (!fechaNacimiento.matches(ValidateUtil.DATE_YYYY_MM_DD)) {
			JOptionPane.showMessageDialog(this,"La fecha de nacimiento es inválida. Debe tener el formato YYYY-MM-DD.");
			return;
		}
		if (cboTipoDirector.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de director.");
			return;
		}
		if (directorModel.existeNombre(nombre)) {
			JOptionPane.showMessageDialog(this, "El bombre "+nombre+" ya existe en la base de datos. Ingrese otro nombre.");
			return;
		}
		if (directorModel.existeDni(dni)) {
			JOptionPane.showMessageDialog(this, "El DNI "+dni+" ya existe en la base de datos. Ingrese otro DNI.");
			return;
		}
		if (directorModel.existeEmail(email)) {
			JOptionPane.showMessageDialog(this, "El email " + email + " ya existe en la base de datos. Ingrese otro email.");
			return;
		}
		//3 se crea un objeto de tipo Director y se le asignan los valores de las variables locales
		TipoDirector objTipoDirector = new TipoDirector();
		objTipoDirector.setIdTipoDirector(Integer.parseInt(tipoSeleccionado.split(" - ")[0]));
		
		Director objDirector = new Director();
		objDirector.setNombres(nombre);
		objDirector.setDni(dni);	
		objDirector.setEmail(email);
		objDirector.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
		objDirector.setTipoDirector(objTipoDirector);
		objDirector.setEstado(1); // Asignar estado activo (1)
		
		//4 se crea un objeto de tipo DirectorModel y se llama al metodo registrarDirector
		DirectorModel objDirectorModel = new DirectorModel();
		int insertados = objDirectorModel.insertaDirector(objDirector);
		
		//5 se muestra un mensaje de confirmacion
		if (insertados > 0) {
			JOptionPane.showMessageDialog(this, "Director registrado correctamente");
			listar(); // Actualizar la tabla después de registrar	
			limpiarFormulario(); // Limpiar el formulario después de registrar
		}
	}
	
	void limpiarFormulario() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtDni.setText("");
		txtEmail.setText("");
		txtFechaNacimiento.setText("");
		cboTipoDirector.setSelectedIndex(0);
	}
	
	void eliminarLogico() {
		if (idDirectorSeleccionado == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un director de la tabla para eliminarlo");
			return;
		}
		List<Director> lstLista =  directorModel.listaPorPk(idDirectorSeleccionado);
		if (!lstLista.isEmpty()) {
			int estado = lstLista.get(0).getEstado() == 1 ? 0 : 1; // Cambiar el estado;
			int actualizados = directorModel.actualizaEstado(idDirectorSeleccionado, estado);
			if (actualizados > 0) {
				limpiarFormulario();
				listar();
				idDirectorSeleccionado = -1; // Reiniciar el ID seleccionado
				JOptionPane.showMessageDialog(this, "Se actualizó el estado del director correctamente");
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo actualizar el estado del director");
			}
		}
		
	}
	
	void eliminarFisico() {
		if (idDirectorSeleccionado == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un director de la tabla para eliminarlo");
			return;
		}
		
		int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el director seleccionado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		if (confirmacion == JOptionPane.YES_OPTION) {
			int eliminados = directorModel.eliminarFisico(idDirectorSeleccionado);
			if (eliminados > 0) {
				limpiarFormulario();
				listar();
				idDirectorSeleccionado = -1; // Reiniciar el ID seleccionado
				JOptionPane.showMessageDialog(this, "Director eliminado correctamente");
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo eliminar el director");
			}
		}
	}
	
	void actualizar() {
        if (idDirectorSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un director de la tabla para actualizarlo");
            return;
        }
        
      //1 Capturar los datos del formulario en variables locales de tipo String
        	String id = txtCodigo.getText().trim();
      		String nombre = txtNombre.getText().trim();
      		String dni = txtDni.getText().trim();
      		String email = txtEmail.getText().trim();
      		String fechaNacimiento = txtFechaNacimiento.getText().trim();
      		String tipoSeleccionado = cboTipoDirector.getSelectedItem().toString();
      		
      		//2 Validar con expresiones regulares
      		if (!nombre.matches(ValidateUtil.TEXTO_30)) {
      			JOptionPane.showMessageDialog(this, "El nombre es inválido. Debe contener solo letras y espacios, y tener entre 1 y 30 caracteres.");
      			return;
      		}
      		if (!dni.matches(ValidateUtil.DNI)) {
      			JOptionPane.showMessageDialog(this, "El DNI es inválido. Debe contener exactamente 8 dígitos.");
      			return;
      		}
      		if (!email.matches(ValidateUtil.EMAIL)) {
                  JOptionPane.showMessageDialog(this, "El email es inválido. Debe tener un formato válido .");
                  return;
      		}
      		if (!fechaNacimiento.matches(ValidateUtil.DATE_YYYY_MM_DD)) {
      			JOptionPane.showMessageDialog(this,"La fecha de nacimiento es inválida. Debe tener el formato YYYY-MM-DD.");
      			return;
      		}
      		if (cboTipoDirector.getSelectedIndex() == 0) {
      			JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de director.");
      			return;
      		}
      		//3 se crea un objeto de tipo Director y se le asignan los valores de las variables locales
      		TipoDirector objTipoDirector = new TipoDirector();
      		objTipoDirector.setIdTipoDirector(Integer.parseInt(tipoSeleccionado.split(" - ")[0]));
      		
      		Director objDirector = new Director();
      		objDirector.setIdDirector(Integer.parseInt(id));
      		objDirector.setNombres(nombre);
      		objDirector.setDni(dni);	
      		objDirector.setEmail(email);
      		objDirector.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
      		objDirector.setTipoDirector(objTipoDirector);
      		objDirector.setEstado(1); // Asignar estado activo (1)
      		
      		//4 se crea un objeto de tipo DirectorModel y se llama al metodo registrarDirector
      		DirectorModel objDirectorModel = new DirectorModel();
      		int actualizados = objDirectorModel.acualizarDirector(objDirector);
      		
      		//5 se muestra un mensaje de confirmacion
      		if (actualizados > 0) {
      			JOptionPane.showMessageDialog(this, "Director actualizado correctamente");
      			listar(); // Actualizar la tabla después de registrar	
      			limpiarFormulario(); // Limpiar el formulario después de registrar
      		}
       
	}
	
	void buscar() {
		String id = txtCodigo.getText().trim();
		List<Director> lista =  directorModel.listaPorPk(Integer.parseInt(id));
		
		
		//2 Limpiar la tabla
		DefaultTableModel tabla = (DefaultTableModel) table.getModel();
		tabla.setRowCount(0);
				
		//3 Recorrer la lista y agregar los datos a la tabla
		for (Director director : lista) {
					Object[] fila = new Object[7];
					fila[0] = director.getIdDirector();
					fila[1] = director.getNombres();
					fila[2] = director.getDni();
					fila[3] = director.getEmail();
					fila[4] = director.getFechaNacimiento();
					fila[5] = director.getTipoDirector().getDescripcion();
					fila[6] = director.getEstado() == 1 ? "Activo" : "Inactivo";
					tabla.addRow(fila);
		}
		
	}
}


