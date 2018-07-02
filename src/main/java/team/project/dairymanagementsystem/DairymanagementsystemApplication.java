package team.project.dairymanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team.project.dairymanagementsystem.model.Contract;
import team.project.dairymanagementsystem.model.DairyStaff;
import team.project.dairymanagementsystem.model.enumerated.Gender;
import team.project.dairymanagementsystem.model.RoleGroup;
import team.project.dairymanagementsystem.service.ContractService;
import team.project.dairymanagementsystem.service.DairyStaffService;

@SpringBootApplication
public class DairymanagementsystemApplication {

	@Autowired
	private DairyStaffService dairyStaffService;
	@Autowired
    private ContractService contractService;

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
            dairyStaffService.addDairyStaff(new DairyStaff("1234","Admin","admin@info.co.ke",Gender.MALE,new RoleGroup("admin"),new BCryptPasswordEncoder().encode("12345")));
//            contractService.createContract(new Contract(47294729, "pending", 34, 43, "sweet"));
//            contractService.createContract(new Contract(42739472, "approved", 43, 32, "nice"));
//            contractService.createContract(new Contract(83295739, "denied", 42, 53, "thanks"));
//            contractService.createContract(new Contract(73294729, "pending", 21, 23, "welcome"));
//            contractService.createContract(new Contract(63282945, "cancelled", 43, 75, "white"));
//            contractService.createContract(new Contract(29237593, "cancelled", 22, 43, "early"));
		};
	}
}
