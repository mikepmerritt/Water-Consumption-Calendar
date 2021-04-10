import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupWindow extends JFrame {
	
	private JPanel mainPanel, insidePanel, bottomPanel, headingPanel;
	private JLabel heading;
    private JLabel[] questions;
    private JTextField[] inputFields;
    private JButton[] defaultButtons;
    private JButton changeButton;
    
    private double[] multipliers;
	
    public SetupWindow() {
    	super("Setup");
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setVisible(true);
    	
	    heading = new JLabel("Setup", SwingConstants.CENTER);
	    heading.setFont(new Font(heading.getFont().getName(), Font.PLAIN, 24));
	    
	    
	
	    mainPanel = new JPanel(new BorderLayout());
	    bottomPanel = new JPanel();
	    
	    questions = new JLabel[] {
	    		new JLabel("What is the gallons per flush for your toilet?"),
	    		new JLabel("How efficient is your washing machine in terms of gallons per load?"),
	    		new JLabel("What is the average flow rate of your showerhead in gallons per minute?")
	    };
	    
	    inputFields = new JTextField[questions.length];
        for (int i = 0; i < inputFields.length; i++) {
            inputFields[i] = new JTextField(10);
            inputFields[i].setText("0.0");
        }
	    
        double[] defaults = new double[] {1.6, 14.0, 2.1};
        defaultButtons = new JButton[questions.length];
        for (int i = 0; i < defaultButtons.length; i++) {
        	defaultButtons[i] = new JButton("Set to default");
        	defaultButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					inputFields[i].setText("" + defaults[i]);
				}
        	});
        }
        
        //bottomPanel.add(changeButton);

        //mainPanel.add(insidePanel, BorderLayout.CENTER);
        mainPanel.add(heading, BorderLayout.NORTH);
        //mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // spacing
        mainPanel.add(new JPanel(), BorderLayout.WEST);
        mainPanel.add(new JPanel(), BorderLayout.EAST);
        
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

    }
	
    public double[] getMultipliers() {
    	return multipliers;
    }
    
}
