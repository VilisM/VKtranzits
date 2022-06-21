package lv.vktranzits.demo.models;
import java.util.ArrayList;
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
public class CourseType {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="IdTy")
    @Setter(value = AccessLevel.NONE)
    private int idTy;

    @Column(name="IsObligatory")
    private boolean isObligatory;
    //TODO parejie mainigie

    @OneToMany(mappedBy="coType")
    @ToString.Exclude
    private Collection<Course> courses;

}