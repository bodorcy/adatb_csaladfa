package com.csaladfa.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer mother_id;
    private Integer father_id;
    private String first_name;
    private String last_name;
    private String gender;
    private LocalDate date_of_birth;

    public void setDate_of_birth(String dob){
        if(dob == null || dob.equals(""))
            return;
        date_of_birth = LocalDate.parse(dob);
    }
    public void setDate_of_birth(Date dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setDate_of_birth(sdf.format(dob));
    }
    public void setDate_of_birth(LocalDate dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setDate_of_birth(sdf.format(dob));
    }
}
