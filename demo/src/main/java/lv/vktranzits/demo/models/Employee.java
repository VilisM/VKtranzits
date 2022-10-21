package lv.vktranzits.demo.models;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Table
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="IdEm")
    @Setter(value=AccessLevel.NONE)
    private int idEm;

    @Column(name="Name")
    @NonNull
    @NotBlank(message = "Vārds ir obligāts")
    @Size(min=1, max=20)
	@Pattern (regexp = "[A-Z,Ā,Ē,Ī,Ū,Š,Ģ,Ķ,Ļ,Ž,Č,Ņ]{1}[a-z,ā,ē,ū,ī,š,ģ,ķ,ļ,ž,č,ņ]+")
    private String name;
    
    @Column(name="Surname")
    @NonNull
    @NotBlank(message = "Uzvārds ir obligāts")
    @Size(min=1, max=20)
	@Pattern (regexp = "[A-Z,Ā,Ē,Ī,Ū,Š,Ģ,Ķ,Ļ,Ž,Č,Ņ]{1}[a-z,ā,ē,ū,ī,š,ģ,ķ,ļ,ž,č,ņ]+")
    private String surname;

    @Column(name = "PhoneNumber")
	@Min (20000000)
	@Max (29999999)
    private int phone;
    
    @Column(name="Email")
    @NotNull
    @NotEmpty 
    @Email
    @Size(min=5, max=30)
	@Pattern (regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,10}$")
    private String email;
    
    @Column(name="Password")
	private String password;

    @ManyToOne
    @JoinColumn(name="IdDe")
    private Department department;

    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    private Collection<EmployeeCourse> emCourse;
    
	@ManyToMany(mappedBy="employees", fetch = FetchType.EAGER)
	@ToString.Exclude
    private Collection<Position> allPositions = new ArrayList<Position>();
	
	public Collection<Position> getAllPositions() {
		return allPositions;
	}
    
    public Employee(String name, String surname, int phone, String email, String password) {
        setName(name);
        setSurname(surname);
        setPhone(phone);
        setEmail(email);
        setPasswordHashed(password);
    }

    // hash password
    public void setPasswordHashed(String password) {
        setPassword(new BCryptPasswordEncoder().encode(password));
    }

    // check password
    public boolean checkPassword(String password) {
        return new BCryptPasswordEncoder().matches(password, this.password);
    }
    
}
