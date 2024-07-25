import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonalDiaryApp extends JFrame {

    private JTextArea diaryTextArea;

    public PersonalDiaryApp() {
        setTitle("Personal Diary App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Personal Diary");
        titleLabel.setBounds(150, 10, 150, 25);
        panel.add(titleLabel);

        diaryTextArea = new JTextArea();
        diaryTextArea.setBounds(20, 40, 340, 160);
        panel.add(diaryTextArea);

        JButton saveButton = new JButton("Save Entry");
        saveButton.setBounds(150, 210, 120, 25);
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDiaryEntry();
            }
        });
    }

    private void saveDiaryEntry() {
        String entryText = diaryTextArea.getText();

        if (!entryText.trim().isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String timestamp = dateFormat.format(new Date());
                String fileName = "diary_entry_" + timestamp + ".txt";

                FileWriter writer = new FileWriter(fileName);
                writer.write(entryText);
                writer.close();

                JOptionPane.showMessageDialog(this, "Diary entry saved successfully!");
                diaryTextArea.setText("");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving diary entry.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Diary entry cannot be empty.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PersonalDiaryApp();
            }
        });
    }
}