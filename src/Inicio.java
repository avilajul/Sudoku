import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: ABRIL 27 DE 2014
 * DESCRIPCION: SUDOKU
 * */


public class Inicio extends JFrame implements ActionListener
{

	JButton jugar;
	JLabel img;
	ImageIcon imagen;
	JLabel titulo;
	JLabel opcion1;
	JLabel opcion2;
	JLabel opcion3;
	JRadioButton rb1;
	JRadioButton rb2;
	JRadioButton rb3;
	ButtonGroup bgroup;
	Container contenedor = getContentPane();

	JPanel panelMenu;
	
	String ruta = "src/img/";
	
	
	public Inicio()  
	{
		contenedor.setLayout(null);		
		setSize(410,430);
		setTitle("Sudoku");
		setIconImage(new ImageIcon(ruta+"imgSudoku2.png").getImage());
		setResizable(false);
		setLocationRelativeTo(null);
		
		componentes();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void componentes()
	{		
		Color colorFondo = new Color(165,248,186);
		Color colorLetra = new Color(54,68,75);
		panelMenu = new JPanel();
		panelMenu.setLayout(null);
		panelMenu.setSize(400, 400);
		panelMenu.setBackground(colorFondo);
		
		imagen 	= new ImageIcon(ruta+"imgSudoku2.png");
		img 	= new JLabel(imagen);
		titulo 	= new JLabel("Menu");
		opcion1 = new JLabel("Facil");
		opcion2 = new JLabel("Medio");
		opcion3 = new JLabel("Duro");
		jugar 	= new JButton("Jugar");
		rb1		= new JRadioButton();
		rb2		= new JRadioButton();
		rb3		= new JRadioButton();
		bgroup	= new ButtonGroup();
		
		bgroup.add(rb1);
		bgroup.add(rb2);
		bgroup.add(rb3);
		
		jugar.addActionListener(this);
		
		titulo.setBounds(160, 10, 100, 30);
		titulo.setFont(new java.awt.Font("Tahoma", 1, 28)); 
		titulo.setForeground(colorLetra);
		panelMenu.add(titulo);
		
		img.setBounds(100, 50, 190, 190);
		panelMenu.add(img);
		
		opcion1.setBounds(130, 260, 60, 20);
		opcion1.setFont(new java.awt.Font("Tahoma", 1, 22)); 
		opcion1.setForeground(colorLetra);
		rb1.setBounds(220,260,20,20);
		rb1.setBackground(null);
		panelMenu.add(opcion1);
		
		
		opcion2.setBounds(130, 290, 80, 20);
		opcion2.setFont(new java.awt.Font("Tahoma", 1, 22)); 
		opcion2.setForeground(colorLetra);
		rb2.setBounds(220,290,20,20);
		rb2.setBackground(null);
		panelMenu.add(opcion2);		
		
		opcion3.setBounds(130, 320, 80, 20);
		opcion3.setFont(new java.awt.Font("Tahoma", 1, 22)); 
		opcion3.setForeground(colorLetra);
		rb3.setBounds(220,320,20,20);
		rb3.setBackground(null);
		panelMenu.add(opcion3);
		
		jugar.setBounds(150, 360, 90, 20);
		panelMenu.add(jugar);
		panelMenu.add(rb1);
		panelMenu.add(rb2);
		panelMenu.add(rb3);		
		
		contenedor.add(panelMenu);		
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		String archivo="";
		
		if(ae.getSource() == jugar)
		{
			if (rb1.isSelected()==true)
				archivo="sudoku1.txt";
			if (rb2.isSelected()==true)
				archivo="sudoku2.txt";
			if (rb3.isSelected()==true)
				archivo="sudoku3.txt";	
			if(archivo!="")
			{
				this.setVisible(false);
				try
				{
					VentanaSudoku frame = new VentanaSudoku(archivo);
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}				
			}
			if(archivo=="")
				JOptionPane.showMessageDialog(null, "Favor Seleccione una opcion ");
		}
	}
}
