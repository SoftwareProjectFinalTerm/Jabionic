import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame{

	JTextField link = new JTextField(30);
	JTextArea Text = new JTextArea(25,40);
	JScrollPane scrollText = new JScrollPane(Text);
	JButton readButton = new JButton("Read");

	public Gui() {
		setTitle("Bionic reader");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		NorthPanel NP = new NorthPanel();
		NP.setSize(500,200);
		NP.setLocation(0,0);
		contentPane.add(NP);
		
		SouthPanel SP = new SouthPanel();
		SP.setSize(500,500);
		SP.setLocation(0,200);
		contentPane.add(SP);
		
		setSize(500,700); 
		setVisible(true);
		setResizable(false);
	}
	
	class NorthPanel extends JPanel { 
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawLine(10,60,490,60); 
			g.drawLine(200,60,200,70); 
			g.drawLine(200,160,200,170); 
			g.drawLine(10,170,490,170); 
		}
	}
	
	class SouthPanel extends JPanel {
		public SouthPanel() {
			add(scrollText);
			add(new JLabel("Link"));
			add(link);
			add(readButton);
		}
	}
	
    public static void main(String[] args) {
//		new Gui();
		JsoupTest jsoupTest = new JsoupTest();
		jsoupTest.crawling("https://n.news.naver.com/mnews/article/293/0000039337?sid=105");
    }
}