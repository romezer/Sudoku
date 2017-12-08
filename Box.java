import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class Box extends JFormattedTextField {
	private int rowIndex;
	private int colIndex;
	private boolean activate;

	public Box(int row , int col){
		super();
		this.activate = false;
		this.rowIndex = row;
		this.colIndex = col;
	}

	public int getRow(){
		return rowIndex;
	}
	public int getCol(){
		return colIndex;
	}

	public boolean getActivate(){
		return activate;
	}

	public void setActivate(boolean b){
		activate = b;
	}

	public boolean validation(){
		String regex = "[1-9]+";
		if(this.getText().matches(regex) && this.getText().length() == 1){
			return true;
		}
		else{
			return false;
		}
	}
}
