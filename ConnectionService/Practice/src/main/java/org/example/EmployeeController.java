//package org.example;
//
//@RestController
//@RequestMapping("/employee")
//public class EmployeeController {
//
//    @Autowired
//    EmployeeService employeeService;
//
//    @GetMapping("/getEmployee")
//    public List<Employee> getAllEmployee(){
//        List<Employee> employeeList=employeeService.getAllEmployees();
//        return employeeList;
//    }
//
//    @PostMapping("/addEmployee")
//    public Employee addEmployee(@RequestBody Employee employee){
//        Employee emp = employeeService.addEmployee(employee);
//        return emp;
//    }
//}
