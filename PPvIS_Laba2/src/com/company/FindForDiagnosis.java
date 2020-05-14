package com.company;
import javax.swing.*;
import java.util.List;
public class FindForDiagnosis extends JFrame{
    private  ControllerMain controllerMain;

    JButton findButton3;

    private TablePanel tablePanel;

    private ChoosePanelForDiagnosis choosePanelForDiagnosis = new ChoosePanelForDiagnosis();

    FindForDiagnosis(ControllerMain controllerMain){
        super("Поиск в таблице по диагнозу");
        this.controllerMain = controllerMain;
        setSize(500,320);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        findButton3 = new JButton("Поиск");
        findButton3.addActionListener(actionEvent ->{
            List<String> list = choosePanelForDiagnosis.getInfoesFromFields();
            ControllerMain temp = new ControllerMain();
            temp.setNotes(this.controllerMain.FindNotesForDiagnosis(list.get(0)));
            tablePanel.setNotes(temp);
            tablePanel.showTable(temp);
        });

        tablePanel = new TablePanel(controllerMain);
        JPanel pan = new JPanel();
        pan.add(findButton3);

        add(choosePanelForDiagnosis);
        add(pan);
        add(tablePanel);
    }
}
