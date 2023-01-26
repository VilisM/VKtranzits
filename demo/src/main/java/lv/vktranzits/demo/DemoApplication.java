package lv.vktranzits.demo;


import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lv.vktranzits.demo.repos.*;
import lv.vktranzits.demo.models.*;

@SpringBootApplication
@EnableJpaRepositories("lv.vktranzits.demo.*")
@ComponentScan(basePackages = { "lv.vktranzits.demo.*" })
@EntityScan("lv.vktranzits.demo.*")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(ICompanyRepo companyRepo, ICourseCalendarRepo courseCalRepo,
	 ICourseTypeRepo courseTypeRepo, IPositionRepo posRepo,
	 IDepartmentRepo departmentRepo, ICourseRepo courseRepo, IEmployeeRepo employeeRepo, IEmployeeCourseRepo empCourseRepo)
	{
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				// // Create company
				// Company company = new Company("VK tranzits");
				// companyRepo.save(company);

				// // Create department
				// Department department = new Department("IT", "Janis", "Berzins");
				// departmentRepo.save(department);

				// // Create position
				// Position position = new Position("Junior Java developer", "Random description");
				// posRepo.save(position);
				CourseType t1 = new CourseType(true, "Vienu reizi gadā");
				courseTypeRepo.save(t1);
				CourseType t2 = new CourseType(true, "Divas reizes gadā");
				courseTypeRepo.save(t2);
				CourseType t3 = new CourseType(true, "Tris reizes gadā");
				courseTypeRepo.save(t3);
				
				Course c1 = new Course("Programmētājs", "te kaut kada informācija", t1);
				Course c2 = new Course("Datorzinātnes", "te kaut kada informācija", t1);
				Course c3 = new Course("Celtnieki", "te kaut kada informācija", t1);
				Course c4 = new Course("Celtnieki2", "te kaut kada informācija", t1);
				courseRepo.save(c1);
				courseRepo.save(c2);
				courseRepo.save(c3);
				courseRepo.save(c4);

				Department d1 = new Department("Krāvēji", "Joņs", "Joņs");
				Department d2 = new Department("Cēlēji", "Pēters", "Peters");
				Department d3 = new Department("Stiepēji", "Džonis", "Bravo");
				Department d4 = new Department("Forklift dievi", "Antons", "Leons");
				Department d5 = new Department("IT speciālists", "Janis", "Berzins");
				departmentRepo.save(d1);
				departmentRepo.save(d2);
				departmentRepo.save(d3);
				departmentRepo.save(d4);
				departmentRepo.save(d5);


				Position position = new Position("ROLE_DEVELOPER", "Junior Java developer");
				Position position2 = new Position("ROLE_ADMIN", "Sistēmas administrātors");
				Position position3 = new Position("ROLE_EMPLOYEE", "Darbinieks");
				posRepo.save(position);
				posRepo.save(position2);
				posRepo.save(position3);
				Employee employee1 = new Employee("Janis", "Berzins", 22222222, "janisberzins@inbox.lv", "test123");
				Employee employee2 = new Employee("Pēteris", "Peters", 22222223, "peterispeters@inbox.lv", "test123");
				employee1.addPosition(position2);
				employee1.setDepartment(d1);
				employee2.setDepartment(d1);
				employee2.addPosition(position3);
				employeeRepo.save(employee1);
				employeeRepo.save(employee2);

				position2.addEmployee(employee1);
				position.addEmployee(employee1);
				position3.addEmployee(employee2);
				posRepo.save(position2);
				posRepo.save(position);
				posRepo.save(position3);

				// Vērtējumu pārbaudei
				Employee employee3 = new Employee("Roberts", "Ozoliņš", 22222223, "robertsozolins@inbox.lv", "test123");
				Employee employee4 = new Employee("Emīls", "Kociņš", 22222223, "emilskocins@inbox.lv", "test123");
				Employee employee5 = new Employee("Raitis", "Ozoliņš", 22222223, "raitisozolins@inbox.lv", "test123");
				Employee employee6 = new Employee("Māris", "Kociņš", 22222223, "mariskocins@inbox.lv", "test123");

				employee3.addPosition(position3);
				employeeRepo.save(employee3);
				employeeRepo.save(employee4);
				employeeRepo.save(employee5);
				employeeRepo.save(employee6);

				position3.addEmployee(employee3);
				posRepo.save(position3);

				Date date = new Date();

				EmployeeCourse empCourse1 = new EmployeeCourse("Programminženierija", 4, date, employee3, c2);
				EmployeeCourse empCourse2 = new EmployeeCourse("Datubāzes II", 6, date, employee3, c1);
				EmployeeCourse empCourse4 = new EmployeeCourse("Datubāzes II", 4, date, employee3, c1);
				EmployeeCourse empCourse5 = new EmployeeCourse("Programminženierija", 10, date, employee3, c1);
				EmployeeCourse empCourse6 = new EmployeeCourse("Programminženierija", 5, date, employee3, c1);
				EmployeeCourse empCourse7 = new EmployeeCourse("Matemātika", 4, date, employee3, c1);
				EmployeeCourse empCourse8 = new EmployeeCourse("Matemātika", 3, date, employee3, c1);
				EmployeeCourse empCourse3 = new EmployeeCourse("Programminženierija", 5, date, employee4, c1);
				empCourseRepo.save(empCourse1);
				empCourseRepo.save(empCourse2);
				empCourseRepo.save(empCourse3);
				empCourseRepo.save(empCourse4);
				empCourseRepo.save(empCourse5);
				empCourseRepo.save(empCourse6);
				empCourseRepo.save(empCourse7);
				empCourseRepo.save(empCourse8);
				
				
			}
		};
	}
	
	
	
	
	

}
