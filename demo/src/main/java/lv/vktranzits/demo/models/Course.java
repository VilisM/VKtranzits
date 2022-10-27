package lv.vktranzits.demo.models;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table
@Entity
public class Course extends Auditable<String> {
    private static Log log = LogFactory.getLog(Course.class);
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="IdCou")
    @Setter(value = AccessLevel.NONE)
    private int idCou;

   
    @Column(name = "Title")
    private String title;

    
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

    public Course(String title, String description, CourseType coType) {
        setTitle(title);
        setDescription(description);
        setCoType(coType);
    }

    @PrePersist
    public void logNewCourseAttempt() {
        log.info("[COURSE AUDIT] Attempting to add new course with title: " + title);
    }
        
    @PostPersist
    public void logNewCourseAdded() {
        log.info("[COURSE AUDIT] Added course '" + title + "' with ID: " + idCou);
    }
        
    @PreRemove
    public void logCourseRemovalAttempt() {
        log.info("[COURSE AUDIT] Attempting to delete course: " + title);
    }

    @PreUpdate
    public void logCourseUpdateAttempt() {
        log.info("[COURSE AUDIT] Attempting to update course: " + title);
    }
        
    @PostRemove
    public void logCourseRemoval() {
        log.info("[COURSE AUDIT] Deleted course: " + title);
    }

    @PostUpdate
    public void logCourseUpdate() {
        log.info("[COURSE AUDIT] Updated course: " + title);
    }

    @PostLoad
    public void logCourseLoad() {
        log.info("[COURSE AUDIT] Loaded course: " + title);
    }
}
