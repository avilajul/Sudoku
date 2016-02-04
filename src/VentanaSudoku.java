import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Stack;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: ABRIL 27 DE 2014
 * DESCRIPCION: SUDOKU
 * */

public class VentanaSudoku extends JFrame implements ActionListener
{
	
	private JPanel contentPane, contentMenu;
	private JButton btnDeshacer, btnRehacer, btnHistorial, btnSugerencia;
	Stack<CustomJTextField> pilaDeshacer;
	Stack<CustomJTextField> pilaRehacer;
	Stack<CustomJTextField> pilaHistorial;
	Container contenedor = getContentPane();
	ImageIcon iconoDeshacer, iconoRehacer, iconoHistorial, iconoSugerencia;
	JLabel jlDeshacer, jlRehacer, jlHistorial, jlSugerencia;
	JScrollPane scrollPane;
	
	int contadorFila=0;
	
	//------tabla
	JTable tabla;
	DefaultTableModel modelo;
	String titulos[] = {"Fila","Columna","Numero","Accion"};
	
	//caja de texto del sudoku
	CustomJTextField currentField;
	String numero;
	
	String ruta = "src/img/";	

	public VentanaSudoku(String archivo) throws IOException 
	{
		contenedor.setLayout(null);
		
		// Usando panel personalizado del tablero del sudoku con numero de columnas y filas
		contentPane = new CustomJPanel(9, 9,archivo);
		contentPane.setBounds(420, 10, 500, 500);		
		contentPane.setBorder(BorderFactory.createTitledBorder("Tablero"));
		((CustomJPanel) contentPane).pintarRegion();

		
		//panel Menu
		contentMenu = new JPanel();
		contentMenu.setLayout(null);
		contentMenu.setBorder(BorderFactory.createTitledBorder("Menu"));		
		contentMenu.setBounds(10, 10, 405, 500);		
		//iconos
		iconoDeshacer = new ImageIcon(ruta+"deshacerUndo.png");
		iconoRehacer = new ImageIcon(ruta+"rehacerRedo.png");
		iconoHistorial = new ImageIcon(ruta+"historia.png");
		iconoSugerencia = new ImageIcon(ruta+"sugerencia.png");
		
		jlDeshacer = new JLabel("Deshacer");
		jlRehacer = new JLabel("Rehacer");
		jlHistorial = new JLabel("Ver Jugadas");
		jlSugerencia = new JLabel("Jugada Sugerida");
		
		//botones
		btnDeshacer = new JButton(iconoDeshacer);
		btnRehacer = new JButton(iconoRehacer);
		btnHistorial = new JButton(iconoHistorial);
		btnSugerencia = new JButton(iconoSugerencia);
		
		btnDeshacer.addActionListener(this);
		btnRehacer.addActionListener(this);
		btnHistorial.addActionListener(this);
		btnSugerencia.addActionListener(this);
		
		jlDeshacer.setBounds(10, 30, 70, 10);
		jlRehacer.setBounds(90, 30, 70, 10);
		jlHistorial.setBounds(170, 30, 100, 15);
		jlSugerencia.setBounds(280, 30, 125, 15);
		
		btnDeshacer.setBounds(15,50,40,40);
		btnRehacer.setBounds(90,50,40,40);
		btnHistorial.setBounds(185,50,40,40);
		btnSugerencia.setBounds(305,50,40,40);
		
		contentMenu.add(jlDeshacer);
		contentMenu.add(jlRehacer);
		contentMenu.add(jlHistorial);
		contentMenu.add(jlSugerencia);
		contentMenu.add(btnDeshacer);
		contentMenu.add(btnRehacer);
		contentMenu.add(btnHistorial);
		contentMenu.add(btnSugerencia);
		
		
		//--------------------------------------------------------------------------------------
		//se instancia las pilas deshacer,rehacer e historial
		pilaDeshacer = new Stack();					
		pilaRehacer = new Stack();
		pilaHistorial = new Stack();
		
		//---------------------datos historial----------------------
		
		
		String informacion[][]= new String[0][3];
		modelo = new DefaultTableModel(informacion,titulos);		
		tabla = new JTable(modelo);
		
		

		
		
		scrollPane = new JScrollPane(tabla);
		contentMenu.add(scrollPane);
		scrollPane.setBounds(8, 110, 385, 385);
		scrollPane.setVisible(false);
		
		//tabla.setBounds(10, 110, 380, 350);
		//contentMenu.add(tabla);
		//--------------------------------------------------------------
		
		contenedor.add(contentMenu);		
		contenedor.add(contentPane);
		
		setSize(935, 555);
		setTitle("Sudoku");
		setIconImage(new ImageIcon(ruta+"imgSudoku2.png").getImage());
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Dentro del Keylistener no podre acceder al frame
		// ni a sus funciones, en esta ocasion quiero cambiar el
		// titulo de la ventana asi que utilizo "self" como
		// una referencia a la ventana principal.
		final JFrame self = this;
		// Este keyListener manejara los eventos de teclado de TODOS los
		// JTextField incluidos en el gridLayout
		
		((CustomJPanel)contentPane).addDefaultKeyListener(new java.awt.event.KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e){}

			@Override
			public void keyReleased(KeyEvent e) 
			{
				// El componente devuelto debe ser convertido a mi CustomJTextField
				// para poder acceder a las funciones de obtener columna y fila
											
				currentField = (CustomJTextField)e.getComponent();				
				numero = currentField.getText();					
				currentField.setText("");
				
				try
				{
					if (!(e.getKeyCode()==KeyEvent.VK_ENTER))
					{
						currentField.setNumero(Integer.parseInt(numero));
						
						if(((CustomJPanel) contentPane).comprobar_valor(numero))
						{				
							if (((CustomJPanel) contentPane).existe_fila(Integer.valueOf(numero),currentField.getRow())
									|| ((CustomJPanel) contentPane).existe_columna(Integer.valueOf(numero),currentField.getColumn())
									|| ((CustomJPanel) contentPane).existe_caja(Integer.valueOf(numero),currentField.getRow(),currentField.getColumn())								) 
							{   
								JOptionPane.showMessageDialog(null,"el numero no fue introducido","Error",JOptionPane.ERROR_MESSAGE);
								currentField.setText("");
							}
							else
						    {
								currentField.setText(numero);
						        pilaDeshacer.push(currentField);	
						        pilaHistorial.push(currentField);
						        
						        historial(currentField);
						       ((CustomJPanel) contentPane).terminado();
						    }
						}
						else
						{							
							currentField.setText("");
						}
					}					
			}
			catch (IllegalArgumentException nfe)
			{
				JOptionPane.showMessageDialog(null,"este valor no es valido","Error",JOptionPane.ERROR_MESSAGE);				
			}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e)
	 {
		if (e.getSource()==btnDeshacer)
		{	
			if(!pilaDeshacer.isEmpty())
			{	
				modelo.insertRow(contadorFila, new Object[]{});                        //se inserta un dato cada vez que es necesario
				modelo.setValueAt((pilaDeshacer.peek()).getRow(), contadorFila, 0);
				modelo.setValueAt((pilaDeshacer.peek()).getColumn(), contadorFila, 1);
				modelo.setValueAt((pilaDeshacer.peek()).getNumero(), contadorFila, 2);	
				modelo.setValueAt("Deshacer", contadorFila, 3);
				contadorFila++;
				
				pilaRehacer.push(pilaDeshacer.pop());	
				(pilaRehacer.peek()).setText("");
			}
		}
		
		if (e.getSource()==btnRehacer)
		{			
			if(!pilaRehacer.isEmpty())
			{				
				modelo.insertRow(contadorFila, new Object[]{});						//se inserta un dato cada vez que es necesario
				modelo.setValueAt((pilaRehacer.peek()).getRow(), contadorFila, 0);
				modelo.setValueAt((pilaRehacer.peek()).getColumn(), contadorFila, 1);
				modelo.setValueAt((pilaRehacer.peek()).getNumero(), contadorFila, 2);	
				modelo.setValueAt("Rehacer", contadorFila, 3);
				contadorFila++;
				
				pilaDeshacer.push(pilaRehacer.pop());				
				(pilaDeshacer.peek()).setText(Integer.toString((pilaDeshacer.peek()).getNumero()));
			}
		}
		
		if(e.getSource() == btnHistorial)
		{
			if(!(scrollPane.isVisible()))
			{
				scrollPane.setVisible(true);
			}
			else
			{
				scrollPane.setVisible(false);
			}
		}
	}
	
	public void historial(CustomJTextField campo)
	{
		 String informacion[][] = new String[pilaHistorial.size()][4];
		 int fila,columna;
		 String accion = "Nueva";
		 boolean booleanAccion=false;
		 
		 modelo.insertRow(contadorFila, new Object[]{});
		 
		 modelo.setValueAt(campo.getRow(), contadorFila, 0);
		 modelo.setValueAt(campo.getColumn(), contadorFila, 1);
		 modelo.setValueAt(campo.getNumero(), contadorFila, 2);				 
		 modelo.setValueAt(accion, contadorFila, 3);
		 contadorFila++;
	}
}