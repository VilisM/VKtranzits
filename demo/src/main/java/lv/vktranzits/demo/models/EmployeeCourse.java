package lv.vktranzits.demo.models;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class EmployeeCourse {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idEmCo")
    @Setter(value=AccessLevel.NONE)
    private int idEmCo;

    @Column(name="ValuePr")
    private float valuePr;

    @ManyToOne
    @JoinColumn(name="IdEm")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="IdCou")
    private Course course;

}
