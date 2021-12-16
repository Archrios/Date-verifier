import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;

public class DataCounterPanel extends JPanel
{
	private DataVerifier data;
	private JLabel startDate;
	private JLabel endDate;

	private JTextField startDateField;
	private JTextField endDateField;

	private JButton countbutton;

	private int day = LocalDateTime.now().getDayOfMonth();
	private int month = LocalDateTime.now().getMonthValue();
	private int year = LocalDateTime.now().getYear();
	private String today = "" + day + "/" + month + "/" + year;
	private String defaultStart = "1/1/2001";

	/**
	 * default constructor of DataCounterPanel
	 */
	public DataCounterPanel()
	{
		Font fieldFont = new Font("sansSerif", Font.BOLD, 24);
		Font otherFont = new Font("Monospaced", Font.BOLD, 24);

		startDate = new JLabel("Start Date: ");
		endDate = new JLabel("End Date: ");
		startDate.setFont(otherFont);
		endDate.setFont(otherFont);
		this.add(startDate);

		startDateField = new JTextField();
		startDateField.setFont(fieldFont);
		startDateField.setText(defaultStart);
		this.add(startDateField);

		this.add(endDate);
		endDateField = new JTextField();
		endDateField.setFont(fieldFont);
		endDateField.setText(today);
		this.add(endDateField);

		countbutton = countButton("count");
		countbutton.setFont(otherFont);
		this.add(countbutton);
	}

	/**
	 * Takes in string and creates a button with the string as a title 
	 * sets data as a new DataVerifier using the text from startDateField and endDateField
	 * tries to procedurally goes through various check methods from the data Verifier class
	 * any thrown error messages are then caught and a popup window made using the msg 
	 * of the exception thrown.
	 * 
	 * @param title
	 * @return JButton
	 */
	private JButton countButton(String title)
	{
		class CountListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					data = new DataVerifier(startDateField.getText(), endDateField
					      .getText());
					data.formatCheck();
					data.startCheck();
					data.endCheck();
					data.dateDifference();
				} catch (NumberFormatException exception)
				{
					JOptionPane.showMessageDialog(null, exception.getMessage());
				} catch (DateTimeException exception)
				{
					JOptionPane.showMessageDialog(null, exception.getMessage());
				} catch (CPSC1181Exception exception)
				{
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}
			}
		}
		JButton button = new JButton(title);
		button.addActionListener(new CountListener());
		return button;
	}
}
