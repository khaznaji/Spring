package com.example.intermove.Entities.EventsAndComplaints;

import com.example.intermove.Entities.User.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String title;
    private String description;
    private Date dateD;
    private Date dateF;
    private Integer nbreDePlaces;
    private String image;
    private String Speaker;
    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;
    @Enumerated(EnumType.STRING)
    private ModaliteEvent modaliteEvent;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="events")
    public List<User> users;



}
