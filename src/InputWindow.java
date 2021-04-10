import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class InputWindow extends JFrame {

    private Calendar calendar;
    private DayWindow dayWindow;

    private JPanel mainPanel, insidePanel, bottomPanel;
    private JLabel heading;
    private JLabel[] questions;
    private JTextField[] inputFields;
    private JButton submitButton, backButton;

    public InputWindow(Calendar calendar) {
        super("Input Values");

        this.calendar = calendar;

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new InputWindowListener());
        this.setVisible(false);
    }

    public void loadDay(int day) {
        heading = new JLabel("Day " + day, SwingConstants.CENTER);
        heading.setFont(new Font(heading.getFont().getName(), Font.PLAIN, 24));

        mainPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel();

        backButton = new JButton("Back to Month");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close this window or hide it
                dayWindow.reload();
                InputWindow.this.dispose();
            }
        });

        questions = new JLabel[WaterUsage.QUESTIONS.length];
        for (int i = 0; i < questions.length; i++) {
            questions[i] = new JLabel(WaterUsage.QUESTIONS[i]);
        }

        inputFields = new JTextField[questions.length];
        for (int i = 0; i < inputFields.length; i++) {
            inputFields[i] = new JTextField(10);
        }

        insidePanel = new JPanel(new GridLayout(questions.length, 2, 5, 5));
        for (int i = 0; i < questions.length && i < inputFields.length; i++) {
            insidePanel.add(questions[i]);
            insidePanel.add(inputFields[i]);
        }
        submitButton = new JButton("Submit");

        bottomPanel.add(submitButton);
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

    public void addDayWindow(DayWindow dayWindow) {
        this.dayWindow = dayWindow;
    }

    private class InputWindowListener implements WindowListener {
        @Override
        public void windowClosed(WindowEvent e) {
            dayWindow.reload();
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
