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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Table
@Entity
public class Position {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IdPos")
    @Setter(value = AccessLevel.NONE)
    private int IdPos;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;
    
    @ManyToMany
	@JoinTable(name = "employee_positions",
	joinColumns = @JoinColumn(name = "IdEm"),
	inverseJoinColumns = @JoinColumn(name="IdPos")
	)
    @ToString.Exclude
    private Collection<Employee> employees = new ArrayList<Employee>();

    @ManyToMany(mappedBy="positions")
    private Collection<CourseCalendar> courseCal = new ArrayList<CourseCalendar>();

    public Position(String title, String description) {
        setTitle(title);
        setDescription(description);
    }

}
