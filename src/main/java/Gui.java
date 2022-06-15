import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.text.AttributedString;

public class Gui extends JFrame {

	JsoupTest jsoupTest = new JsoupTest();
	JTextField link = new JTextField(30);
	JTextArea Text = new JTextArea(25,40);

	JScrollPane scrollText = new JScrollPane(Text);
	JButton readUrlButton = new JButton("Read URL");
	JButton readButton = new JButton("Read");
	String url = "";

	ResponseCrawling rc = new ResponseCrawling();
	static int cnt = 0;

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

		//버튼 눌리면 패널 업데이트
		public NorthPanel() {

			readButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					//TextArea에 있는 내용을 BionicReading
					try {
						rc = jsoupTest.BionicReading(Text.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					//결과를 출력하기 위해 paintComponent()를 업데이트
					for(cnt = 0; cnt < rc.getResponseWords().size(); cnt++) {

						try {
							Thread.sleep(100);
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
						revalidate();
						update(getGraphics());

					}
				}
			});
		}
		
		public void paintComponent(Graphics g) {
			
			//고정위치를 나타내는 부분 삭제(상하 줄만 제외 제거함) 
			super.paintComponent(g);
			g.drawLine(10,60,490,60); 
			//g.drawLine(200,60,200,70); 
			//g.drawLine(200,160,200,170); 
			g.drawLine(10,170,490,170);
			
			//리스트가 비었을 경우(Bionic reading 된 결과가 없을 경우)
			if(rc.getResponseWords().isEmpty()) return;
			
			Font MalgunGothic = new Font("맑은 고딕",Font.PLAIN,30);
			Font MalgunGothicBold = new Font("맑은 고딕",Font.BOLD,30);
			
			//g.setFont();			
			//g.drawString(str,100,125);
			
			//지정 인덱스의 character만 강조하여 출력
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			String str = new String(rc.getResponseWords().get(cnt).getResultWord());
			int index = rc.getResponseWords().get(cnt).getStressIndex();
			
			AttributedString as = new AttributedString(str);
			as.addAttribute(TextAttribute.FONT, MalgunGothic);
			as.addAttribute(TextAttribute.FONT, MalgunGothicBold, 0, index+1);
			
			g2.drawString(as.getIterator(), 100, 125);
		}			
	}

	//링크 받아오기, 기사 전체 출력 패널
	class SouthPanel extends JPanel {
		
		public SouthPanel() {
			
			add(scrollText);
			add(readButton);
			add(link);
			add(readUrlButton);
			
			//링크 사용자에게 입력받도록 함
			readUrlButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					url = link.getText();
					//JTextArea에서 기사 내용 볼 수 있도록 수정
					Text.setLineWrap(true);
				    Text.setWrapStyleWord(true);
					try {
						Text.setText(jsoupTest.crawling(url));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}
    public static void main(String[] args) {
        new Gui();
    }
}