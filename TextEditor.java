package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;

import static java.lang.String.valueOf;

public class TextEditor extends JFrame implements ActionListener,
        ItemListener {

    private final JTextField FilenameJField = new JTextField(25);
    JTextArea textArea = new JTextArea(20, 20);

    public TextEditor() {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JMenuBar menuBar;
        JMenu MenuFile;
        JMenuItem MenuLoad, MenuSave, MenuExit;

        menuBar = new JMenuBar();

        MenuFile = new JMenu("File");
        MenuFile.setName("MenuFile");
        MenuFile.setMnemonic(KeyEvent.VK_A);
        MenuFile.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(MenuFile);

        MenuLoad = new JMenuItem("Load",
                KeyEvent.VK_T);
        MenuLoad.setName("MenuLoad");
        MenuFile.add(MenuLoad);

        MenuSave = new JMenuItem("Save");
        MenuSave.setName("MenuSave");
        MenuSave.setMnemonic(KeyEvent.VK_B);
        MenuFile.add(MenuSave);

        MenuFile.addSeparator();

        MenuExit = new JMenuItem("Exit");
        MenuExit.setName("MenuExit");
        MenuSave.setMnemonic(KeyEvent.VK_B);
        MenuFile.add(MenuExit);


        JPanel panel = new JPanel(new FlowLayout());
        add(panel, BorderLayout.NORTH);


        JButton SaveButton = new JButton(new ImageIcon("save.png"));
        SaveButton.setName("SaveButton");
        panel.add(SaveButton);

        JButton LoadButton = new JButton(new ImageIcon("load.png"));
        LoadButton.setName("LoadButton");
        panel.add(LoadButton);

        FilenameJField.setName("FilenameField");
        String filename = FilenameJField.getText();
        System.out.println(filename);
        panel.add(FilenameJField);

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
            boolean exists = file.exists();
            if (exists) {
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write(text);
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        LoadButton.addActionListener(e -> {

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
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (!exists) {
                textArea.setText("");
            }
        });

        MenuSave.addActionListener(e -> {
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

        MenuLoad.addActionListener(e -> {

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
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (!exists) {
                textArea.setText("");
            }
        });

        MenuExit.addActionListener(e -> {
            dispose();
        });

        //add(textArea);
        setJMenuBar(menuBar);
        add(scroll);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}

