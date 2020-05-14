package com.company;

import javax.swing.*;
import java.util.List;

public class FindForPetNameAndDateOfBirthFrame extends JFrame{
    private  ControllerMain controllerMain;
    
    JButton findButton;
    
    private TablePanel tablePanel;
    
    private ChoosePanelForPetNameAndDateOfBirth choosePanelForPetNameAndDateOfBirth = new ChoosePanelForPetNameAndDateOfBirth();
    
    FindForPetNameAndDateOfBirthFrame(ControllerMain controllerMain){
        super("Поиск в таблице по имени и дате рождения питомца");
        this.controllerMain = controllerMain;
        setSize(500,320);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        findButton = new JButton("Поиск");
        findButton.addActionListener(actionEvent ->{
            List<String> list = choosePanelForPetNameAndDateOfBirth.getInfoesFromFields();
            ControllerMain temp = new ControllerMain();
            temp.setNotes(this.controllerMain.FindNotesForNameAndDate(list.get(0), list.get(1)));
            tablePanel.setNotes(temp);
            tablePanel.showTable(temp);
        });
        
        tablePanel = new TablePanel(controllerMain);
        JPanel pan = new JPanel();
        pan.add(findButton);
        
        add(choosePanelForPetNameAndDateOfBirth);
        add(pan);
        add(tablePanel);
    }
}
