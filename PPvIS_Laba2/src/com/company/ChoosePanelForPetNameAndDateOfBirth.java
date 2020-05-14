package com.company;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChoosePanelForPetNameAndDateOfBirth extends JPanel {
    private JPanel textPanel;
    private JTextField pet_name;
    public ControllerMain controllerMainBirth = new ControllerMain();
    public ControllerForCalendar controllerForCalendarBirth = new ControllerForCalendar();
    public JDatePickerImpl jDatePickerForDateOfBirth;

    ChoosePanelForPetNameAndDateOfBirth(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        createLabels();
        createText();

    }
    private void createLabels(){
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

        JLabel pet_name = new JLabel("              Pet name");
        JLabel date_of_birth = new JLabel("Date of birth");

        labelPanel.add(pet_name);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(date_of_birth);
        labelPanel.add(Box.createHorizontalGlue());

        add(labelPanel);
    }
    private void createText(){
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));

        final int WIDTH = 200;
        final int HEIGHT = 20;

        pet_name = new JTextField();
        pet_name.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        pet_name.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        textPanel.add(pet_name);
        jDatePickerForDateOfBirth = controllerForCalendarBirth.createCalendar(jDatePickerForDateOfBirth,WIDTH,HEIGHT,textPanel);

        add(textPanel);
    }

    public List<String> getInfoesFromFields(){
        List<String> result = new ArrayList<>();
        String resDate = String.valueOf(jDatePickerForDateOfBirth.getJFormattedTextField().getText());

        result.add(pet_name.getText());
        result.add(resDate);


        return  result;
    }

    public String getFieldPetName(){
        return pet_name.getText();
    }
}
