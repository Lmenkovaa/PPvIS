package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChoosePanelForDiagnosis extends JPanel {
    private JPanel textPanel;
    private JTextField diagnosis;
    ChoosePanelForDiagnosis(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        createLabels();
        createText();

    }
    private void createLabels(){
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

        JLabel diagnosis = new JLabel("             Diagnosis");

        labelPanel.add(diagnosis);
        labelPanel.add(Box.createHorizontalGlue());

        add(labelPanel);
    }
    private void createText(){
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));

        final int WIDTH = 400;
        final int HEIGHT = 20;

        diagnosis = new JTextField();
        diagnosis.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        diagnosis.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        textPanel.add(diagnosis);

        add(textPanel);
    }

    public List<String> getInfoesFromFields(){

        List<String> result = new ArrayList<>();
        result.add(diagnosis.getText());
        return  result;
    }

    public String getDiagnosis(){
        return diagnosis.getText();
    }
}