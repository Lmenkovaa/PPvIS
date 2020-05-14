package com.company;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ChoosePanelForLastDateAndDoctor extends JPanel {
    private JPanel textPanel;
    private JTextField doctor;
    public ControllerMain controllerMain = new ControllerMain();
    public JDatePickerImpl jDatePickerForLastDate;

    public ControllerForCalendar controllerForCalendar = new ControllerForCalendar();

    ChoosePanelForLastDateAndDoctor(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        createLabels();
        createText();

    }
    private void createLabels(){
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

        JLabel last_date = new JLabel("             last date");
        JLabel doctor = new JLabel("Doctor");

        labelPanel.add(last_date);
        labelPanel.add(Box.createHorizontalGlue());

        labelPanel.add(doctor);
        labelPanel.add(Box.createHorizontalGlue());



        add(labelPanel);
    }

    private void createText(){

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        jDatePickerForLastDate = controllerForCalendar.createCalendar(jDatePickerForLastDate,200,20,textPanel);

        doctor = new JTextField();
        doctor.setMaximumSize(new Dimension(200,20));
        doctor.setMinimumSize(new Dimension(200,20));

        textPanel.add(doctor);
        add(textPanel);
    }



    public List<String> getInfoesFromFields(){
        List<String> result = new ArrayList<>();
        String resDate = String.valueOf(jDatePickerForLastDate.getJFormattedTextField().getText());
        result.add(resDate);
        result.add(doctor.getText());

        System.out.println(result);
        return  result;
    }

    public String getFieldDoctor(){
        return doctor.getText();
    }
}