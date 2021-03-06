package lv.vktranzits.demo.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Table
@Entity
public class CourseImplementer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="IdCImpl")
    @Setter(value = AccessLevel.NONE)
    private int IdCImpl;
    
	@Column(name="Notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name="IdImp")
    private Implementer impl;
    
    @ManyToOne
    @JoinColumn(name="IdCal")
    private CourseCalendar courseimplementer;

    public CourseImplementer(String notes, Implementer impl, CourseCalendar courseimplementer) {
        setNotes(notes);
    }



}
