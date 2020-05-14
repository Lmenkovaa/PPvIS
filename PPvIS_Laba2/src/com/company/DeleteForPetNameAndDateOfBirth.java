package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeleteForPetNameAndDateOfBirth extends JFrame {
    private ControllerMain controllerMain;
    private ChoosePanelForPetNameAndDateOfBirth choosePanelForPetNameAndDateOfBirth = new ChoosePanelForPetNameAndDateOfBirth();
    private JButton deleteForPetNameAndDateOfBirthButton;

    DeleteForPetNameAndDateOfBirth(ControllerMain controllerMain){
        super("Удаление из таблицы по имени и дате рождения");
        this.controllerMain = controllerMain;

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        init();
        setSize(500,120);
        setResizable(false);
    }

    private void init(){
        deleteForPetNameAndDateOfBirthButton = new JButton("Delete");
        deleteForPetNameAndDateOfBirthButton.setMaximumSize( new Dimension(100,20));

        add(choosePanelForPetNameAndDateOfBirth);
        add(deleteForPetNameAndDateOfBirthButton);
    }

    public int deleteNotes(){
        List<String> list = choosePanelForPetNameAndDateOfBirth.getInfoesFromFields();
        return this.controllerMain.deleteNotesForPetNameAndDateOfBirth(list.get(0), list.get(1));
    }

    public JButton getDeleteForPetNameAndDateOfBirthButton(){
        return deleteForPetNameAndDateOfBirthButton;
    }
}
