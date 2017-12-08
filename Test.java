import javax.swing.JFrame;
import javax.swing.BoxLayout; 

public class Test {
	private static int gamePanelWidth = 500;
	private static int gamePanelHight = 500;
	private static int buttonHight = 10;

	public static void main(String[] args) {

		JFrame frame = new JFrame("Sudoku");
		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.setLayout(boxLayout);
		frame.setSize(gamePanelWidth, gamePanelWidth + buttonHight);
		GamePanel g = new GamePanel();
		g.setSize(gamePanelWidth, gamePanelHight);
		ButtonPanel b = new ButtonPanel(g);
		b.setSize(gamePanelWidth, buttonHight);
		frame.add(g);
		frame.add(b);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}

}
