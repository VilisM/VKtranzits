package lv.vktranzits.demo.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class CourseCalendar {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="IdCal")
    @Setter(value = AccessLevel.NONE)
    private int idCal;

    @Column(name="Year")
    private int year;

    @Column(name="StartDate")
    private Date start_date;

    @Column(name="EndDate")
    private Date end_date;
    
    @ManyToOne
    @JoinColumn(name="IdCou")
    private Course courseCal;

    @OneToMany
    

    @ManyToMany
    @JoinTable(
        joinColumns=@JoinColumn(name="IdPos"),
        inverseJoinColumns=@JoinColumn(name="IdCal"))
    @ToString.Exclude
    private Collection<Position> positions = new ArrayList<Position>();

}