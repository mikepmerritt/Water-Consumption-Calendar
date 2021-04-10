import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DayWindow extends JFrame {

    private Calendar calendar;
    private JPanel mainPanel, insidePanel, bottomPanel;
    private JLabel heading;
    private JLabel[] questions;
    private JTextField[] inputFields;
    private JButton changeButton, backButton;

    public DayWindow(Calendar calendar) {
        super("Day View");

        this.calendar = calendar;

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
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

}
