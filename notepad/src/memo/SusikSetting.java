package memo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class SusikSetting extends JDialog implements ActionListener{
	Option np;
	Container con;
	JTextPane jPane;
	JComboBox box1, box2, box3;
	JButton button1, button2, button3;

	SimpleAttributeSet saset = new SimpleAttributeSet();

	Font font,font2;
	Color color;
	
	JColorChooser cDialog;

	String s = null;
	String fontname = "굴림";
	int size = 8, attri = Font.PLAIN;
	
	SusikSetting(Option np) {
		super(np, "글자속성", true);//modal다이얼로그는 true = 창이 있으면 다른 작업 못함/modeless리스는 false = 창이 있어도 다른 작업 가능
		jPane = new JTextPane();
		font = new Font(fontname, attri, size);
		this.np = np;		
		//this 선언은 하지 않으면 Option np는 생성자 안에서만 사용가능하므로 다른 클래스에서 사용하기 위해서는 this.np = np 선언을 해야한다.
		con = getContentPane();
		con.setLayout(null);
		 s = np.area.getSelectedText();
		 jPane.setText(s);
		
		
		jPane.setText(s);
		jPane.setBounds(10, 100, 225, 75);
		jPane.setEditable(true);
		
		box1 = new JComboBox();
		box1.setBounds(65,10,70,20);
		box1.addItem("8");
		box1.addItem("12");
		box1.addItem("15");
		box1.addItem("20");
		box1.addItem("30");
		box1.addActionListener(this);
		
		box2 = new JComboBox();
		box2.setBounds(65,40,70,20);
		box2.addItem("보통");
		box2.addItem("굵게");
		box2.addItem("기울게");
		box2.addActionListener(this);
		
		box3 = new JComboBox();
		box3.setBounds(65,70,70,20);
		box3.addItem("굴림체");
		box3.addItem("명조체");
		box3.addItem("맑은 고딕");
		box3.addItem("궁서체");
		box3.addItem("돋움체");
		box3.addActionListener(this);
		
		JLabel label1 = new JLabel("글크기");
		label1.setBounds(15,10,70,20);
		JLabel label2 = new JLabel("글속성");
		label2.setBounds(15,40,70,20);
		JLabel label3 = new JLabel("글꼴");
		label3.setBounds(15,70,70,20);
		
		button1 = new JButton("설정");
		button1.setBounds(155,10,70,20);
		button1.addActionListener(this);
		button2 = new JButton("취소");
		button2.setBounds(155,40,70,20);
		button2.addActionListener(this);
		button3 = new JButton("색상");
		button3.setBounds(155,70,70,20);
		button3.addActionListener(this);
		
		con.add(jPane);
		con.add(box1);
		con.add(box2);
		con.add(box3);
		con.add(label1);
		con.add(label2);
		con.add(label3);
		con.add(button1);
		con.add(button2);
		con.add(button3);

		setTitle("글꼴");
		setSize(275,225);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			doSet();
			this.dispose();
		}
		else if (e.getSource() == button2) {
			System.exit(0);

		}
		else if (e.getSource() == button3) {
			color = JColorChooser.showDialog(this, "색상을 고르시오", Color.blue);
			if (color != null) {
				jPane.setForeground(color);
			} else
				JOptionPane.showMessageDialog(this, "색상을 선택하지 않았습니다",
						"색상", JOptionPane.ERROR_MESSAGE);
			return;

		}
		else if (e.getSource() == box1) {
			switch(Integer.parseInt((String) box1.getSelectedItem())) {
			case 8:
				size=8;
				break;
			case 12:
				size=12;
				break;
			case 15:
				size = 15;
				break;
			case 20:
				size = 20;
				break;
			case 30:
				size = 30;
				break;
			}
		}
		else if (e.getSource() == box2) {
			if((String)box2.getSelectedItem()=="보통") {
				attri = Font.PLAIN;
			}
			if((String)box2.getSelectedItem()=="굵게") {
				attri = Font.BOLD;
			}
			if((String)box2.getSelectedItem()=="기울게") {
				attri = Font.ITALIC;
			}
		}
		
		else if (e.getSource() == box3) {
			if((String)box3.getSelectedItem()=="굴림체") {
				fontname = "굴림체";
			}
			if((String)box3.getSelectedItem()=="명조체") {
				fontname = "명조";
			}
			if((String)box3.getSelectedItem()=="맑은 고딕") {
				fontname = "맑은 고딕";
			}
			if((String)box3.getSelectedItem()=="궁서체") {
				fontname = "궁서체";
			}
			if((String)box3.getSelectedItem()=="돋움체") {
				fontname = "돋움";
			}
			
		}
		
		font2 = new Font(fontname, attri, size);
		jPane.setFont(font2);
	}
	public void doSet() {
		if (color !=null) {
			StyleConstants.setForeground(saset, color);
		}
		if (fontname !=null) {
			StyleConstants.setFontFamily(saset, fontname);
		}
		if (size != 0) {
			StyleConstants.setFontSize(saset, size);
		}
		if (attri !=font.PLAIN) {
			StyleConstants.setBold(saset, false);
			StyleConstants.setItalic(saset, false);
		}
		else if(attri ==1) {
			StyleConstants.setBold(saset, true);
		}
		else if(attri ==2) {
			StyleConstants.setItalic(saset, true);
		}
		np.area.setCharacterAttributes(saset, true);
	}

}
