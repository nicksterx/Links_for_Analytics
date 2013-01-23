import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class LinkerGUI  extends JFrame{
public File htmlFile;
public File saveFile;
	public LinkerGUI(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch( InstantiationException e){
            e.printStackTrace();
        } catch( IllegalAccessException e){
            e.printStackTrace();
        } catch ( UnsupportedLookAndFeelException e) {
			e.printStackTrace();
	    }
		Container content = this.getContentPane();
		setTitle("Links for Analytics");
		setSize(300,200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JPanel jp_labels = new JPanel(new GridLayout(7,1));
		JPanel jp_fields = new JPanel(new GridLayout(7,1));
		
		JLabel src_label = new JLabel("Campaign Source*: ");
		final JTextField tf_src = new JTextField();
		
		src_label.setLabelFor(tf_src);
		jp_labels.add(src_label);
		jp_fields.add(tf_src);
		
		JLabel label_med = new JLabel("Campaign Medium*: ");
		final JTextField tf_med = new JTextField();
		label_med.setLabelFor(tf_med);
		jp_labels.add(label_med);
		jp_fields.add(tf_med);
		
		JLabel label_term = new JLabel("Campaign Term: ");
		final JTextField tf_term = new JTextField();
		label_term.setLabelFor(tf_term);
		jp_labels.add(label_term);
		jp_fields.add(tf_term);

		JLabel label_content = new JLabel("Campaign Content: ");
		final JTextField tf_content = new JTextField();
		label_content.setLabelFor(tf_content);
		jp_labels.add(label_content);
		jp_fields.add(tf_content);
		
		JLabel label_name = new JLabel("Campaign Name*: ");
		final JTextField tf_name = new JTextField();
		label_name.setLabelFor(tf_name);
		jp_labels.add(label_name);
		jp_fields.add(tf_name);
		
		JLabel label_file = new JLabel("Newsletter File");
		final JButton btn_file = new JButton("Select file");
		label_file.setLabelFor(btn_file);
		jp_labels.add(label_file);
		jp_fields.add(btn_file);
		
		btn_file.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jf = new JFileChooser();
				int choice = jf.showOpenDialog(LinkerGUI.this);
				if(choice == JFileChooser.APPROVE_OPTION){
					btn_file.setText(jf.getSelectedFile().getName());
					htmlFile = jf.getSelectedFile();
				}else{
					System.out.println("You Closed it!");
				}
				
			}
		});
		JLabel label_save = new JLabel("Save As");
		final JButton btn_save = new JButton("Select Location");
		label_file.setLabelFor(btn_save);
		jp_labels.add(label_save);
		jp_fields.add(btn_save);
		
		btn_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jf = new JFileChooser();
				int choice = jf.showSaveDialog(LinkerGUI.this);
				if(choice == JFileChooser.APPROVE_OPTION){
					btn_save.setText(jf.getSelectedFile().getName());
					saveFile = jf.getSelectedFile();
				}else{
					System.out.println("You Closed it!");
				}
				
			}
		});
		
		content.add(jp_labels, BorderLayout.WEST);
		content.add(jp_fields, BorderLayout.CENTER);
		JButton submit = new JButton("Parse");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Parser parse = new Parser(htmlFile, tf_src.getText(), tf_med.getText(), tf_term.getText(), tf_content.getText(), tf_name.getText(), saveFile);
				
			}
		});
		content.add(submit,BorderLayout.SOUTH);
		

	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				LinkerGUI lg = new LinkerGUI();
				lg.setVisible(true);
				
			}
		});

	}

}
