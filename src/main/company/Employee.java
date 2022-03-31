package main.company;

public abstract class Employee {
  int performanceScore;
  private String name;
  private String surname;
  private String id;

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

  @Override
  public String toString() {
    return "Employee{" +
            "performanceScore=" + performanceScore +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", id='" + id + '\'' +
            '}';
  }
}
