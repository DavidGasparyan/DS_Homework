package main.company;

import java.util.ArrayList;

public class SWManager extends SWEngineer implements Manager {
  private ArrayList<Employee> directReports = new ArrayList<Employee>();

  public SWManager(String id, String name, String surname) {
    super(id, name, surname);
  }

  public SWManager(String id, String name, String surname, String title) {
    super(id, name, surname, title);
  }

  public void mentorEmployee() {
    System.out.println("Doing some mentoring stuff");
  }

  public void distributeTasks() {
    System.out.println("Doing some task distribution work");
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
