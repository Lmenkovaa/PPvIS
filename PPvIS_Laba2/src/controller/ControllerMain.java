package controller;

import model.DOMParser;
import model.SAXParserForRead;
import model.PetInformation;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.xml.sax.SAXException;
import view.ChoosePanel;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ControllerMain {
    private List<PetInformation> tableInfo = new ArrayList<PetInformation>();
    private final ChoosePanel choosePanel = new ChoosePanel();
    private boolean booleanPetName;
    private boolean booleanDateOfBirth;
    private boolean booleanLastDate;
    private boolean booleanDoctor;
    private boolean booleanDiagnosis;


    private List<PetInformation> FindTemplate(String petName, Date dateOfBirth, Date lastDate, String doctor, String diagnosis){
        List<PetInformation> temp = new ArrayList<>();
        if(dateOfBirth == null && lastDate == null ){
            for(PetInformation information : tableInfo){

                boolean isFits = (information.getNamePet().equals(petName))
                        && (information.getDoctor().equals(doctor) )
                        && (information.getDiagnosis().equals(diagnosis) );
                if(isFits){
                    temp.add(information);
                }
            }
        }else if(dateOfBirth == null){
            for(PetInformation information : tableInfo) {
                boolean bIsFits = (information.getNamePet().equals(petName) || booleanPetName)
                        && (information.getLastDate().compareTo(lastDate) == 0 || booleanLastDate)
                        && (information.getDoctor().equals(doctor) || booleanDoctor)
                        && (information.getDiagnosis().equals(diagnosis) || booleanDiagnosis);
                if (bIsFits) {
                    temp.add(information);
                }
            }
        }else if( lastDate == null){
            for(PetInformation information : tableInfo) {
                boolean bIsFits = (information.getNamePet().equals(petName) || booleanPetName)
                        && (information.getDateOfBirth().compareTo(dateOfBirth) == 0 || booleanDateOfBirth)
                        && (information.getDoctor().equals(doctor) || booleanDoctor)
                        && (information.getDiagnosis().equals(diagnosis) || booleanDiagnosis);
                if (bIsFits) {
                    temp.add(information);
                }
            }
        } else{
            for(PetInformation information : tableInfo) {
                boolean bIsFits = (information.getNamePet().equals(petName) || booleanPetName)
                        && (information.getDateOfBirth().compareTo(dateOfBirth) == 0 || booleanDateOfBirth)
                        && (information.getLastDate().compareTo(lastDate) == 0 || booleanLastDate)
                        && (information.getDoctor().equals(doctor) || booleanDoctor)
                        && (information.getDiagnosis().equals(diagnosis) || booleanDiagnosis);
                if (bIsFits) {
                    temp.add(information);
                }
            }
        }

        return temp;
    }

    public int DeletePetInformation(String petName, Date dateOfBirth, Date lastDate, String doctor, String diagnosis){

        booleanPetName = (petName.isEmpty());
        booleanDateOfBirth = (dateOfBirth == null);
        booleanLastDate = (lastDate == null);
        booleanDoctor = (doctor.isEmpty());
        booleanDiagnosis = (diagnosis.isEmpty());

        List<PetInformation> temp = FindTemplate(petName, dateOfBirth, lastDate, doctor, diagnosis);
        int amount = temp.size();
        tableInfo.removeAll(temp);
        return amount;
    }

    public List<PetInformation> FindPetInformation(String petName, Date dateOfBirth, Date lastDate, String doctor, String diagnosis){
        booleanPetName = (petName.isEmpty());
        booleanDateOfBirth = (dateOfBirth == null);
        booleanLastDate = (lastDate == null);
        booleanDoctor = (doctor.isEmpty());
        booleanDiagnosis = (diagnosis.isEmpty());

        return FindTemplate(petName, dateOfBirth, lastDate, doctor, diagnosis);
    }

    public void addPetInformation(PetInformation info) {
        tableInfo.add(info);
    }

    public void readPetInformation(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserForRead saxParserForRead = new SAXParserForRead();
        saxParserForRead.parse(path);
        tableInfo = SAXParserForRead.getInfoes();
    }

    public void writePetInformation(String path) {
        DOMParser domParser = new DOMParser();
        domParser.setInfoes(tableInfo, path);
        domParser.setBooks();
    }

    public boolean isExists(int index) {
        return index < tableInfo.size();
    }

    public PetInformation atIndex(int index) {
        return tableInfo.get(index);
    }

    public void setNotes(List<PetInformation> info) {
        tableInfo = info;
    }
}
