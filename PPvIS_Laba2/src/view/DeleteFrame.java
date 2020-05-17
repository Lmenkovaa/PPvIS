package view;

import controller.ControllerMain;
import model.PetInformation;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.lang.String;

public class DeleteFrame extends JFrame {
    private ControllerMain controllerMain;
    private ChoosePanel choosePanel = new ChoosePanel();
    private JButton delete;

    DeleteFrame(ControllerMain controllerMain) {
        super("Delete frame");
        this.controllerMain = controllerMain;

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        init();
        setSize(500, 120);
        setResizable(false);
    }

    private void init() {
        delete = new JButton("Delete");
        delete.setMaximumSize(new Dimension(100, 20));

        add(choosePanel);
        add(delete);
    }

    int deletePetInfo() {
        ArrayList<PetInformation> list = choosePanel.getPetInformationFromFields();
        return this.controllerMain.DeletePetInformation(list.get(0).getNamePet(), list.get(0).getDateOfBirth(),
                list.get(0).getLastDate(), list.get(0).getDoctor(), list.get(0).getDiagnosis());
    }

    public JButton getDeleteButton() {
        return delete;
    }
}
