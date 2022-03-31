package main.company;

import java.util.ArrayList;

public interface Manager {
  public void evaluateEmployee();

  public void reviewSalary();

  public ArrayList<Employee> getDirectReports();

  public void setDirectReports(Employee employee);
}
