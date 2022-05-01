package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;

import static java.lang.String.valueOf;

public class TextEditor extends JFrame {
    private final JTextField FilenameJField = new JTextField(25);
    JTextArea textArea = new JTextArea(20, 20);

    public TextEditor() {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout());
        add(panel, BorderLayout.NORTH);


        FilenameJField.setName("FilenameField");
        String filename = FilenameJField.getText();
        System.out.println(filename);

        panel.add(FilenameJField, FlowLayout.LEFT);

        JButton SaveButton = new JButton("Save");

        SaveButton.setName("SaveButton");
        panel.add(SaveButton, FlowLayout.CENTER);

        JButton LoadButton = new JButton("Load");
        LoadButton.setName("LoadButton");
        panel.add(LoadButton, FlowLayout.RIGHT);


        textArea.setName("TextArea");
        //  textArea.setBounds(20, 20, 260, 230);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setName("ScrollPane");
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        SaveButton.addActionListener(e -> {
            String text = textArea.getText();
            String name = FilenameJField.getText();

            File file = new File(name);
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(text);
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);

            }
        });

        LoadButton.addActionListener(e1 -> {

            String name = FilenameJField.getText();
            File file = new File(name);
            String text = "";
            boolean exists = file.exists();
            if (exists) {
                try (FileReader fr = new FileReader(file)) {
                    int content;
                    while ((content = fr.read()) != -1) {
                        text += ((char) content);
                    }
                    textArea.setText(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (!exists) {
                textArea.setText("");
            }
        });

        //add(textArea);

        add(scroll);
        setVisible(true);
    }


}

