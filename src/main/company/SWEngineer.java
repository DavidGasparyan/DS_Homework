package main.company;

public class SWEngineer extends Employee {
  private String title;

  public SWEngineer(String id, String name, String surname) {
    super(id, name, surname);
  }

  public SWEngineer(String id, String name, String surname, String title) {
    super(id, name, surname);
    this.title = title;
  }

  public void doCoding() {
  }
}
