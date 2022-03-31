package main.company;

import java.util.ArrayList;

public class Executive extends Employee implements Manager {
  private Manager[] team;
  private ArrayList<Employee> directReports = new ArrayList<Employee>();

  public boolean confirmHiring(Employee employee) {
    return true;
  }
  public boolean confirmFiring(Employee employee) {
    return false;
  }
  public boolean confirmCompanyBudget() {
    return false;
  }

  @Override
  public void evaluateEmployee() {

  }

  @Override
  public void reviewSalary() {

  }

  @Override
  public ArrayList<Employee> getDirectReports() {
    return directReports;
  }

  @Override
  public void setDirectReports(Employee employee) {
    directReports.add(employee);
  }
}

