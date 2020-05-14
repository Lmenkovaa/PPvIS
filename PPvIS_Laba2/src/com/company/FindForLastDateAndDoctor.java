package com.company;
import javax.swing.*;
import java.util.List;
public class FindForLastDateAndDoctor extends JFrame{
    private  ControllerMain controllerMain;

    JButton findButton2;

    private TablePanel tablePanel;

    private ChoosePanelForLastDateAndDoctor choosePanelForLastDateAndDoctor = new ChoosePanelForLastDateAndDoctor();

    FindForLastDateAndDoctor(ControllerMain controllerMain){
        super("Поиск в таблице по дате последнего визита и ФИО доктора");
        this.controllerMain = controllerMain;
        setSize(500,320);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        findButton2 = new JButton("Поиск");
        findButton2.addActionListener(actionEvent ->{
            List<String> list = choosePanelForLastDateAndDoctor.getInfoesFromFields();
            ControllerMain temp = new ControllerMain();
            temp.setNotes(this.controllerMain.FindNotesForLastDateAndDoctor(list.get(0), list.get(1)));
            tablePanel.setNotes(temp);
            tablePanel.showTable(temp);
        });

        tablePanel = new TablePanel(controllerMain);
        JPanel pan = new JPanel();
        pan.add(findButton2);

        add(choosePanelForLastDateAndDoctor);
        add(pan);
        add(tablePanel);
    }
}
