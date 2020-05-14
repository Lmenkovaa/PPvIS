package com.company;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ControllerMain {
    private List <Info> tableInfo = new ArrayList<Info>();
    private List<String> columnNames;

    private AbstractTableModel tableModel;
    private boolean boolean_pet_name;
    private boolean boolean_date_of_birth;
    private boolean boolean_last_date;
    private boolean boolean_doctor;
    private boolean boolean_diagnosis;

    public JDatePickerImpl createCalendar(JDatePickerImpl datePicker, int x, int y, JPanel panel){
        Properties properties = new Properties();
        properties.put("text.today", "today");
        properties.put("text.month", "month");
        properties.put("text.year", "year");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        datePicker.setMaximumSize(new Dimension(x,y));
        panel.add(datePicker);
        return datePicker;
    }


    private List<Info> FindTemplateForPetNameAndDateOfBirth (String pet_name, String date_of_birth ){
        List<Info> temp = new ArrayList<>();
        for(Info tableInfos: tableInfo){
            boolean boolIs = (tableInfos.getName_pet().equals(pet_name) || boolean_pet_name) &&
                    (tableInfos.getDate_of_birth().equals(date_of_birth) || boolean_date_of_birth);
            if(boolIs){
                temp.add(tableInfos);
            }
        }
        return temp;
    }

    private List<Info> FindTemplateForLastDateAndDoctor(String last_date, String doctor){
        List<Info> temp = new ArrayList<>();
        for(Info tableInfos: tableInfo){
            boolean boolIs = (tableInfos.getLast_date().equals(last_date) || boolean_last_date) &&
                    (tableInfos.getDoctor().equals(doctor) || boolean_doctor);
            if(boolIs){
                temp.add(tableInfos);
            }
        }
        return temp;
    }

    private List<Info> FindTemplateForDiagnosis(String diagnosis){
        List<Info> temp = new ArrayList<>();
        for(Info tableInfos: tableInfo){
            boolean boolIs = (tableInfos.getDiagnosis().equals(diagnosis) || boolean_diagnosis);
            if(boolIs){
                temp.add(tableInfos);
            }
        }
        return temp;
    }

    public List<Info> FindNotesForNameAndDate (String pet_name, String date_of_birth ){
        boolean_pet_name = (pet_name.equals(""));
        boolean_date_of_birth = (date_of_birth.equals(""));
        return FindTemplateForPetNameAndDateOfBirth(pet_name, date_of_birth);
    }

    public List<Info> FindNotesForLastDateAndDoctor(String last_date, String doctor){
        boolean_last_date = (last_date.equals(""));
        boolean_doctor = (doctor.equals(""));
        return FindTemplateForLastDateAndDoctor(last_date,doctor);
    }

    public List<Info> FindNotesForDiagnosis(String diagnosis){
        boolean_diagnosis = (diagnosis.equals(""));
        return FindTemplateForDiagnosis(diagnosis);
    }
    public void Add(Info info){
        tableInfo.add(info);
    }

    public void Read( String path ) throws ParserConfigurationException, SAXException, IOException {
        SAXNew saxNew = new SAXNew();
        saxNew.parse(path);
        tableInfo = saxNew.getInfoes();
    }

    public void Write(String path){
        DOMNew domNew = new DOMNew();
        domNew.setInfoes(tableInfo, path);
        domNew.setBooks();
    }

    public int deleteNotesForPetNameAndDateOfBirth(String pet_name,String date_of_birth){
        boolean_pet_name = (pet_name.equals(""));
        boolean_date_of_birth = (date_of_birth.equals(""));

        List<Info> temp = FindTemplateForPetNameAndDateOfBirth(pet_name, date_of_birth);
        int amount = temp.size();
        tableInfo.removeAll(temp);
        return amount;
    }

    public int deleteNotesForLastDateAndDoctor(String last_date,String doctor){
        boolean_last_date = (last_date.equals(""));
        boolean_doctor = (doctor.equals(""));
        List<Info> temp = FindTemplateForLastDateAndDoctor(last_date, doctor);
        int amount = temp.size();
        tableInfo.removeAll(temp);
        return amount;
    }

    public int deleteNotesForDiagnosis(String diagnosis){
        boolean_diagnosis = (diagnosis.equals(""));
        List<Info> temp = FindTemplateForDiagnosis(diagnosis);
        int amount = temp.size();
        tableInfo.removeAll(temp);
        return amount;
    }

    public boolean isExists(int index){
        return index < tableInfo.size();
    }

    public Info atIndex(int index){
        return tableInfo.get(index);
    }

    public void setNotes(List<Info> info){
        tableInfo = info;
    }
}
