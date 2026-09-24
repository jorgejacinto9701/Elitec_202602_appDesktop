package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entity.Concurso;
import model.ConcursoModel;

public class FrmRegistraConcurso extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JLabel lblFechainicio;
	private JTextField txtFechaInicio;
	private JLabel lblFechaFin;
	private JTextField txtFechaFin;
	private JButton btnRegistrar;
	private JButton btnLimpiar;
	private JCheckBox chkEstado; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistraConcurso frame = new FrmRegistraConcurso();
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
	public FrmRegistraConcurso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de Concurso");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(27, 26, 551, 36);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(66, 93, 130, 22);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(221, 94, 277, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblFechainicio = new JLabel("FechaI nicio");
		lblFechainicio.setBounds(66, 137, 130, 22);
		contentPane.add(lblFechainicio);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setColumns(10);
		txtFechaInicio.setBounds(221, 138, 277, 20);
		contentPane.add(txtFechaInicio);
		
		lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(66, 182, 130, 22);
		contentPane.add(lblFechaFin);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setColumns(10);
		txtFechaFin.setBounds(221, 183, 277, 20);
		contentPane.add(txtFechaFin);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setSelected(true);
		chkEstado.setBounds(217, 230, 97, 23);
		contentPane.add(chkEstado);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(302, 295, 130, 43);
		contentPane.add(btnLimpiar);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon("icons/001-agregar.png"));
		btnRegistrar.setBounds(134, 295, 158, 43);
		contentPane.add(btnRegistrar);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			handleBtnLimpiarActionPerformed(e);
		}
		if (e.getSource() == btnRegistrar) {
			handleBtnRegistrarActionPerformed(e);
		}
	}
	protected void handleBtnRegistrarActionPerformed(ActionEvent e) {
		//1. Capturar los datos del formulario en variables locales de tipo String
		String nombre = txtNombre.getText().trim();
		String fechaInicio = txtFechaInicio.getText().trim();
		String fechaFin = txtFechaFin.getText().trim();
		boolean esActivo  = chkEstado.isSelected();
		
		//2 Crea un objeto de tipo Concurso
		Concurso objConcurso = new Concurso();
		objConcurso.setNombre(nombre);
		objConcurso.setFechaInicio(LocalDate.parse(fechaInicio));
		objConcurso.setFechaFin(LocalDate.parse(fechaFin));
		objConcurso.setEstado(esActivo ? "1" : "0");
		
		//3. Crea un objeto de tipo ConcursoModel
		ConcursoModel objConcursoModel = new ConcursoModel();
		int insertados = objConcursoModel.insertaConcurso(objConcurso);

		//4 
		if (insertados > 0) {
           JOptionPane.showMessageDialog(this, "Concurso registrado correctamente");
		}
		
	}
	protected void handleBtnLimpiarActionPerformed(ActionEvent e) {
		txtNombre.setText("");
		txtFechaInicio.setText("");
		txtFechaFin.setText("");
		chkEstado.setSelected(true);
	
	}
}
