package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entity.Director;
import entity.TipoDirector;
import model.DirectorModel;
import model.TipoDirectorModel;
import util.ValidateUtil;

public class FrmRegistraDirector extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDNI;
	private JTextField txtEmmail;
	private JTextField txtFechaNacimiento;
	private JComboBox<String> cboTipo;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistraDirector frame = new FrmRegistraDirector();
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
	public FrmRegistraDirector() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro Director");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(40, 25, 642, 57);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(136, 152, 118, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(316, 149, 206, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(136, 193, 118, 14);
		contentPane.add(lblDni);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(316, 190, 206, 20);
		contentPane.add(txtDNI);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(136, 234, 118, 14);
		contentPane.add(lblEmail);
		
		txtEmmail = new JTextField();
		txtEmmail.setColumns(10);
		txtEmmail.setBounds(316, 231, 206, 20);
		contentPane.add(txtEmmail);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(136, 278, 118, 14);
		contentPane.add(lblFechaNacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(316, 275, 206, 20);
		contentPane.add(txtFechaNacimiento);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(136, 323, 118, 14);
		contentPane.add(lblTipo);
		
		cboTipo = new JComboBox<String>();
		cboTipo.setBounds(316, 319, 280, 22);
		contentPane.add(cboTipo);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(298, 394, 139, 23);
		contentPane.add(btnRegistrar);
		
		cargarComboTipo();

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			handleBtnRegistrarActionPerformed(e);
		}
	}
	protected void handleBtnRegistrarActionPerformed(ActionEvent e) {
		//1 Capturar los datos del formulario en variables locales de tipo String
		String nombre = txtNombre.getText().trim();
		String dni = txtDNI.getText().trim();
		String email = txtEmmail.getText().trim();
		String fechaNacimiento = txtFechaNacimiento.getText().trim();
		String tipoSeleccionado = cboTipo.getSelectedItem().toString();
		
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
		if (cboTipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de director.");
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
		
		//4 se crea un objeto de tipo DirectorModel y se llama al metodo registrarDirector
		DirectorModel objDirectorModel = new DirectorModel();
		int insertados = objDirectorModel.insertaDirector(objDirector);
		
		//5 se muestra un mensaje de confirmacion
		if (insertados > 0) {
	           JOptionPane.showMessageDialog(this, "Director registrado correctamente");
		}
		
	}
	
	public void cargarComboTipo() {
		TipoDirectorModel model = new TipoDirectorModel();
		List<TipoDirector> tipos = model.listarTipoDirector();
		cboTipo.addItem("[ Seleccione un tipo] ");
		for (TipoDirector tipo : tipos) {
			cboTipo.addItem(tipo.getIdTipoDirector() + " - " + tipo.getDescripcion());
		}
		
	}
}
