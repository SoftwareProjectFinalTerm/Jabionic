import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class Gui extends JFrame{

	JsoupTest jsoupTest = new JsoupTest();
	JTextField link = new JTextField(30);
	JTextArea Text = new JTextArea(25,40);
	
	JScrollPane scrollText = new JScrollPane(Text);
	JButton readButton = new JButton("Read");
	String url = "";

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
	
	//바이오닉리딩 결과 띄워주는 패널
	class NorthPanel extends JPanel { 
		public void paintComponent(Graphics g) {
			
			//고정위치를 나타내는 부분 삭제(상하 줄만 제외 제거함) 
			super.paintComponent(g);
			g.drawLine(10,60,490,60); 
			//g.drawLine(200,60,200,70); 
			//g.drawLine(200,160,200,170); 
			g.drawLine(10,170,490,170); 
			g.setFont(new Font("맑은 고딕",Font.PLAIN,30));

			//String str = "example string";
			//g.drawString(str,100,125);
		}
	}

	//링크 받아오기, 기사 전체 출력 패널
	class SouthPanel extends JPanel {
		public SouthPanel() {
			add(scrollText);
			add(new JLabel("Link"));
			add(link);
			add(readButton);
			
			//링크 사용자에게 입력받도록 함
			readButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					url = link.getText();
					//JTextArea에서 기사 내용 볼 수 있도록 수정
					Text.setLineWrap(true);
				    Text.setWrapStyleWord(true);
					Text.setText(jsoupTest.classifySite(url));
					//jsoupTest.crawling(url);					
				}
			});
		}
	}
	
    public static void main(String[] args) {
		new Gui();
    }
}