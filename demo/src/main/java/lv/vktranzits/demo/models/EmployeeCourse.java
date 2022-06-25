package lv.vktranzits.demo.models;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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
public class EmployeeCourse {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idEmCo")
    @Setter(value=AccessLevel.NONE)
    private int idEmCo;

    
    @Column(name="Title")
    private String title;
    
    @Column(name="ValuePr")
    @Size(min = 0, max = 10)
    private float valuePr;

    @Column(name="Date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="IdEm")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="IdCou")
    private Course emCourse;

}
