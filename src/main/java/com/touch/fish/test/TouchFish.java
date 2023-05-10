package com.touch.fish.test;

import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;

public class TouchFish {

    private JPanel jPanel;
    private JButton changeFile;
    private JTextField filePath;
    private JLabel fileLable;

    public TouchFish() {
        // 给按钮添加一个选择文件的事件
        changeFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(jPanel);
            File file = fileChooser.getSelectedFile();
            filePath.setText(file.getPath());
        });

    }

    public JComponent getComponent() {
        return jPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return filePath;
    }

    public JTextField getUrlTextField() {
        return filePath;
    }

    public String getFilePath() {
        return filePath.getText();
    }

    public void setFilePath(@NotNull String filePath) {
        this.filePath.setText(filePath);
    }
}
