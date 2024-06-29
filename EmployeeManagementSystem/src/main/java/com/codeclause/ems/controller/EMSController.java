package com.codeclause.ems.controller;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeclause.ems.dto.ApiResponseDto;
import com.codeclause.ems.dto.EmployeeDto;
import com.codeclause.ems.dto.JobRoleDto;
import com.codeclause.ems.service.DepartmentService;
import com.codeclause.ems.service.EmployeeService;
import com.codeclause.ems.service.JobRoleService;
//import com.codeclause.ems.service.SuperAdminService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/ems")
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.OPTIONS})
@AllArgsConstructor
public class EMSController {

	//private final SuperAdminService superAdminService;
	private final DepartmentService departmentService;
	private final JobRoleService jobRoleService;
	private final EmployeeService employeeService;

	/*@PostMapping("/login")
	public ResponseEntity<Map<String, String>> validateUser(@RequestBody @Valid LoginCredsDto loginCredsDto) {
		return new ResponseEntity<>(superAdminService.loginUser(loginCredsDto), HttpStatus.OK);
	}*/

	@GetMapping("/welcome")
	public ResponseEntity<?> welcome() {
		return new ResponseEntity<>("Welcome to Dashboard", HttpStatus.OK);
	}

	@GetMapping("/departments")
	public ResponseEntity<?> getDepartments() {
		return new ResponseEntity<>(departmentService.fetchAllDepartments(), HttpStatus.OK);
	}

	@GetMapping("/roles")
	public ResponseEntity<?> getRolesByDepartment(@RequestParam(required = false) String deptName,
			@RequestParam(required = false) Long deptId) {
		List<JobRoleDto> jobRoles = null;
		if (StringUtils.hasText(deptName)) {
			jobRoles = jobRoleService.findRolesByDeptName(deptName);
		} else if (deptId > 0) {
			jobRoles = jobRoleService.findRolesByDeptId(deptId);
		} else {
			jobRoles = jobRoleService.findAllRoles();
		}
		return new ResponseEntity<>(jobRoles, HttpStatus.OK);
	}

	@PostMapping("/employee")
	public ResponseEntity<?> addNewEmployeeToTheSystem(@RequestBody @Valid EmployeeDto requestPayload) {
		return new ResponseEntity<>(employeeService.saveEmployee(requestPayload), HttpStatus.OK);
	}

	@GetMapping("/fetchYears")
	public ResponseEntity<List<Integer>> fetchYears() {
		List<Integer> yearsList = new ArrayList<>();
		int currentYear = Year.now().getValue();
		int firstYear = 1920;
		for (int i = firstYear; i <= currentYear; i++) {
			yearsList.add(i);
		}
		return new ResponseEntity<>(yearsList, HttpStatus.OK);
	}

	@GetMapping("/fetchMonths")
	public ResponseEntity<List<Integer>> fetchMonths() {
		List<Integer> monthsList = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			monthsList.add(i);
		}
		return new ResponseEntity<>(monthsList, HttpStatus.OK);
	}

	@GetMapping("/fetchDays")
	public ResponseEntity<List<Integer>> fetchDays(@RequestParam Integer year, @RequestParam Integer month) {
		List<Integer> daysList = new ArrayList<>();
		if (Month.FEBRUARY == Month.of(month) ) {
			if (year % 4 == 0) {
				for (int i = 1; i <= 29; i++) {
					daysList.add(i);
				}
			} else {
				for (int i = 1; i <= 28; i++) {
					daysList.add(i);
				}
			}
		} else {
			for (int i = 1; i <= Month.of(month).maxLength(); i++) {
				daysList.add(i);
			}
		}
		return new ResponseEntity<>(daysList, HttpStatus.OK);
	}
	
	@PostMapping("/addEmployee")
	public ResponseEntity<?> saveEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		return new ResponseEntity<>(employeeService.saveEmployee(employeeDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/allEmployeesIdAndNames")
	public ResponseEntity<?> populateEmployeeDropdown()	{
		return new ResponseEntity<>(employeeService.getAllEmployyesIdAndNames(),HttpStatus.OK);
	}
	
	@GetMapping("/employee/{empId}")
	public ResponseEntity<EmployeeDto> fetchAnEmployee(@PathVariable Long empId)	{
		return new ResponseEntity<>(employeeService.getEmployeeById(empId),HttpStatus.OK);
	}
	
	@PostMapping("/updateEmployee")
	public ResponseEntity<ApiResponseDto> updateEmployee(@RequestBody EmployeeDto dto)	{
		return new ResponseEntity<>(employeeService.updateEmployee(dto),HttpStatus.OK);
	}
	
	@PostMapping("/deleteEmployee")
	public ResponseEntity<List<EmployeeDto>> deleteEmployee(@RequestBody Map<String, Long> reqPayload)	{
 		Long employeeId = reqPayload.get("empId");
 		return new ResponseEntity<>(employeeService.deleteAnEmployee(employeeId),HttpStatus.OK);
	}
	
	@GetMapping("/allEmployees")
	public ResponseEntity<List<EmployeeDto>> listEmployees()	{
		return new ResponseEntity<>(employeeService.listEmployeeDetails(),HttpStatus.OK);
	}
}
