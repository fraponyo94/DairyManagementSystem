package team.project.dairymanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team.project.dairymanagementsystem.model.DairyStaff;
import team.project.dairymanagementsystem.model.RoleGroup;
import team.project.dairymanagementsystem.model.enumerated.Gender;
import team.project.dairymanagementsystem.model.enumerated.Roles;
import team.project.dairymanagementsystem.service.DairyStaffService;

@SpringBootApplication

public class DairymanagementsystemApplication {

	@Autowired
	private DairyStaffService dairyStaffService;

	public static void main(String[] args) {
		SpringApplication.run(DairymanagementsystemApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner runner(){
		return (String...args) -> {
            dairyStaffService.addDairyStaff(new DairyStaff("1234","Admin","admin@info.co.ke",Gender.MALE,new RoleGroup(Roles.Admin.toString()),new BCryptPasswordEncoder().encode("12345")));
		};
	}
}
