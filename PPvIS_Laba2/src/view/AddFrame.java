package view;

import model.PetInformation;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import controller.ControllerMain;

public class AddFrame extends JFrame {

    ControllerMain controllerMainFirst;
    ChoosePanel choosePanel = new ChoosePanel();

    private JButton add;

    AddFrame(ControllerMain controllerMain) {
        super("Add new record to table");
        this.controllerMainFirst = controllerMain;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add = new JButton("Add");
        add.setMaximumSize(new Dimension(100, 20));

        add(choosePanel);
        add(add);

        setSize(500, 120);
        setResizable(false);
    }

    public void addNotes() {
        String tempPetName = choosePanel.getFieldPetName();
        Date tempDateOfBirth = choosePanel.getFieldDateOfBirth();
        Date tempLastDate = choosePanel.getFieldLastDate();
        String tempDoctor = choosePanel.getFieldDoctor();
        String tempDiagnosis = choosePanel.getFieldDiagnosis();
        controllerMainFirst.addPetInformation(new PetInformation(tempPetName, tempDateOfBirth, tempLastDate, tempDoctor, tempDiagnosis));
    }

    public JButton addButton() {
        return add;
    }

}
