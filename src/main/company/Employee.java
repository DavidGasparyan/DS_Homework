package main.company;

import main.company.utility.LinkedList;

public abstract class Employee {
  int performanceScore;
  private String name;
  private String surname;
  private String id;
  private LinkedList<Employee> mentees;

  public void doWork() {
    System.out.println("Employee does general work");
  }

  public int takeVacation() {
    return 25;
  }

  public int getPerformanceScore() {
    return performanceScore;
  }

  public void setPerformanceScore(int performanceScore) {
    this.performanceScore = performanceScore;
  }

  protected Employee() {}

  protected Employee(String id, String name, String surname) {
    this.id = id;
    this.name = name;
    this.surname = surname;
  }

  @Override
  public String toString() {
    return "Employee{" +
            "performanceScore=" + performanceScore +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", id='" + id + '\'' +
            '}';
  }

  public LinkedList<Employee> getMentees() {
    return mentees;
  }
}
