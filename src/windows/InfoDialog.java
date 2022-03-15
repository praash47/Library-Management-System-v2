package windows;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class InfoDialog extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoDialog dialog = new InfoDialog("test", "test message", new ImageIcon("F:\\java\\LMSv2\\assets\\icons\\tick.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public InfoDialog(String title, String message, ImageIcon icon) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\LMSv2\\assets\\main-icon.png"));
		getContentPane().setSize(new Dimension(100, 100));
		setTitle(title);
		setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel dialogStatusIcon = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, dialogStatusIcon, 20, SpringLayout.WEST, getContentPane());
		dialogStatusIcon.setIcon(icon);
		getContentPane().add(dialogStatusIcon);
		
		JLabel dialogText = new JLabel(message);
		springLayout.putConstraint(SpringLayout.NORTH, dialogText, 116, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, dialogStatusIcon, -21, SpringLayout.NORTH, dialogText);
		springLayout.putConstraint(SpringLayout.WEST, dialogText, 22, SpringLayout.EAST, dialogStatusIcon);
		dialogText.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		getContentPane().add(dialogText);
		this.setVisible(true);
	}

}
