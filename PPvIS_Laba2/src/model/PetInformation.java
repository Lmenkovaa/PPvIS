package model;

import java.util.Date;
import java.lang.String;

public class PetInformation extends Date {
    private String namePet, doctor, diagnosis;
    private Date dateOfBirth, lastDate;

    public PetInformation(String namePet, Date dateOfBirth, Date lastDate, String doctor, String diagnosis) {
        this.namePet = namePet;
        this.dateOfBirth = dateOfBirth;
        this.lastDate = lastDate;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
    }

    public PetInformation() {
    }

    public String getNamePet() {
        return namePet;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
