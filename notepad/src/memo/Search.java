package memo;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Search extends JDialog implements ActionListener {
	Container con;
	JButton button1, button2;
	JCheckBox box1;
	JTextField jPane1, jPane2;
	JLabel label1,label2;
	JTextPane textPane;
	String allText;
	int last;
	Option np;//여러 메소드에서 사용하기위해 전역변수 선언
	
	JPanel panel;
	Search(Option np){
		super(np, "찾기", false);
		this.np = np;
		setTitle("찾기");
		setSize(500,160);
		
		con = getContentPane();
		con.setLayout(null);
		
		textPane = np.area; //메모장 입력칸 가져오기
		
		label1 = new JLabel("찾을 단어 입력");
		label1.setBounds(15,10,100,20);
		label2 = new JLabel("바꿀 단어 입력");
		label2.setBounds(15,40,100,20);
		label2.setVisible(false);
		jPane1 = new JTextField();
		jPane1.setBounds(105,10,230,20);
		jPane2 = new JTextField();
		jPane2.setBounds(105,40,230,20);
		jPane2.setVisible(false);
		
		button1 = new JButton("찾기");
		button1.setBounds(115, 70, 100, 20);
		button2 = new JButton("취소");
		button2.setBounds(225, 70, 100, 20);
		box1 = new JCheckBox("찾아 바꾸기");
		box1.setBounds(350, 30, 100, 20);
		
		box1.addActionListener(this);
		button2.addActionListener(this);
		button1.addActionListener(this);
		
		con.add(label1);
		con.add(button1);
		con.add(button2);
		con.add(box1);
		con.add(jPane1);
		con.add(jPane2);
		con.add(label2);
		
		setVisible(true);
	}
	
	public String setFind() {
		String s = textPane.getText();
		StringTokenizer st = new StringTokenizer(s, "\n");	//문자열에서 엔터제거, 엔터를 기준으로 문자열 배열에 저장
		s = "";
		
		while (st.hasMoreTokens()) { //문자열이 있으면 true, 없으면 false
			s += st.nextToken(); //다음 문자열
		}
		return s;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand(); //버튼의 text 값
		
		if(box1.isSelected()) {	//boolean값으로 반환됨 주로 체크박스랑 라디오버튼
			
			button1.setText("찾아 바꾸기");
			label2.setVisible(true);
			jPane2.setVisible(true);
		}
		if (!box1.isSelected()) { // boolean값으로 반환됨 주로 체크박스랑 라디오버튼

			button1.setText("찾기");
			label2.setVisible(false);
			jPane2.setVisible(false);
		}

		if(s.equals(button1.getText())) { //찾기버튼
			allText = setFind(); 
			
			String findText = jPane1.getText();//찾는 문자열
			
			int leng = findText.length();//찾는 문자열의 길이
			
			int start = textPane.getSelectionEnd();//마우스 커서의 현재 위치 = 찾기 시작할 위치
			
			int first = allText.indexOf(findText, start);//문자열내에서 주어진 문자열의 위치 리턴, 못 찾으면 -1리턴
			
			if(first == -1) {
				last = -1;
			}
			else {
				last = first + leng;
			}
			textPane.select(first,  last);
			
			if (button1.getText().equals("찾아 바꾸기")) {
				if(first != -1) {
					textPane.replaceSelection(jPane2.getText());
				}
			}
			if(first == -1) {
				int i = JOptionPane.showConfirmDialog(this, "찾는 문자열이 없습니다 처음부터 찾으시겠습니까?", "찾기",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				
				if(i == JOptionPane.YES_OPTION)
					return;
				else
					dispose(); //종료 커맨드
			}
		}
		
		if(e.getSource() == button2) {
			dispose();
		}
	}
		
	}
		


