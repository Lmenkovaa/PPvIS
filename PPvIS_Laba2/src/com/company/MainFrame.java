package com.company;

import org.xml.sax.SAXException;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class MainFrame extends Component {
    ControllerMain controllerMain = new ControllerMain();
    DOMNew controller = new DOMNew();
    private JFrame mainFrame = new JFrame("Информация о пациентах");
    private JPanel mainPanel = new JPanel();
    private TablePanel tablePanel;
    private JButton addButton, findNameAndDateOfBirthButton,findLastDateAndDoctorButton, findDiagnosisButton;
    private JButton removeNameAndBirthButton,removeLastDateAndDoctorButton, removeDiagnosisButton, showButton, saveButton;
    private JTextField numberOfInfoesOnPageField;
    private JTextArea numberOfInfoesOnPageArea;
    private boolean tableCreated = false;
    private String pathFile;

    private AddFrame addFrame;
    private FindForPetNameAndDateOfBirthFrame findForPetNameAndDateOfBirthFrame;

    private FindForLastDateAndDoctor findForLastDateAndDoctor;
    private FindForDiagnosis findForDiagnosis;
    private DeleteForPetNameAndDateOfBirth deleteForPetNameAndDateOfBirth;
    private DeleteForLastDateAndDoctor deleteForLastDateAndDoctor;
    private DeleteForDiagnosis deleteForDiagnosis;

    public void initialize(){
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel.setLayout(null);
        tablePanel = new TablePanel(this.controllerMain);
        tablePanel.setBounds(490, 40,490,400);
        mainPanel.add(tablePanel);

        addButton = new JButton("Добавить в таблицу новую информацию");
        addButton.setBounds(20,20,450,40);
        mainPanel.add(addButton);

        addFrame = new AddFrame(this.controllerMain);
        AddListener addListener = new AddListener();

        addButton.addActionListener(addListener);
        addFrame.add.addActionListener(actionEvent ->{
            addFrame.addNotes();
            tablePanel.showTable(this.controllerMain);
        });
        mainPanel.setLayout(null);


        findNameAndDateOfBirthButton = new JButton("Поиск в таблице по имени питомца и дате рождения");
        findNameAndDateOfBirthButton.setBounds(20,80,450,40);
        mainPanel.add(findNameAndDateOfBirthButton);

        findForPetNameAndDateOfBirthFrame = new FindForPetNameAndDateOfBirthFrame(this.controllerMain);
        FindForPetNameAdnDateOfBirthListener findForPetNameAdnDateOfBirthListener = new FindForPetNameAdnDateOfBirthListener();
        findNameAndDateOfBirthButton.addActionListener(findForPetNameAdnDateOfBirthListener);


        findLastDateAndDoctorButton = new JButton("Поиск в таблице по последнем визиту и ФИО доктора");
        findLastDateAndDoctorButton.setBounds(20,140, 450,40);
        mainPanel.add(findLastDateAndDoctorButton);

        findForLastDateAndDoctor = new FindForLastDateAndDoctor(this.controllerMain);
        FindForLastDateAndDoctorListener findForLastDateAndDoctorListener = new FindForLastDateAndDoctorListener();
        findLastDateAndDoctorButton.addActionListener(findForLastDateAndDoctorListener);


        findDiagnosisButton = new JButton("Поиск в таблице по диагнозу");
        findDiagnosisButton.setBounds(20,200,450,40);
        mainPanel.add(findDiagnosisButton);

        findForDiagnosis = new FindForDiagnosis(this.controllerMain);
        FindForDiagnosisListener findForDiagnosisListener = new FindForDiagnosisListener();
        findDiagnosisButton.addActionListener(findForDiagnosisListener);


        removeNameAndBirthButton = new JButton("Удаление из таблицы по имени питомца и дате рождения");
        removeNameAndBirthButton.setBounds(20,260, 450, 40);
        mainPanel.add(removeNameAndBirthButton);
        deleteForPetNameAndDateOfBirth = new DeleteForPetNameAndDateOfBirth(this.controllerMain);
        DeleteForPetNameAndDateOfBirthListener deleteForPetNameAndDateOfBirthListener = new DeleteForPetNameAndDateOfBirthListener();
        removeNameAndBirthButton.addActionListener(deleteForPetNameAndDateOfBirthListener);
        deleteForPetNameAndDateOfBirth.getDeleteForPetNameAndDateOfBirthButton().addActionListener(actionEvent ->{
            int amount = deleteForPetNameAndDateOfBirth.deleteNotes();
            JOptionPane.showMessageDialog(this.addFrame, "Deleted: " + amount);
            tablePanel.showTable(this.controllerMain);

        });



        removeLastDateAndDoctorButton = new JButton("Удаление из таблицы по последнему визиту и ФИО доктора");
        removeLastDateAndDoctorButton.setBounds(20,320,450,40);
        mainPanel.add(removeLastDateAndDoctorButton);
        deleteForLastDateAndDoctor = new DeleteForLastDateAndDoctor(this.controllerMain);
        DeleteForLastDateAndDoctorListener deleteForLastDateAndDoctorListener = new DeleteForLastDateAndDoctorListener();
        removeLastDateAndDoctorButton.addActionListener(deleteForLastDateAndDoctorListener);
        deleteForLastDateAndDoctor.getDeleteForLastDateAndDoctorButton().addActionListener(actionEvent ->{
            int amount = deleteForLastDateAndDoctor.deleteNotes();
            tablePanel.showTable(this.controllerMain);
            JOptionPane.showMessageDialog(this.addFrame, "Deleted: " + amount);

        });

        removeDiagnosisButton = new JButton("Удаление из таблицы по диагнозу");
        removeDiagnosisButton.setBounds(20,380,450,40);
        mainPanel.add(removeDiagnosisButton);
        deleteForDiagnosis = new DeleteForDiagnosis(this.controllerMain);
        DeleteForDiagnosisListener deleteForDiagnosisListener = new DeleteForDiagnosisListener();
        removeDiagnosisButton.addActionListener(deleteForDiagnosisListener);
        deleteForDiagnosis.getDeleteForDiagnosisButton().addActionListener(actionEvent ->{
            int amount = deleteForDiagnosis.deleteNotes();
            tablePanel.showTable(this.controllerMain);
            JOptionPane.showMessageDialog(this.addFrame, "Deleted: " + amount);
        });

        showButton = new JButton("Показать данные из XML файла");
        showButton.setBounds(20,440,450,40);
        showButton.addActionListener(new LoadListener());
        mainPanel.add(showButton);

        saveButton = new JButton("Сохранить изменения в XML файл");
        saveButton.setBounds(20,500,450,40);
        saveButton.addActionListener(new SaveListener());
        mainPanel.add(saveButton);

        mainPanel.setVisible(true);
        mainFrame.add(mainPanel);
        mainFrame.setSize(1000,600);
        mainFrame.setVisible(true);
    }

    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            addFrame.setVisible(!addFrame.isVisible());
        }
    }


    private class FindForPetNameAdnDateOfBirthListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            findForPetNameAndDateOfBirthFrame.setVisible(!findForPetNameAndDateOfBirthFrame.isVisible());
        }
    }

    private class FindForLastDateAndDoctorListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            findForLastDateAndDoctor.setVisible(!findForLastDateAndDoctor.isVisible());
        }
    }

    private class FindForDiagnosisListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            findForDiagnosis.setVisible(!findForDiagnosis.isVisible());
        }
    }

    private class DeleteForPetNameAndDateOfBirthListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteForPetNameAndDateOfBirth.setVisible(!deleteForPetNameAndDateOfBirth.isVisible());
        }
    }

    private class DeleteForLastDateAndDoctorListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteForLastDateAndDoctor.setVisible(!deleteForLastDateAndDoctor.isVisible());
        }
    }

    private class DeleteForDiagnosisListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteForDiagnosis.setVisible(!deleteForDiagnosis.isVisible());
        }
    }

    private class SaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Special XML file", "xml");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(chooser);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                String path = chooser.getSelectedFile().getAbsolutePath();
                controllerMain.Write(path);
            }

        }
    }

    private class LoadListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Special xml file" , "xml");
            chooser.setFileFilter(filter);
            int returnValue = chooser.showOpenDialog(chooser);
            if(returnValue == JFileChooser.APPROVE_OPTION){
                String path = chooser.getSelectedFile().getAbsolutePath();
                try{
                    controllerMain.Read(path);
                    tablePanel.showTable(controllerMain);
                }catch (ParserConfigurationException ex){
                    ex.printStackTrace();
                }catch (SAXException exc){
                    exc.printStackTrace();
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        }
    }
}
