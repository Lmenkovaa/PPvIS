package view;

import controller.ControllerForCalendar;
import controller.ControllerMain;
import model.PetInformation;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.*;


public class ChoosePanel extends JPanel {

    private JPanel textPanel;
    private JTextField petNameField;
    private JTextField doctorField;
    private JTextField diagnosisField;
    private JDatePickerImpl jDatePickerForDateOfBirth;


    final long millisecondsToRoundTime = 86400000;
    long millisecondsToRoundThreeHours = 10800000;
    private JDatePickerImpl jDatePickerForLastDate;
    private ControllerForCalendar controllerForCalendar = new ControllerForCalendar();

    public ChoosePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        createLabels();
        createText();
    }

    private void createLabels() {
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

        JLabel petNameLabel = new JLabel("PET NAME");
        JLabel dateOfBirthLabel = new JLabel("DATE OF BIRTH");
        JLabel lastDateLabel = new JLabel("LAST DATE");
        JLabel doctorLabel = new JLabel("DOCTOR");
        JLabel diagnosisLabel = new JLabel("DIAGNOSIS");

        labelPanel.add(petNameLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(dateOfBirthLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(lastDateLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(doctorLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(diagnosisLabel);

        add(labelPanel);
    }

    private void createText() {
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));

        final int WIDTH = 100;
        final int HEIGHT = 20;

        petNameField = new JTextField();
        petNameField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        petNameField.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        jDatePickerForDateOfBirth = controllerForCalendar.createCalendar(jDatePickerForDateOfBirth, WIDTH, HEIGHT, textPanel);
        jDatePickerForLastDate = controllerForCalendar.createCalendar(jDatePickerForLastDate, WIDTH, HEIGHT, textPanel);
        doctorField = new JTextField();
        doctorField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        doctorField.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        diagnosisField = new JTextField();
        diagnosisField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        diagnosisField.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        textPanel.add(petNameField);
        textPanel.add(jDatePickerForDateOfBirth);
        textPanel.add(jDatePickerForLastDate);
        textPanel.add(doctorField);
        textPanel.add(diagnosisField);

        add(textPanel);
    }

    public ArrayList<PetInformation> getPetInformationFromFields() {
        ArrayList<PetInformation> result = new ArrayList<>();
        PetInformation petInformation = new PetInformation(getFieldPetName(), getFieldDateOfBirth(),
                getFieldLastDate(), getFieldDoctor(), getFieldDiagnosis());
        result.add(petInformation);

        return result;
    }

    public String getFieldPetName() {
        return petNameField.getText();
    }

    public String getFieldDoctor() {
        return doctorField.getText();
    }

    public String getFieldDiagnosis() {
        return diagnosisField.getText();
    }

    public Date getFieldDateOfBirth() {
        Date dateBirth = (Date) jDatePickerForDateOfBirth.getModel().getValue();
        if (dateBirth == null) {
            return null;
        }
        long time = 0;
        time = dateBirth.getTime();
        long newTime = time - time % millisecondsToRoundTime - millisecondsToRoundThreeHours;
        dateBirth.setTime(newTime);
        return dateBirth;
    }

    public Date getFieldLastDate() {
        Date dateLast = (Date) jDatePickerForLastDate.getModel().getValue();
        if (dateLast == null) {
            return null;
        }
        long time = 0;
        time = dateLast.getTime();
        long newTime = time - time % millisecondsToRoundTime - millisecondsToRoundThreeHours;
        dateLast.setTime(newTime);
        return dateLast;
    }
}
