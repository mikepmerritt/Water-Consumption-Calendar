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

    private JPanel mainPanel, insidePanel, bottomPanel;
    private JLabel heading;
    private JLabel[] questions;
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
        bottomPanel = new JPanel();

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

            insidePanel = new JPanel(new GridLayout(questions.length, 2, 5, 5));
            for (int i = 0; i < questions.length && i < inputFields.length; i++) {
                insidePanel.add(questions[i]);
                insidePanel.add(inputFields[i]);
            }
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

        bottomPanel.add(changeButton);
        bottomPanel.add(backButton);

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
        }
        else {
            // need to regenerate entire panel
        }
        this.setVisible(true);
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
