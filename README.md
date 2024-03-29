# Java Programming Exercise

## Introduction

Given a JSON payload, I made a CSV file per employer with employees having the most family members enrolled at the top of the file and employees having the least number of enrolled family members at the bottom. For employees having the same number of enrolled family members, they are sorted alphabetically.

## Input Description

The input to the program is a JSON payload which contains an array of objects (or dictionaries). Each dictionary will have 5 properties:

- employer (**int**) - the numeric identifier of the employer
- employee (**string**) - the name of the employee
- product (**string**) - the product enrolled in
- is_enrolled (**boolean**) - whether the employee is enrolled
- spouses_enrolled (**int**) - the number of spouses the employee has elected to co-enroll - children_enrolled (int) - the number of children the employee has elected to co-enroll

> Note: Employees may have policies for more than one employer (if they work multiple jobs).

> Note: Employees are guaranteed to only have one enrollment per product per employer.

## Output Description

For each employer, there is a file named <employer>.csv. The CSV file has 5 columns:

- Employer (**int**) - the id of the employer
- Employee (**string**) - the name of the employee
- Total Enrolled (**int**) - the total number of people enrolled for this employee (in the case of an employee being enrolled in multiple products, this is the maximum value across all products for that employer)
- Spouses Enrolled (**int**) - the total number of spouses enrolled for this employee (in the case of an employee being enrolled in multiple products, this is the maximum value across all products for that employer)
- Children Enrolled (**int**) - the total number of children enrolled for this employee (in the case of an employee being enrolled in multiple products, this is the maximum value across all products for that employer)

> Note: Exclude any employee who has not enrolled.

## Sample Data

```json
[{
  "employer": 1,
  "employee": "James Monroe",
  "product": "MEDICAL",
  "is_enrolled": false,
  "spouses_enrolled": 0,
  "children_enrolled": 0
},{
  "employer": 1,
  "employee": "Thomas Jefferson",
  "product": "MEDICAL",
  "is_enrolled": true,
  "spouses_enrolled": 3,
  "children_enrolled": 46
},{
  "employer": 1,
  "employee": "James Madison",
  "product": "MEDICAL",
  "is_enrolled": true,
  "spouses_enrolled": 1,
  "children_enrolled": 0
},{
  "employer": 1,
  "employee": "Alexander Hamilton",
  "product": "MEDICAL",
  "is_enrolled": true,
  "spouses_enrolled": 1,
  "children_enrolled": 0
}]
```
  
## Sample Output

It will produce a file named 1.csv with the columns: Employer, Employee, Total Enrolled, Spouses Enrolled, Children Enrolled. The file should have the following content.

| Employer | Employee | Total Enrolled | Spouses Enrolled | Children Enrolled |
| --- | --- | --- | --- | --- |
| 1 | Thomas Jefferson | 50 | 3 | 46 |
| 1 | Alexander Hamilton | 2 | 1 | 0 |
| 1 | James Madison | 2 | 1 | 0 |

## Official Input Data
  
The official input data for this problem can be consumed from [https://raw.githubusercontent.com/iagtech/challenges-data/main/coding-problem.json](https://raw.githubusercontent.com/iagtech/challenges-data/main/coding-problem.json).
