import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: ABRIL 27 DE 2014
 * DESCRIPCION: SUDOKU
 * */

	class CustomJPanel extends JPanel
	{
		private JTextField textFields[][];
		
		private int rows;
		private int columns;
		Color colorFondo = new Color(165,248,186);
		Color colorFondo2 = new Color(16,29,127);	
		Color colorFondo3 = new Color(195,195,195);
		
		public JTextField[][] getTextFields() 
		{
			// Devuelve los JTextFields
			return textFields;
		}
		
		
		public CustomJPanel(int rows, int columns, String archivo) throws IOException 
		{
			super();
			this.rows = rows;
			this.columns = columns;
			
			BufferedReader entrada = new BufferedReader(new FileReader(archivo)); 
			String renglon;

			this.setLayout(new GridLayout(rows, columns, 0, 0));
			
			// Inicializar arreglo multidimensional
			textFields = new JTextField[rows][];
			for(int i=0;i<rows;i++)
			{
				textFields[i] = new JTextField[columns];				
			}
			
			// Inicializar JtextFields y agregarlos al panel
			for(int i=0;i<rows;i++) 
			{
				renglon = entrada.readLine();
				
				for(int j=0;j<columns;j++) 
				{
					// Cada componente conoce su ubicacion en el arreglo
					textFields[i][j] = new CustomJTextField(i, j);
					textFields[i][j].setFont(new java.awt.Font("Tahoma", 0, 20));
					textFields[i][j].setHorizontalAlignment(SwingConstants.CENTER);
					
					if (renglon.charAt(j)!='-')
					{
						textFields[i][j].setText(""+renglon.charAt(j));		
						textFields[i][j].setFont(new java.awt.Font("Tahoma", 1, 20));
						textFields[i][j].setEnabled(false);
						textFields[i][j].setDisabledTextColor(colorFondo2);						
					}
					this.add(textFields[i][j]);
				}
			}
		}
		
		public void addDefaultKeyListener(java.awt.event.KeyListener listener)
		{
			for(int i=0;i<this.rows;i++) 
			{
				for(int j=0;j<this.columns;j++) 
				{
					textFields[i][j].addKeyListener(listener);
				}
			}
		}	
		
	    //METODO PARA COMPROBAR FILAS.

	    public boolean existe_fila(int numero, int fila) 
	    {
	        boolean resultado = false;
	       // int a = matriz[0][2];
	        for (int i = 0; i < textFields.length; i++) 
	        {
	            if (numero == 0) 
	            {
	            } 
	            else 
	            {
	            	if ((textFields[fila][i].getText()).equals( Integer.toString(numero))) 
	                {	                	
	                    resultado = true;
	                    break;
	                }
	            }
	        }
	        return resultado;
	    }
	    
	  //METODO PARA COMPROBAR COLUMNAS.
	    public boolean existe_columna(int numero, int columna) 
	    {
	        boolean resultado = false;
	        //int a = matriz[7][0];	        
	        for (int i = 0; i < textFields.length; i++) 
	        {
	            if ((textFields[i][columna].getText()).equals(Integer.toString(numero))) 
	            {
	                resultado = true;
	                break;
	            }
	        }
	        return resultado;
	    }
	    
	    // METODO PARA COMPROBAR LOS VALORES.

	    public boolean comprobar_valor(String valor1)
	    {
	    	boolean comprobar = false;

	        if (String.valueOf(valor1).equalsIgnoreCase("")) 
	        {	        	
	            return comprobar;
	        }
	        else
	        {
	        	comprobar= true;
	        	
	        }
	        int valor = Integer.valueOf(valor1);

	        if (valor >= 1 && valor <= 9) 
	        {	        	
	            comprobar = true;
	         } 
	         else
	         {	        	 
	             comprobar = false;
	         }
	       
	        return comprobar;
	    }	    

 
	    public boolean existe_caja(int valor, int fila, int columna) 
	    {
	        //VARIABLES.
	        int minimo_fila;
	        int maximo_fila;
	        int minimo_columna;
	        int maximo_columna;
	        boolean resultado = false;

	        //DETERMINAMOS LAS FILAS DE LA CAJA.
	        if (fila >= 0 && fila < 3) 
	        {
	            minimo_fila = 0;
	            maximo_fila = 2;
	        } 
	        else if (fila >= 3 && fila < 6) 
	        {
	            minimo_fila = 3;
	            maximo_fila = 5;
	        } 
	        else 
	        {
	            minimo_fila = 6;
	            maximo_fila = 8;
	        }

	        //DETERMINAMOS LAS COLUMNAS DE LA CAJA.
	        if (columna >= 0 && columna < 3) 
	        {
	            minimo_columna = 0;
	            maximo_columna = 2;
	        }
	        else if (columna >= 3 && columna < 6) 
	        {
	            minimo_columna = 3;
	            maximo_columna = 5;
	        }
	        else
	        {
	            minimo_columna = 6;
	            maximo_columna = 8;
	        }

	        //RECORREMOS EL RANGO DE LA CAJA, Y BUSCAMOS EL VALOR.
	        for (int f = minimo_fila; f <= maximo_fila; f++) 
	        {
	            for (int c = minimo_columna; c <= maximo_columna; c++) 
	            {
	                if (valor == 0) 
	                {
	                } 
	                else
	                {
	                    if ((textFields[f][c].getText()).equals(Integer.toString(valor))) 
	                    {
	                        resultado = true;
	                        break;
	                    }
	                }
	            }
	        }
	        //REGRESAMOS EL VALOR BOOLEANO.
	        return resultado;
	    }
	    
	    public void pintarRegion()
	    {
	      //RECORREMOS EL RANGO DE LA CAJA, Y BUSCAMOS EL VALOR.
	        for (int f = 0; f <9; f++) {
	            for (int c = 0; c <9; c++) {	                
	            	if((f>=0 && f<3)&&(c>=0 && c<3)||(f>=0 && f<3)&&(c>=6 && c<9)
	                   ||(f>=6 && f<9)&&(c>=0 && c<3)||(f>=6 && f<9)&&(c>=6 && c<9)
	                   ||(f>=3 && f<6)&&(c>=3 && c<6)){
	                    textFields[f][c].setBackground(colorFondo);	                    
	                  }	                
	            }
	        }
	    }
	    
	    //METODO QUE DETECTA SI EL JUEGO FUE TERMINADO.
		public void terminado()
		{
			int contadorJuego = 0;
			
			for ( int f = 0; f < 9; f ++)
			{
				for ( int c = 0; c < 9; c ++)
				{					 
					if (!((textFields[f][c]).getText()).isEmpty())
					{						
						contadorJuego++;
						if (contadorJuego==81)
						{
							JOptionPane.showMessageDialog(null, "El juego ha terminado");
						}
					}
				}
			}
		}
	}