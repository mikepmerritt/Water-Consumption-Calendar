import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DayWindow extends JFrame {

    private Calendar calendar;
    private MonthWindow monthWindow;
    private InputWindow inputWindow;

    private int day;

    private JPanel mainPanel, insidePanel, bottomPanel,summaryPanel, bottomButtonPanel;
    private JLabel heading, summary, comparison;
    private JLabel[] questions, gallonLabels;
    private JTextField[] inputFields;
    private JButton changeButton, backButton;

    public DayWindow(Calendar calendar) {
        super("Day View");

        this.calendar = calendar;

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new DayWindowListener());
        this.setVisible(false);
    }

    public void loadDay(int day) {
        this.day = day;

        heading = new JLabel("Day " + day, SwingConstants.CENTER);
        heading.setFont(new Font(heading.getFont().getName(), Font.PLAIN, 24));

        mainPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel(new GridLayout(2, 1));

        backButton = new JButton("Back to Month");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close this window or hide it
                monthWindow.reload();
                DayWindow.this.dispose();
            }
        });

        if (calendar.getDay(day) != null) {
            questions = new JLabel[WaterUsage.QUESTIONS.length];
            for (int i = 0; i < questions.length; i++) {
                questions[i] = new JLabel(WaterUsage.QUESTIONS[i]);
            }

            inputFields = new JTextField[questions.length];
            for (int i = 0; i < inputFields.length; i++) {
                inputFields[i] = new JTextField(10);
                inputFields[i].setEditable(false);
                inputFields[i].setText("" + calendar.getDay(day).getAnswer(i));
            }
            
            gallonLabels = new JLabel[questions.length];
            for (int i = 0; i < gallonLabels.length; i++) {
            	gallonLabels[i] = new JLabel("Gallons used: " + ((int) ((Double.parseDouble(inputFields[i].getText()) * WaterUsage.DEFAULT_MULTIPLIERS[i]) * 10) / 10.0)  + " gallons");
            }

            insidePanel = new JPanel(new GridLayout(questions.length, 3, 5, 5));
            for (int i = 0; i < questions.length && i < inputFields.length; i++) {
                insidePanel.add(questions[i]);
                insidePanel.add(inputFields[i]);
                insidePanel.add(gallonLabels[i]);
            }
            
            double totalGallons = calendar.getDay(day).getConsumption();
            summary = new JLabel("You used a total of " + totalGallons + " gallons of water on these activities this day.", SwingConstants.CENTER);
            if(50 - totalGallons < 0) {
            	comparison = new JLabel("The average person uses around 50 gallons of water on these tasks, meaning you used an extra " + (totalGallons - 50) + " gallons of water this day.", SwingConstants.CENTER);
            }
            else if(50 - totalGallons > 0) {
            	comparison = new JLabel("The average person uses around 50 gallons of water on these tasks, meaning you used " + (50 - totalGallons) + " less gallons of water than the average person this day. Good job!", SwingConstants.CENTER);
            }
            else {
            	comparison = new JLabel("The average person uses around 50 gallons of water on these tasks, so you used an average amount of water this day.", SwingConstants.CENTER);
            }
            
            summaryPanel = new JPanel(new GridLayout(2, 1));
            summaryPanel.add(summary);
            summaryPanel.add(comparison);
            bottomPanel.add(summaryPanel);
            
            changeButton = new JButton("Overwrite Data");
        }
        else {
            insidePanel = new JPanel();

            insidePanel.add(new JLabel("No data available for today. Please add this data."));

            questions = null;
            inputFields = null;

            changeButton = new JButton("Add Data");
        }

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputWindow = new InputWindow(calendar);
                inputWindow.addDayWindow(DayWindow.this);
                inputWindow.loadDay(day);
                DayWindow.this.setVisible(false);
            }
        });

        bottomButtonPanel = new JPanel();
        bottomButtonPanel.add(changeButton);
        bottomButtonPanel.add(backButton);
        bottomPanel.add(bottomButtonPanel);
        
        mainPanel.add(insidePanel, BorderLayout.CENTER);
        mainPanel.add(heading, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // spacing
        mainPanel.add(new JPanel(), BorderLayout.WEST);
        mainPanel.add(new JPanel(), BorderLayout.EAST);
        
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }

    public void addMonthWindow(MonthWindow monthWindow) {
        this.monthWindow = monthWindow;
    }

    public void reload() {
        if (inputFields != null) {
            for (int i = 0; i < inputFields.length; i++) {
                inputFields[i].setText("" + calendar.getDay(day).getAnswer(i));
            }
            this.setVisible(true);
        }
        else if (calendar.getDay(day) != null) {
            // need to regenerate entire panel
            this.remove(mainPanel);
            this.remove(bottomPanel);
            this.remove(insidePanel);
            this.remove(heading);

            loadDay(day);
        }
        else {
            this.setVisible(true);
        }
    }

    private class DayWindowListener implements WindowListener {
        @Override
        public void windowClosed(WindowEvent e) {
            monthWindow.reload();
        }

        // unused methods required by interface
        @Override
        public void windowOpened(WindowEvent e) {}
        @Override
        public void windowClosing(WindowEvent e) {}
        @Override
        public void windowIconified(WindowEvent e) {}
        @Override
        public void windowDeiconified(WindowEvent e) {}
        @Override
        public void windowActivated(WindowEvent e) {}
        @Override
        public void windowDeactivated(WindowEvent e) {}
    }

}
