package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class SAXNew {
    private String pet_name;
    private String date_of_birth;
    private String last_date;
    private String doctor;
    private String diagnosis;

    private String endlEl;

    private Info info;
    private static List<Info> infoes = new ArrayList<>();

    public void parse(String fileName){
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler(){

                boolean boolean_pet_name = false;
                boolean boolean_date_of_birth = false;
                boolean boolean_last_date = false;
                boolean boolean_doctor = false;
                boolean boolean_diagnosis = false;

                public void startElement( String uri, String localName, String queryName, Attributes attributes) throws SAXException{

                    if(queryName.equalsIgnoreCase(EValidParams.PET_NAME.toString())){
                        boolean_pet_name = true;
                    }
                    if(queryName.equalsIgnoreCase(EValidParams.DATE_OF_BIRTH.toString())){
                        boolean_date_of_birth = true;
                    }
                    if(queryName.equalsIgnoreCase(EValidParams.LAST_DATE.toString())){
                        boolean_last_date = true;
                    }
                    if(queryName.equalsIgnoreCase(EValidParams.DOCTOR.toString())){
                        boolean_doctor = true;
                    }
                    if(queryName.equalsIgnoreCase(EValidParams.DIAGNOSIS.toString())){
                        boolean_diagnosis = true;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException{
                    endlEl = qName;
                }

                public void characters(char[] ch, int start, int length) throws  SAXException{

                    if(boolean_pet_name){
                        System.out.println("pet_name: " + new String(ch, start, length));
                        setPet_name(new String(ch, start, length));
                        boolean_pet_name = false;

                    } else if(boolean_date_of_birth){
                        System.out.println("date_of_birth: " + new String(ch, start, length));
                        setDate_of_birth(new String(ch, start, length));
                        boolean_date_of_birth = false;

                    } else if(boolean_last_date){
                        System.out.println("last_date: " + new String(ch, start, length));
                        setLast_date(new String(ch, start, length));
                        boolean_last_date = false;

                    }else if(boolean_doctor){
                        System.out.println("doctor: " + new String(ch, start, length));
                        setDoctor(new String(ch, start, length));
                        boolean_doctor = false;

                    }else if(boolean_diagnosis){
                        System.out.println("diagnosis: " + new String(ch, start, length));
                        setDiagnosis(new String(ch, start, length));
                        boolean_diagnosis = false;

                        Info info = new Info();
                        info.setName_pet(getPet_name());
                        info.setDate_of_birth(getDate_of_birth());
                        info.setLast_date(getLast_date());
                        info.setDoctor(getDoctor());
                        info.setDiagnosis(getDiagnosis());
                        infoes.add(info);
                    }
                }

            };
            saxParser.parse(fileName, handler);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static List<Info> getInfoes(){
        return infoes;
    }
    public Info getInfo(){
        return info;
    }
    public String getPet_name(){
        return pet_name;
    }
    public String getDate_of_birth(){
        return date_of_birth;
    }
    public String getLast_date(){
        return last_date;
    }
    public String getDoctor(){
        return doctor;
    }
    public String getDiagnosis(){
        return diagnosis;
    }
    public void setPet_name(String pet_name){
        this.pet_name = pet_name;
    }
    public void setDate_of_birth(String date_of_birth){
        this.date_of_birth = date_of_birth;
    }
    public void setLast_date(String last_date){
        this.last_date = last_date;
    }
    public void setDoctor(String doctor){
        this.doctor = doctor;
    }
    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }
}
