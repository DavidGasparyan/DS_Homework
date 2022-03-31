package main.company;

import java.util.ArrayList;

public class FinanceManager extends Accountant implements Manager {
  private ArrayList<Employee> directReports = new ArrayList<Employee>();

  public double createCompanyBudget() {
    return 20000.0;
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
