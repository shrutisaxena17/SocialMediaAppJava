package org.example;

import java.util.Arrays;

public class SecondLargest {
   void findLargestNumber(){
       int a=10;
       int b=20;
       int c=30;
       if(a>b){
           if(b>c){
               System.out.println("A is the largest element");
           }
           else{
               System.out.println("C is the largest element");
           }
       }
       else{
           if(a>c){
               System.out.println("B is the largest element");
           }
           else{
               System.out.println("C is the largest element");
           }
       }
   }
}


Select count(XYC) as xyzcount
    from abc
            having xyzcount>1;

@Autowired
EmployeeService employeeService;

@PostMapping
Employee saveEmployee(@RequestBody Employee employee){
    Employee emp = employeeService.saveEmp(employee);
    return emp;
}
EmpRepo empRepo;
Employee saveEmp(Employee employee){
    empRepo.save(employee);
}