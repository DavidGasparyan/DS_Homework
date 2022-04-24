package main.company;

public class SWEngineer extends Employee implements Comparable<SWEngineer> {
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

  @Override
  public int compareTo(SWEngineer o) {
    return this.getName().compareTo(o.getName());
  }
}
