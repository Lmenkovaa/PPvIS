package com.company;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TablePanel extends JPanel {
    ControllerMain controllerMain;

    JTable table;
    JPanel controlPane;
    JLabel count;
    JLabel page;

    int left;
    int right;
    int pageCounter;

    TablePanel(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        table = new JTable(10, 5);

        makeControlButtons();
        createLabels();
        add(table);
        add(controlPane);


    }

    public void createLabels(){
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

        JLabel pet_name = new JLabel(" Pet name");
        JLabel date_of_birth = new JLabel("  Date of birth");
        JLabel last_date = new JLabel("Last date");
        JLabel doctor = new JLabel("   Doctor");
        JLabel diagnosis = new JLabel("      Diagnosis");

        labelPanel.add(pet_name);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(date_of_birth);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(last_date);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(doctor);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(diagnosis);
        //labelPanel.add(Box.createHorizontalGlue());

        add(labelPanel);
    }

    void makeControlButtons(){
        left = 1;
        right = 10;
        pageCounter = 1;

        controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.LINE_AXIS));
        count = new JLabel("Notes " + left +" - " + right);
        page = new JLabel("Page: " + pageCounter);

        JButton nextPage = new JButton("Next");
        nextPage.addActionListener(actionEvent ->{
            if(controllerMain.isExists(right + 1 )){
                left += 10;
                right += 10;
                pageCounter++;
                count.setText("Notes " + left + " - " + right);
                page.setText("Page: " + pageCounter);
                showTable(controllerMain);
            }
        });

        JButton prevPage = new JButton("Previous");
        prevPage.addActionListener(actionEvent ->{
            if(pageCounter > 1){
                left -= 10;
                right -= 10;
                pageCounter--;
                count.setText("Notes " + left + " - " + right);
                page.setText("Page: " + pageCounter);
                showTable(controllerMain);
            }
        });

        controlPane.add(count);
        controlPane.add(Box.createHorizontalGlue());
        controlPane.add(page);
        controlPane.add(Box.createHorizontalGlue());
        controlPane.add(prevPage);
        controlPane.add(nextPage);
    }


     public void addEmpty(int row){
         table.setValueAt(" ", row, 0);
         table.setValueAt(" ", row, 1);
         table.setValueAt(" ", row, 2);
         table.setValueAt(" ", row, 3);
         table.setValueAt(" ", row, 4);
     }

     public void addNotes(int row, Info info){
         table.setValueAt(info.getName_pet(), row, 0);
         table.setValueAt(info.getDate_of_birth(), row, 1);
         table.setValueAt(info.getLast_date(), row, 2);
         table.setValueAt(info.getDoctor(), row, 3);
         table.setValueAt(info.getDiagnosis(), row, 4);
     }

     public void setNotes(ControllerMain cont){
        controllerMain = cont;
     }

    public void showTable(ControllerMain controllerMain){
        int index = 0;
        for(int i = left -1; i < right; i++){
            if(controllerMain.isExists(i)){
                addNotes(index, controllerMain.atIndex(i));
            }else{
                addEmpty(index);
            }
            index++;
        }
    }
}
