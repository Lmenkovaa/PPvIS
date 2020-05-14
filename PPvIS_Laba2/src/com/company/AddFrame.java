package com.company;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AddFrame extends JFrame{
    JPanel labelPanel;
    JPanel textPanel;

    ControllerMain controllerMain;
    ControllerMain controllerMainFirst;

    JTextField pet_name;
    JTextField doctor;
    JTextField diagnosis;

    JDatePickerImpl datePickerForLastDate, datePickerForDateOfBirth;
    JButton add;

    AddFrame(ControllerMain controllerMain){
        super("Добавление записи в таблицу");
        this.controllerMainFirst = controllerMain;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add = new JButton("Добавить запись");
        add.setMaximumSize(new Dimension(200,30));
        createFields();
        createLabels();

        add(labelPanel);
        add(textPanel);
        add(add);

        setSize(600,200);
        setResizable(false);

    }
    public void createLabels(){
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

        JLabel pet_name = new JLabel("Pet name");
        JLabel date_of_birth = new JLabel(" Date of birth");
        JLabel last_date = new JLabel("       Last Date");
        JLabel doctor = new JLabel("            Doctor");
        JLabel diagnosis = new JLabel("      Diagnosis");

        labelPanel.add(pet_name);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(date_of_birth);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(last_date);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(doctor);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(diagnosis);
        labelPanel.add(Box.createHorizontalGlue());

    }
    public void createFields(){
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.LINE_AXIS));

        controllerMain = new ControllerMain();

        final int WIDTH = 200;
        final int HEIGHT = 20;

        pet_name = new JTextField();
        pet_name.setMaximumSize(new Dimension(WIDTH, HEIGHT));


        doctor= new JTextField();
        doctor.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        diagnosis = new JTextField();
        diagnosis.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        textPanel.add(pet_name);
        datePickerForDateOfBirth = controllerMain.createCalendar(datePickerForDateOfBirth,90,20,textPanel);
        datePickerForLastDate = controllerMain.createCalendar(datePickerForLastDate, 90,20, textPanel);
        textPanel.add(doctor);
        textPanel.add(diagnosis);
    }

    public void addNotes(){
        String tempPetName = pet_name.getText();
        String tempDateOfBirthCal = String.valueOf(datePickerForDateOfBirth.getJFormattedTextField().getText());
        String tempLastDateCal = String.valueOf(datePickerForLastDate.getJFormattedTextField().getText());
        String tempDoctor = doctor.getText();
        String tempDiagnosis = diagnosis.getText();
        System.out.println(tempLastDateCal);
        System.out.println(tempDateOfBirthCal);
        controllerMainFirst.Add(new Info(tempPetName, tempDateOfBirthCal, tempLastDateCal, tempDoctor, tempDiagnosis));
    }
}
