import javax.swing.JTextField;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA 
 * CODIGOS: 1053506 - 1356358
 * FECHA: ABRIL 27 DE 2014
 * DESCRIPCION: SUDOKU
 * */

	class CustomJTextField extends JTextField
	{
		private int row;
		private int column;
		private int numero;
		
		public CustomJTextField(int row,int column) 
		{
			super();
			this.row = row;
			this.column = column;
		}
		
		public int getRow() 
		{
			return this.row;
		}
		
		public int getColumn() 
		{
			return this.column;
		}
		
		public void setNumero(int numero)
		{
			this.numero = numero;
		}
		
		public int getNumero()
		{
			return this.numero;
		}
	}