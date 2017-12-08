import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComponent;

public class GamePanel extends JPanel {
	public static final int rowNum = 9;
	public static final int colNum =9;
	private static final int blockLength = 3;
	private Box [][] values;

	public GamePanel(){
		setLayout(new GridLayout(rowNum,colNum));
		values = new Box[rowNum][colNum];
		createBox();

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

	private void createBox(){
		for(int i = 0 ; i < rowNum ; i ++){
			for(int j = 0 ; j < colNum ; j ++){
				Box box = new Box(i,j);
				values[i][j] = box;
				box.setBackground(Color.WHITE);
				box.setVisible(true);
				box.addActionListener(new BoxListener());
				box.addFocusListener(new BoxFocusListener());
				if(i < blockLength && j < blockLength){
					box.setBackground(Color.LIGHT_GRAY);
				}
				else if(i < blockLength && j > 2 * blockLength-1){
					box.setBackground(Color.LIGHT_GRAY);
				}
				else if(i > 2 * blockLength - 1 && j < blockLength){
					box.setBackground(Color.LIGHT_GRAY);
				}
				else if(i > 2 * blockLength - 1 && j > 2 * blockLength - 1){
					box.setBackground(Color.LIGHT_GRAY);
				}
				else if( i > blockLength -1 && i < 2 * blockLength  && j > blockLength -1 && j < 2 * blockLength  ){
					box.setBackground(Color.LIGHT_GRAY);
				}
				
				add(box);
			}
		}
	}

	private boolean checkRow(Box b){
		boolean flag = true;
		String val = b.getText();
		int row = b.getRow();
		for(int j = 0 ; j < colNum ; j ++){
			if(values[row][j].getText().equals(val) && j != b.getCol()){
				flag = false;
			}
		}
		return flag;
	}

	private boolean checkCol(Box b){
		boolean flag = true;
		String val = b.getText();
		int col = b.getCol();
		for(int i = 0 ; i < rowNum ; i ++){
			if(values[i][col].getText().equals(val) && i != b.getRow()){
				flag = false;
			}
		}
		return flag;
	}


	private boolean checkBlock(Box b){
		boolean flag = true;
		for(int i = b.getRow() - b.getRow() % blockLength ; i < b.getRow() + (blockLength -  b.getRow() % blockLength) ; i ++){
			for(int j = b.getCol() - b.getCol() % blockLength ; j < b.getCol() + (blockLength - b.getCol() % blockLength) ; j ++){
				if(b.getText().equals(values[i][j].getText()) && i != b.getRow() && j != b.getCol()){
					flag = false;
				}
			}
		}
		return flag;
	}

	public Box[][] getValueMatrix(){
		return values;
	}



	private class BoxListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof Box ){
				Box b = (Box)e.getSource();

				if(b.validation()){
					if(checkRow(b) && checkCol(b) && checkBlock(b)){
						b.setActivate(true);
						values[b.getRow()][b.getCol()] = b;
						repaint();

					}
					else{
						int j =	JOptionPane.showConfirmDialog(null,"Violation of game rules" ,"Click ok",JOptionPane.DEFAULT_OPTION);
						b.setText(null);	
						repaint();
					}
				}
				else{
					int j =	JOptionPane.showConfirmDialog(null,"Invalid Input" ,"Click ok",JOptionPane.DEFAULT_OPTION);
					b.setText(null);	
					repaint();
				}
			}
		}
	}

	private class BoxFocusListener implements FocusListener{

		public void focusLost(FocusEvent e) {
			if(e.getSource() instanceof Box ){
				Box b = (Box)e.getSource();
				if(!b.getActivate()){
					b.setText(null);
				}
			}

		}

		public void focusGained(FocusEvent e) {
		}

	}


}
