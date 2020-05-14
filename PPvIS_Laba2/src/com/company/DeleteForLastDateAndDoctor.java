package com.company;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class DeleteForLastDateAndDoctor extends JFrame{
    private ControllerMain controllerMain;
    private ChoosePanelForLastDateAndDoctor choosePanelForLastDateAndDoctor = new ChoosePanelForLastDateAndDoctor();
    private JButton deleteForLastDateAndDoctorButton;
    private JPanel newPanel;


    DeleteForLastDateAndDoctor (ControllerMain controllerMain){
        super("Удаление из таблицы по последней дате визита и ФИО доктора");
        this.controllerMain = controllerMain;


        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        init();
        setSize(500,120);
        setResizable(false);

    }

    private void init(){
        newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.LINE_AXIS));

        deleteForLastDateAndDoctorButton = new JButton("Delete");
        deleteForLastDateAndDoctorButton.setMaximumSize(new Dimension(100,20));

        add(choosePanelForLastDateAndDoctor);
        add(deleteForLastDateAndDoctorButton);

    }

    public int deleteNotes(){
        List<String> list = choosePanelForLastDateAndDoctor.getInfoesFromFields();
        System.out.println(list);

        return this.controllerMain.deleteNotesForLastDateAndDoctor(list.get(0), list.get(1));
    }

    public JButton getDeleteForLastDateAndDoctorButton(){
        return deleteForLastDateAndDoctorButton;
    }

}
