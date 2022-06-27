package lv.vktranzits.demo.models;
import java.util.ArrayList;
import java.util.Collection;

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
import javax.validation.constraints.Pattern;
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
public class Department {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="IdDe")
    @Setter(value = AccessLevel.NONE)
    private int idDe;

    @Pattern(regexp="[A-Z]{1}[a-z\s]+")
    @Column(name = "Title")
    @Size(min = 4, max = 30)
    @Pattern(regexp="[A-Z]{1}[a-z\\s]+")
    private String title;

    @Column(name = "VName")
    @Size(min = 4, max = 30)
    @Pattern(regexp="[A-Z]{1}[a-z\\s]+")
    private String V_name;

    @Column(name = "VSurname")
    @Size(min = 4, max = 30)
    @Pattern(regexp="[A-Z]{1}[a-z\\s]+")
    private String V_surname;

    @ManyToOne
    @JoinColumn(name="IdCo")
    private Company company;

    @OneToMany(mappedBy="department")
    @ToString.Exclude
    private Collection<Employee> employees;

    @ManyToMany
    @JoinTable(
        joinColumns=@JoinColumn(name="IdDe"),
        inverseJoinColumns=@JoinColumn(name="IdCou"))
    @ToString.Exclude
    private Collection<Course> courses = new ArrayList<Course>();

    public void addNewCourse(Course course) {
        courses.add(course);
    }

    public Department(String title) {
        setTitle(title);
    }
}
