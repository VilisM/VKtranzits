package lv.vktranzits.demo.models;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

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
public class Course {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="IdCou")
    @Setter(value = AccessLevel.NONE)
    private int idCou;

    @Pattern(regexp="[A-Z]{1}[a-z\s]+")
    @Column(name = "Title")
    private String title;

    @Pattern(regexp="[A-Z]{1}[a-z\s]+")
    @Column(name = "Description")
    private String description;

    @ManyToOne
    @JoinColumn(name="IdTy")
    private CourseType coType;

    @ManyToMany(mappedBy="courses")
    private Collection<Department> departments = new ArrayList<Department>();

    @OneToMany(mappedBy="emCourse")
    @ToString.Exclude
    private Collection<EmployeeCourse> courses;

    @OneToMany(mappedBy = "courseCal")
    @ToString.Exclude
    private Collection<CourseCalendar> courseCalendars;

}
