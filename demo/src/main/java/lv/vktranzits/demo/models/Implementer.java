package lv.vktranzits.demo.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Implementer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IdImp")
    @Setter(value = AccessLevel.NONE)
    private int idImp;

    @Pattern(regexp="[A-Z]{1}[a-z\s]+")
    @Column(name = "Title")
    private String title;

    @OneToMany(mappedBy = "impl")
    @ToString.Exclude
    private Collection<CourseImplementer> cImplementers = new ArrayList<CourseImplementer>();
}
