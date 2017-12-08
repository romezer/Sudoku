import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel {
	private JButton cmdSet , cmdClear;
	private GamePanel gamePanel;
	private final Color ACTIVE_COLOUR = Color.BLUE;
	private final Color defaultColor = Color.BLACK;

	public ButtonPanel(GamePanel g){
		this.gamePanel = g;
		cmdSet = new JButton("Set");
		cmdClear = new JButton("Clear");
		cmdSet.addActionListener(new ButtonListener());
		cmdClear.addActionListener(new ButtonListener());
		add(cmdSet);
		add(cmdClear);

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cmdSet){
				cmdSet.setVisible(false);
				Box[][] values = gamePanel.getValueMatrix();
				for(int i = 0 ; i < gamePanel.rowNum ; i ++){
					for(int j = 0 ; j < gamePanel.colNum ; j ++){
						if(!values[i][j].getText().equals("")){
							values[i][j].setForeground(ACTIVE_COLOUR);
							values[i][j].setEditable(false);
						}
					}
				}
			}
			else if(e.getSource() == cmdClear){
				cmdSet.setVisible(true);
				Box[][] values = gamePanel.getValueMatrix();
				for(int i = 0 ; i < gamePanel.rowNum ; i ++){
					for(int j = 0 ; j < gamePanel.colNum ; j ++){
						values[i][j].setText("");
						values[i][j].setEditable(true);
						values[i][j].setForeground(defaultColor);
					}
				}
			}
		}
	}
}
