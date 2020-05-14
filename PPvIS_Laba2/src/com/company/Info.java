package com.company;

public class Info {
    private String name_pet, doctor, date_of_birth, last_date, diagnosis;

    public Info(String name_pet, String date_of_birth, String last_date, String doctor,String diagnosis){
        this.name_pet = name_pet;
        this.date_of_birth = date_of_birth;
        this.last_date = last_date;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
    }

    public Info() {

    }

    public String getName_pet(){
        return name_pet;
    }
    public String getDate_of_birth(){ return date_of_birth; }
    public String getLast_date(){ return last_date; }
    public String getDiagnosis(){ return diagnosis; }
    public String getDoctor(){
        return doctor;
    }

    public void setName_pet(String name_pet){
        this.name_pet = name_pet;
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
