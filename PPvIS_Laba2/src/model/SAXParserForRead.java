package model;

import model.PetInformation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.EValidParams;

import static java.lang.Long.parseLong;

public class SAXParserForRead {
    private String petName;
    private Date dateOfBirth;
    private Date lastDate;
    private String doctor;
    private String diagnosis;

    private String endlEl;

    private PetInformation info;
    private static List<PetInformation> infoes = new ArrayList<>();

    public void parse(String fileName) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean booleanPetName = false;
                boolean booleanDateOfBirth = false;
                boolean booleanLastDate = false;
                boolean booleanDoctor = false;
                boolean booleanDiagnosis = false;

                public void startElement(String uri, String localName, String queryName, Attributes attributes) throws SAXException {

                    if (queryName.equalsIgnoreCase(EValidParams.PET_NAME.toString())) {
                        booleanPetName = true;
                    }
                    if (queryName.equalsIgnoreCase(EValidParams.DATE_OF_BIRTH.toString())) {
                        booleanDateOfBirth = true;
                    }
                    if (queryName.equalsIgnoreCase(EValidParams.LAST_DATE.toString())) {
                        booleanLastDate = true;
                    }
                    if (queryName.equalsIgnoreCase(EValidParams.DOCTOR.toString())) {
                        booleanDoctor = true;
                    }
                    if (queryName.equalsIgnoreCase(EValidParams.DIAGNOSIS.toString())) {
                        booleanDiagnosis = true;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    endlEl = qName;
                }

                public void characters(char[] ch, int start, int length) throws SAXException {

                    if (booleanPetName) {
                        System.out.println("pet_name: " + new String(ch, start, length));
                        setPetName(new String(ch, start, length));
                        booleanPetName = false;

                    } else if (booleanDateOfBirth) {
                        System.out.println("date_of_birth: " + new String(ch, start, length));
                        setDateOfBirth(new String(ch, start, length));
                        booleanDateOfBirth = false;

                    } else if (booleanLastDate) {
                        System.out.println("last_date: " + new String(ch, start, length));
                        setLastDate(new String(ch, start, length));
                        booleanLastDate = false;

                    } else if (booleanDoctor) {
                        System.out.println("doctor: " + new String(ch, start, length));
                        setDoctor(new String(ch, start, length));
                        booleanDoctor = false;

                    } else if (booleanDiagnosis) {
                        System.out.println("diagnosis: " + new String(ch, start, length));
                        setDiagnosis(new String(ch, start, length));
                        booleanDiagnosis = false;

                        PetInformation info = new PetInformation();
                        info.setNamePet(getPetName());
                        info.setDateOfBirth(getDateOfBirth());
                        info.setLastDate(getLastDate());
                        info.setDoctor(getDoctor());
                        info.setDiagnosis(getDiagnosis());
                        infoes.add(info);
                    }
                }

            };
            saxParser.parse(fileName, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<PetInformation> getInfoes() {
        return infoes;
    }

    public PetInformation getInfo() {
        return info;
    }

    public String getPetName() {
        return petName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setDateOfBirth(String stringForDateOfBirth) {
        long timeForDateOfBirth;
        timeForDateOfBirth = parseLong(stringForDateOfBirth);
        Date dateOfBirth = new Date();
        dateOfBirth.setTime(timeForDateOfBirth);
        this.dateOfBirth = dateOfBirth;
    }

    public void setLastDate(String stringForLastDate) {
        Date dateForLastDate = new Date();
        long timeForLastDate;
        try {
            timeForLastDate = parseLong(stringForLastDate);
            dateForLastDate.setTime(timeForLastDate);
            this.lastDate = dateForLastDate;
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат строки!");
        }
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
