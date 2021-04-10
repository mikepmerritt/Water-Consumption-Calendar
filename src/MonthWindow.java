import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonthWindow extends JFrame {

    private Calendar calendar;
    private DayWindow dayWindow;

    private JPanel mainPanel, insidePanel;
    private JLabel heading;
    private JButton[] buttons;
    private JLabel[] days = {
            new JLabel("Sun", SwingConstants.CENTER),
            new JLabel("Mon", SwingConstants.CENTER),
            new JLabel("Tue", SwingConstants.CENTER),
            new JLabel("Wed", SwingConstants.CENTER),
            new JLabel("Thu", SwingConstants.CENTER),
            new JLabel("Fri", SwingConstants.CENTER),
            new JLabel("Sat", SwingConstants.CENTER)
    };

    public MonthWindow(Calendar calendar, DayWindow dayWindow) {
        super("Month View");

        this.calendar = calendar;
        this.dayWindow = dayWindow;

        heading = new JLabel(calendar.getName(), SwingConstants.CENTER);
        heading.setFont(new Font(heading.getFont().getName(), Font.PLAIN, 24));
        buttons = new JButton[calendar.getEndDate()];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("" + (i + 1));
            buttons[i].addActionListener(new ButtonListener(i + 1));
        }

        mainPanel = new JPanel(new BorderLayout());

        insidePanel = new JPanel(new GridLayout(6, 7, 5, 5));
        for (JLabel day : days) {
            insidePanel.add(day);
        }

        for (int i = 0; i < calendar.getStartDay(); i++) {
            insidePanel.add(new JLabel()); // empty content
        }

        for (JButton dayButton : buttons) {
            insidePanel.add(dayButton);
        }

        mainPanel.add(insidePanel, BorderLayout.CENTER);
        mainPanel.add(heading, BorderLayout.NORTH);

        // spacing
        mainPanel.add(new JPanel(), BorderLayout.WEST);
        mainPanel.add(new JPanel(), BorderLayout.EAST);
        mainPanel.add(new JPanel(), BorderLayout.SOUTH);

        this.add(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);
    }

    private class ButtonListener implements ActionListener {

        private int day;

        public ButtonListener(int day) {
            this.day = day;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println(calendar.getDay(day).getConsumption());
            dayWindow.loadDay(day);
        }
    }

}
