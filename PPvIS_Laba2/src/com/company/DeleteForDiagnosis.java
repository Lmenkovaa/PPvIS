package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeleteForDiagnosis extends  JFrame{

    private ControllerMain controllerMainDiagnosis;
    private ChoosePanelForDiagnosis choosePanelForDiagnosis = new ChoosePanelForDiagnosis();
    private JButton deleteForDiagnosisButton;
    private JPanel newPanel;

    DeleteForDiagnosis (ControllerMain controllerMain){
        super("Удаление из таблицы по диагнозу");
        this.controllerMainDiagnosis = controllerMain;

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        init();
        setSize(500,120);
        setResizable(false);

    }

    private void init(){
        newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.LINE_AXIS));

        deleteForDiagnosisButton = new JButton("Delete");
        deleteForDiagnosisButton.setMaximumSize(new Dimension(100,20));

        add(choosePanelForDiagnosis);
        add(deleteForDiagnosisButton);
    }

    public int deleteNotes(){
        List<String> list = choosePanelForDiagnosis.getInfoesFromFields();
        return this.controllerMainDiagnosis.deleteNotesForDiagnosis(list.get(0));
    }

    public JButton getDeleteForDiagnosisButton(){
        return deleteForDiagnosisButton;
    }
}
