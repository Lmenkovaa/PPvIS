package view;

import controller.ControllerMain;
import model.PetInformation;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.lang.String;

public class FindFrame extends JFrame {

    private ControllerMain controllerMain;
    private TablePanel tablePanel;
    private ChoosePanel choosePanel = new ChoosePanel();
    private JButton find;

    FindFrame(ControllerMain controllerMain) {
        super("Find frame");
        this.controllerMain = controllerMain;
        setSize(500, 320);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        find = new JButton("Find");
        find.addActionListener(actionEvent -> {
            List<PetInformation> list = choosePanel.getPetInformationFromFields();
            ControllerMain temp = new ControllerMain();
            temp.setNotes(this.controllerMain.FindPetInformation(list.get(0).getNamePet(), list.get(0).getDateOfBirth(),
                    list.get(0).getLastDate(), list.get(0).getDoctor(), list.get(0).getDiagnosis()));
            tablePanel.setNotes(temp);
            tablePanel.showTable(temp);
        });

        tablePanel = new TablePanel(controllerMain);
        JPanel panel = new JPanel();

        add(choosePanel);
        add(find);
        add(panel);
        add(tablePanel);

    }

    private JButton getFindButton() {
        return find;
    }
}
