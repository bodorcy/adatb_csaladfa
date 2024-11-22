package com.csaladfa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    public void setDate(String d){
        date = LocalDate.parse(d);
    }
    public void setDate(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setDate(sdf.format(d));
    }
}
