import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;

public class NotePad implements ActionListener{
	
	String filename;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Clipboard clipboard = toolkit.getSystemClipboard();
	public static void main(String[] args) {
		new NotePad();

	}
	Font font=new Font("Times New Roman",Font.PLAIN,30);
	
	TextArea txtArea = new TextArea();
	TextArea txt2 = new TextArea();
	TextArea txt3 = new TextArea();
	
	Frame f=new Frame("TEXT EDITOR");
	Frame f2=new Frame("Help");
	Frame f3=new Frame("About");
	MenuBar mb=new MenuBar();
	
	Menu fileMenu = new Menu("File");
	Menu editMenu = new Menu("Edit");
	Menu helpMenu = new Menu("Help");
	
	MenuItem fNew = new MenuItem(   "New ");
	MenuItem fOpen = new MenuItem(  "Open");
	MenuItem fSave = new MenuItem(  "Save");
	MenuItem fSaveAs = new MenuItem("Save As");
	MenuItem fClose = new MenuItem( "Close");
	
	MenuItem eCut = new MenuItem(  "Cut");
	MenuItem eCopy = new MenuItem( "Copy");
	MenuItem ePaste = new MenuItem("Paste");
	
	MenuItem hHelp = new MenuItem("View Help");
	MenuItem hAbout = new MenuItem("About");

	int count=0,count2=0;
	public NotePad() {
		fNew.addActionListener(this);
		
		fileMenu.add(fNew);
		fileMenu.add(fOpen);
		fileMenu.add(fSave);
		fileMenu.add(fSaveAs);
		fileMenu.add(fClose);
		
		editMenu.add(eCut);
		editMenu.add(eCopy);
		editMenu.add(ePaste);
		
		helpMenu.add(hHelp);
		helpMenu.add(hAbout);
		
		txtArea.setFont(font);
		txt2.setFont(font);
		txt3.setFont(font);
		
		//scroll.setSize(750, 495);
		
		mb.add(fileMenu);
		mb.add(editMenu);
		mb.add(helpMenu);
		
		txtArea.setBounds(0, 0, 800, 500);
		f.add(txtArea);
		//f.add(scroll);
		f.setMenuBar(mb);
		f.setTitle("NotePad");
		f.setSize(800, 500);
		f.addWindowListener(new WindowListener () {

		    public void windowClosing(WindowEvent we) {
		        System.exit(0);
		    }

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		f.setLocationRelativeTo(null);
		fNew.addActionListener(this);
		fOpen.addActionListener(this);
		fSave.addActionListener(this);
		fSaveAs.addActionListener(this);
		fClose.addActionListener(this);
		eCut.addActionListener(this);
		eCopy.addActionListener(this);
		ePaste.addActionListener(this);
		hHelp.addActionListener(this);
		hAbout.addActionListener(this);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(fNew)) {
			txtArea.setText("");
			f.setTitle("New File");
		}
		if(e.getSource().equals(fOpen)) {
			FileDialog fileDialog = new FileDialog( f, "open file", FileDialog.LOAD);
			fileDialog.setVisible(true);
			
			if(fileDialog.getFile() != null) {
				filename = fileDialog.getDirectory() + fileDialog.getFile();
				f.setTitle(filename);
			}
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				StringBuilder sb = new StringBuilder();
				String line=null;
				while((line = reader.readLine()) != null) {
					sb.append(line + "\n");
					txtArea.setText(sb.toString());
				}
				reader.close();
			} catch (IOException e1) {
				System.out.println("File Not Found");
			}
		}
		
		if(e.getSource().equals(fSave)) {
			try {
				String textToSet = txtArea.getText();
				PrintWriter writer =new PrintWriter(filename.toString(), "UTF-8");
				writer.write(textToSet);
                writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource().equals(fSaveAs)) {
			FileDialog filedialog = new FileDialog(f,"Save File",FileDialog.SAVE);
			filedialog.setVisible(true);
			
			if(filedialog.getFile() != null) {
				filename = filedialog.getDirectory() + filedialog.getFile();
				f.setTitle(filename);
			}
			try {
				FileWriter fileWriter = new FileWriter(filename);
				fileWriter.write(txtArea.getText());
				f.setTitle(filename);
				fileWriter.close();
			} catch(IOException e1) {
				System.out.println("File Not Found");
			}
		}
		
		if(e.getSource().equals(fClose)) {
			System.exit(0);
		}
		
		if(e.getSource().equals(eCut)) {
			String cutString = txtArea.getSelectedText();
			StringSelection cutSelection = new StringSelection(cutString);
			clipboard.setContents(cutSelection, cutSelection);
			txtArea.replaceRange("", txtArea.getSelectionStart(), txtArea.getSelectionEnd());
		}
		
		if(e.getSource().equals(eCopy)) {
			String copyText =txtArea.getSelectedText();
			StringSelection copySelection = new StringSelection(copyText);
			clipboard.setContents(copySelection, copySelection);
		}
		if(e.getSource().equals(ePaste)) {
			try {
				Transferable pasteText = clipboard.getContents(f);
				String sel = (String) pasteText.getTransferData(DataFlavor.stringFlavor);
				txtArea.replaceRange(sel, txtArea.getSelectionStart(), txtArea.getSelectionEnd());
			} catch(Exception e1) {
				System.out.println("Paste not Working");
			}
		}
		
		if(e.getSource().equals(hHelp)) {
			f2.setTitle("Help");
			File selFile = new File("F:\\venky\\projects\\placementwebsite\\NotePad\\src\\help.txt");
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(selFile));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			};
			try {
			    
			    String str;
			    
			    while(count==0) {
			    while ((str = in.readLine()) != null) {
			        txt2.append("\n"+str);
			    }
			    count++;
			    }
			} catch (IOException e3) {
			} finally {
			    try { in.close(); } catch (Exception ex) { }
			}
			txt2.setEditable(false);
			f2.add(txt2);
			f2.setSize(500, 500);
			f2.addWindowListener(new WindowListener () {

			    public void windowClosing(WindowEvent we) {
			        f2.dispose();
			    }

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			f2.setLocationRelativeTo(null);
			f2.setVisible(true);
		}
		
		if(e.getSource().equals(hAbout)) {
			f3.setTitle("About");
			File selFile = new File("F:\\venky\\projects\\placementwebsite\\NotePad\\src\\about.txt");
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(selFile));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			};
			try {
			    
			    String str;
			    while(count2==0) {
			    	while ((str = in.readLine()) != null) {
			    		txt3.append("\n"+str);
			    	}
			    	count2++;
				}
			} catch (IOException e3) {
			} finally {
			    try { in.close(); } catch (Exception ex) { }
			}
			txt3.setEditable(false);
			f3.add(txt3);
			
			f3.setSize(500, 500);
			f3.addWindowListener(new WindowListener () {

			    public void windowClosing(WindowEvent we) {
			        f3.dispose();
			    }

				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			f3.setLocationRelativeTo(null);
			f3.setVisible(true);
		}
		
	}

}
