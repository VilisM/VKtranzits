package lv.vktranzits.demo;

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
	 IDepartmentRepo departmentRepo, ICourseRepo courseRepo, IEmployeeRepo employeeRepo)
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
				
				Course c1 = new Course("Java tests", "Tests par Java pamatiem", t1);
				Course c2 = new Course("Java tests2", "Tests par Java pamatiem", t1);
				Course c3 = new Course("Java tests3", "Tests par Java pamatiem", t1);
				Course c4 = new Course("Java tests4", "Tests par Java pamatiem", t1);
				courseRepo.save(c1);
				courseRepo.save(c2);
				courseRepo.save(c3);
				courseRepo.save(c4);

				Department d1 = new Department("Krāvēji", "Joņs", "Joņs");
				Department d2 = new Department("Cēlēji", "Pēters", "Peters");
				Department d3 = new Department("Stiepēji", "Džonis", "Bravo");
				Department d4 = new Department("Forklift dievi", "Antons", "Leons");
				departmentRepo.save(d1);
				departmentRepo.save(d2);
				departmentRepo.save(d3);
				departmentRepo.save(d4);

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
				employeeRepo.save(employee1);
				employeeRepo.save(employee2);

				position2.addEmployee(employee1);
				position.addEmployee(employee1);
				posRepo.save(position2);
				posRepo.save(position);
				
				
				
				
				
			}
		};
	}
	
	
	
	
	

}
