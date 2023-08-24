package memo;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

class Option extends JFrame implements ActionListener {
	JMenuBar mb;
	JMenu file, edit, susik, help, other, other1;
	JMenuItem newmemo, win, open, save, othersave, print, exit;
	JMenuItem cancel, reuse, cut, paste, copy, delete, search, allcheck, dat;
	JMenuItem fon, inter, kor, unhelp, f1;
	JMenuItem fontype, fonsize, fonoption, foncolor;
	JMenu fon1, fonsize1, shape;
	JMenuItem color, etc1, etc2, etc3, etc4, etc5, etc6, etc7, etc8, etc9, etc10, etc11, etc12;
	JTextPane area;
	Font font;
	String filePath;
	SimpleAttributeSet saset;
	JColorChooser cDialog;
	UndoManager UndoManager = new UndoManager();
	Dimension dimen, dimen1;
	int xpos, ypos;
	
	Option() {
		Editer();
		setTitle("제목없음.txt");
		this.setJMenuBar(mb);

		setSize(400, 400);
		setVisible(true);
	}

	void Editer() {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		dimen = tk.getScreenSize();
		dimen1 = this.getSize();
		
		xpos = (int) (dimen.getWidth()/2 - dimen1.getWidth()/2)-150; //스크린 사이즈/2 - 프레임사이즈/2 가 정중앙
		ypos = (int) (dimen.getHeight()/2 - dimen1.getHeight()/2)-150;
		//xpos = (int) ((dimen.getWidth()/2)-150 - (dimen1.getWidth()/2)-150); //스크린 사이즈/2 - 프레임사이즈/2 가 정중앙
		//ypos = (int) (dimen.getHeight()/2)-150 - (dimen1.getHeight()/2)-150);
		
		this.setLocation(xpos, ypos);
		
		Image logo = tk.getImage("C:\\Users\\kcd\\Desktop\\이미지\\calculator.png");
		setIconImage(logo);
		
		saset = new SimpleAttributeSet();
		mb = new JMenuBar();
		// 메뉴바
		file = new JMenu("파일(F)");
		file.setMnemonic('F');
		edit = new JMenu("편집(E)");
		edit.setMnemonic(KeyEvent.VK_E);
		susik = new JMenu("서식(S)");
		susik.setMnemonic(KeyEvent.VK_S);
		help = new JMenu("도움말(H)");
		help.setMnemonic(KeyEvent.VK_H);
		other = new JMenu("기타1");
		other1 = new JMenu("기타2");
		// 파일
		newmemo = new JMenuItem("새로 만들기(N)");
		newmemo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		win = new JMenuItem("새 창(W)");
		win.setAccelerator(KeyStroke.getKeyStroke('N', ActionEvent.CTRL_MASK ^ ActionEvent.SHIFT_MASK));
		open = new JMenuItem("불러오기(O)");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		save = new JMenuItem("저장(S)");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		othersave = new JMenuItem("다른 이름으로 저장(A)");
		othersave.setAccelerator(KeyStroke.getKeyStroke('S', ActionEvent.CTRL_MASK ^ ActionEvent.SHIFT_MASK));
		print = new JMenuItem("인쇄(P)");
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		exit = new JMenuItem("종료(X)");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		// 편집
		cancel = new JMenuItem("실행 취소");
		cancel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		reuse = new JMenuItem("다시 실행");
		reuse.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		cut = new JMenuItem("잘라내기(X)");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		copy = new JMenuItem("복사(C)");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		paste = new JMenuItem("붙여넣기(V)");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		delete = new JMenuItem("삭제");
		search = new JMenuItem("찾기");
		search.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		allcheck = new JMenuItem("모두선택(A)");
		allcheck.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		dat = new JMenuItem("시간/날짜(D)");
		dat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		// 서식
		fon = new JMenuItem("글자속성");
		// 도움말
		inter = new JMenuItem("인터넷검색");
		kor = new JMenuItem("영한 번역");
		unhelp = new JMenuItem("도움말");
		f1 = new JMenuItem("메모장정보");
		// 기타1
		fontype = new JMenuItem("글자종류");
		fonsize = new JMenuItem("글자크기");
		fonoption = new JMenuItem("글자속성");
		foncolor = new JMenuItem("글자색상");
		// 기타2
		fon1 = new JMenu("글꼴");
		fonsize1 = new JMenu("글자크기");
		shape = new JMenu("모양");
		color = new JMenuItem("색상");
		// 기타2 내부 메뉴
		etc1 = new JCheckBoxMenuItem("굴림체");
		etc2 = new JCheckBoxMenuItem("궁서체");
		etc3 = new JCheckBoxMenuItem("돋움체");
		etc4 = new JCheckBoxMenuItem("5");
		etc5 = new JCheckBoxMenuItem("10");
		etc6 = new JCheckBoxMenuItem("15");
		etc7 = new JCheckBoxMenuItem("20");
		etc8 = new JCheckBoxMenuItem("25");
		etc9 = new JCheckBoxMenuItem("30");
		etc10 = new JCheckBoxMenuItem("보통");
		etc11 = new JCheckBoxMenuItem("굵게");
		etc12 = new JCheckBoxMenuItem("기울게");

		// 버튼 그룹 그룹생성한다면 중복체크 안됨
		ButtonGroup group = new ButtonGroup();
		group.add(etc1);
		group.add(etc2);
		group.add(etc3);
		ButtonGroup group1 = new ButtonGroup();
		group1.add(etc4);
		group1.add(etc5);
		group1.add(etc6);
		group1.add(etc7);
		group1.add(etc8);
		group1.add(etc9);
		ButtonGroup group2 = new ButtonGroup();
		group2.add(etc10);
		group2.add(etc11);
		group2.add(etc12);

		file.add(newmemo);
		file.add(win);
		file.add(open);
		file.add(save);
		file.add(othersave);
		file.add(print);
		file.add(exit);

		edit.add(cancel);
		edit.add(reuse);
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		edit.add(search);
		edit.add(allcheck);
		edit.add(dat);

		susik.add(fon);

		help.add(inter);
		help.add(kor);
		help.add(unhelp);
		help.add(f1);

		other.add(fontype);
		other.add(fonsize);
		other.add(fonoption);
		other.add(foncolor);

		fon1.add(etc1);
		fon1.add(etc2);
		fon1.add(etc3);

		fonsize1.add(etc4);
		fonsize1.add(etc5);
		fonsize1.add(etc6);
		fonsize1.add(etc7);
		fonsize1.add(etc8);
		fonsize1.add(etc9);

		shape.add(etc10);
		shape.add(etc11);
		shape.add(etc12);

		other1.add(fon1);
		other1.add(fonsize1);
		other1.add(color);
		other1.add(shape);

		mb.add(file);
		mb.add(edit);
		mb.add(susik);
		mb.add(help);
		mb.add(other);
		mb.add(other1);

		area = new JTextPane();
		area.setEditable(true);
		font = new Font("돋움", Font.BOLD, 15);
		area.setFont(font);
		this.add("Center", area);

		// 파일 액션
		newmemo.addActionListener(this);
		win.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		othersave.addActionListener(this);
		print.addActionListener(this);
		exit.addActionListener(this);

		// 편집 액션
		cancel.addActionListener(this);
		reuse.addActionListener(this);
		cut.addActionListener(this);
		paste.addActionListener(this);
		copy.addActionListener(this);
		delete.addActionListener(this);
		search.addActionListener(this);
		allcheck.addActionListener(this);
		dat.addActionListener(this);

		// 서식 액션
		fon.addActionListener(this);

		// 도움말 액션
		inter.addActionListener(this);
		kor.addActionListener(this);
		unhelp.addActionListener(this);
		f1.addActionListener(this);

		// 기타1액션
		fontype.addActionListener(this);
		fonsize.addActionListener(this);
		fonoption.addActionListener(this);
		foncolor.addActionListener(this);

		// 기타2액션
		etc1.addActionListener(this);
		etc2.addActionListener(this);
		etc3.addActionListener(this);
		etc4.addActionListener(this);
		etc5.addActionListener(this);
		etc6.addActionListener(this);
		etc7.addActionListener(this);
		etc8.addActionListener(this);
		etc9.addActionListener(this);
		etc10.addActionListener(this);
		etc11.addActionListener(this);
		etc12.addActionListener(this);
		color.addActionListener(this);

		area.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				UndoManager.addEdit(e.getEdit());
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		// 파일부분

		// 새로만들기
		if (e.getSource() == newmemo) {
			if (area.getText() != null) {
				int mess1 = JOptionPane.showConfirmDialog(this, "저장하시겠습니까?", "새로만들기", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (mess1 == 0) {
					JFileChooser fDialog = new JFileChooser("c:/");
					FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 문서(*.txt)", ("txt"));
					fDialog.setFileFilter(filter);

					int returnVal = fDialog.showSaveDialog(this);

					if (returnVal == 1) {
						JOptionPane.showMessageDialog(this, "파일을 가지고 오지 못했습니다");
						return;
					}
					filePath = fDialog.getSelectedFile().getPath();

					try {
						String s = area.getText();
						FileWriter fw = new FileWriter(filePath + ".txt");
						fw.write(s);

						this.setTitle(filePath);
						fw.close();
						JOptionPane.showMessageDialog(this, "파일을 저장했습니다");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(this, e2);
					}
				} else if (mess1 == 1) {
					area.setText(null);
				}

			} else {
				area.setText(null);
			}
		}
		// 새 창
		else if (e.getSource() == win) {
			Option opt = new Option();
			opt.setVisible(true);
		}
		// 불러오기
		else if (e.getSource() == open) {
			JFileChooser fDialog = new JFileChooser("c:/");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 문서(*.txt)", ("txt"));
			fDialog.setFileFilter(filter);
			int returnVal = fDialog.showOpenDialog(this);

			if (returnVal == 1) {
				JOptionPane.showMessageDialog(this, "파일을 가지고 오지 못했습니다");
				return;
			}
			filePath = fDialog.getSelectedFile().getPath();
			String s = "";

			try {
				FileReader fr = new FileReader(filePath);
				int k;

				while (true) {
					k = fr.read();
					if (k == -1)
						break;

					s += (char) k;
				}
				fr.close();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, e2);
			}
			area.setText(s);
		}
		// 저장
		else if (e.getSource() == save) {
			if (filePath == null) {
				JFileChooser Dialog = new JFileChooser("c:/");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 문서(*.txt)", ("txt"));
				Dialog.setFileFilter(filter);
				int returnVal = Dialog.showSaveDialog(this);

				if (returnVal == 1) {
					JOptionPane.showMessageDialog(this, "파일을 가지고 오지 못했습니다");
					return;
				}

				filePath = Dialog.getSelectedFile().getPath();

				try {
					String s = area.getText();
					FileWriter fw = new FileWriter(filePath + ".txt");
					fw.write(s);

					this.setTitle(filePath);
					fw.close();
					JOptionPane.showMessageDialog(this, "파일을 저장했습니다");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, e2);

				}
			} else {
				try {
					FileWriter w = new FileWriter(filePath);
					String s = area.getText();
					w.write(s);
					w.close();

					this.setTitle(filePath);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "파일을 가지고 오지 못했습니다");
				}
			}
		}

		// 다른이름으로 저장
		else if (e.getSource() == othersave) {
			JFileChooser fDialog = new JFileChooser("c:/");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 문서(*.txt)", ("txt"));
			fDialog.setFileFilter(filter);

			int returnVal = fDialog.showSaveDialog(this);

			if (returnVal == 1) {
				JOptionPane.showMessageDialog(this, "파일을 가지고 오지 못했습니다");
				return;
			}
			filePath = fDialog.getSelectedFile().getPath();

			try {
				String s = area.getText();
				FileWriter fw = new FileWriter(filePath + ".txt");
				fw.write(s);

				this.setTitle(filePath);
				fw.close();
				JOptionPane.showMessageDialog(this, "파일을 저장했습니다");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, e2);
			}
		}
		// 인쇄
		else if (e.getSource() == print) {
			PrinterJob pj = PrinterJob.getPrinterJob();
			/*
			 * pj.setPrintable((Printable) area); pj.printDialog(); pj.print();
			 */
			if (pj.printDialog()) {
				try {
					pj.print();
				} catch (PrinterException exc) {
					System.out.println(exc);
				}
			}
		}
		// 종료
		else if (e.getSource() == exit) {
			System.exit(0);
		}

		// 편집 부분

		// 실행취소
		else if (e.getSource() == cancel) {
			try {
				UndoManager.undo();
			} catch (CannotRedoException cre) {
				cre.printStackTrace();
			}

		}
		// 다시실행
		else if (e.getSource() == reuse) {
			try {
				UndoManager.redo();
			} catch (CannotRedoException cre) {
				cre.printStackTrace();
			}

		}
		// 잘라내기
		else if (e.getSource() == cut) {
			area.cut();
		}
		// 복사
		else if (e.getSource() == copy) {
			area.copy();
		}
		// 봍여넣기
		else if (e.getSource() == paste) {
			area.paste();
		}
		// 삭제
		else if (e.getSource() == delete) {

		}
		// 찾기
		else if (e.getSource() == search) {
			new Search(this);

		}
		// 모두선택
		else if (e.getSource() == allcheck) {
			area.selectAll();
		}
		// 날짜
		else if (e.getSource() == dat) {
			/*Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 E요일 mm분 ss초 ");
			JOptionPane.showMessageDialog(this, sdf.format(today));*/
			
			NowTime nt = new NowTime();
			//nt.Times();
			String abc = nt.Times();
			JOptionPane.showMessageDialog(null, abc, "날짜/시간",JOptionPane.PLAIN_MESSAGE);
			
			
		}

		// 서식

		// 글꼴
		else if (e.getSource() == fon) {
			SusikSetting susiks = new SusikSetting(this);

		}

		// 도움말

		// 인터넷검색
		else if (e.getSource() == inter) {
			String url = "https://www.google.com/";

			try {
				Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + url);
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}
		// 한영 번역
		else if (e.getSource() == kor) {
			String trans = area.getSelectedText();
			String url = "https://translate.google.com/?sl=en&tl=ko&text=" + trans + "&op=translate";
			String url2 = "https://translate.google.com/?sl=en&tl=ko&text=&op=translate";
			try {
				if (trans != null) {
					trans = trans.replace(" ", "%20");
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url); //공백 꼭 필요
				} else if (trans == null) {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url2);
				}
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}
		// 도움말
		else if (e.getSource() == unhelp) {
			String url = "https://support.office.com/ko-kr/article/%EB%A9%94%EB%AA%A8%EC%9E%A5-%EB%8F%84%EA%B5%AC-c136c884-871b-4481-8ace-7c206271d50a";
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException e3) {
				e3.printStackTrace();
			} catch (URISyntaxException e3) {
				e3.printStackTrace();
			}
		}
		// 메모장 정보
		else if (e.getSource() == f1) {
			try {
				/*Runtime.getRuntime().exec("notepad");*/
				Desktop.getDesktop().open(new File("C://Windows/system32/notepad.exe"));
			} catch (Exception e3) {
			}
		}

		// 기타1

		// 글자종류
		else if (e.getSource() == fontype) {
			String[] ttf = { "궁서체", "맑은 고딕", "돋움체", "명조체" };
			Object selectedttf = JOptionPane.showInputDialog(this, "글꼴선택", "글꼴", JOptionPane.INFORMATION_MESSAGE, null,
					ttf, ttf[2]);
			String num = (String) selectedttf;
			if (selectedttf != null) {
				if (selectedttf.equals("궁서체")) {
					StyleConstants.setFontFamily(saset, "궁서체");
				} else if (selectedttf.equals("맑은 고딕")) {
					StyleConstants.setFontFamily(saset, "맑은 고딕");
				} else if (selectedttf.equals("돋움체")) {
					StyleConstants.setFontFamily(saset, "돋움체");
				} else if (selectedttf.equals("명조체")) {
					StyleConstants.setFontFamily(saset, "명조체");
				}
				area.setCharacterAttributes(saset, true);
			} else
				JOptionPane.showMessageDialog(this, "취소합니다", "글자종류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 글자크기
		else if (e.getSource() == fonsize) {
			String selectedttfSize = JOptionPane.showInputDialog(this, "크기선택", "글자크기");
			if (selectedttfSize != null) {
				int bo = Integer.parseInt(selectedttfSize);
				StyleConstants.setFontSize(saset, bo);
				area.setCharacterAttributes(saset, true);
			} else
				JOptionPane.showMessageDialog(this, "취소합니다", "글자크기", JOptionPane.ERROR_MESSAGE);
			return;

		}
		// 글자속성
		else if (e.getSource() == fonoption) {
			String[] bol = { "BOLD", "ITALIC", "PLAIN" };
			Object selectedbol = JOptionPane.showInputDialog(this, "속성선택", "속성", JOptionPane.INFORMATION_MESSAGE, null,
					bol, bol[1]);
			if (selectedbol != null) {
				if (selectedbol.equals("BOLD")) {
					StyleConstants.setBold(saset, true);
				} else if (selectedbol.equals("ITALIC")) {
					StyleConstants.setItalic(saset, true);
				} else if (selectedbol.equals("PLAIN")) {
					StyleConstants.setItalic(saset, false);
					StyleConstants.setBold(saset, false);
				}
				area.setCharacterAttributes(saset, true);
			} else
				JOptionPane.showMessageDialog(this, "취소합니다", "글자속성", JOptionPane.ERROR_MESSAGE);
			return;

		}
		// 글자색상
		else if (e.getSource() == foncolor) {
			cDialog = new JColorChooser();

			Color color = cDialog.showDialog(this, "색상을 고르시오", Color.blue);

			if (color != null) {
				StyleConstants.setForeground(saset, color);
				area.setCharacterAttributes(saset, true);
			} else
				JOptionPane.showMessageDialog(this, "취소합니다", "글자색상", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// 기타2

		// 글꼴
		else if (e.getSource() == etc1) {
			StyleConstants.setFontFamily(saset, "굴림");
			area.setCharacterAttributes(saset, true);
		} else if (e.getSource() == etc2) {
			StyleConstants.setFontFamily(saset, "궁서");
			area.setCharacterAttributes(saset, true);
		} else if (e.getSource() == etc3) {
			StyleConstants.setFontFamily(saset, "돋움");
			area.setCharacterAttributes(saset, true);
		}
		// 글자크기
		else if (e.getSource() == etc4) {
			StyleConstants.setFontSize(saset, 5);
			area.setCharacterAttributes(saset, true);

		} else if (e.getSource() == etc5) {
			StyleConstants.setFontSize(saset, 10);
			area.setCharacterAttributes(saset, true);
		} else if (e.getSource() == etc6) {
			StyleConstants.setFontSize(saset, 15);
			area.setCharacterAttributes(saset, true);
		} else if (e.getSource() == etc7) {
			StyleConstants.setFontSize(saset, 20);
			area.setCharacterAttributes(saset, true);
		} else if (e.getSource() == etc8) {
			StyleConstants.setFontSize(saset, 25);
			area.setCharacterAttributes(saset, true);
		} else if (e.getSource() == etc9) {
			StyleConstants.setFontSize(saset, 30);
			area.setCharacterAttributes(saset, true);
		}
		// 색상
		else if (e.getSource() == color) {
			cDialog = new JColorChooser();

			Color color = cDialog.showDialog(this, "색상을 고르시오", Color.blue);

			if (color != null) {
				StyleConstants.setForeground(saset, color);
				area.setCharacterAttributes(saset, true);
			} else
				JOptionPane.showMessageDialog(this, "취소합니다", "글자색상", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 모양
		else if (e.getSource() == etc10) {
			StyleConstants.setItalic(saset, false);
			StyleConstants.setBold(saset, false);
			area.setCharacterAttributes(saset, true);
		} else if (e.getSource() == etc11) {
			StyleConstants.setBold(saset, true);
			area.setCharacterAttributes(saset, true);
		} else if (e.getSource() == etc12) {
			StyleConstants.setItalic(saset, true);
			area.setCharacterAttributes(saset, true);
		}

	}

}

public class memo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Option();
	}
}
