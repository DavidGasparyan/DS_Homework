package main.company;

// Import my implementation of a LinkedList
import main.company.utility.*;

public class Company {
  private LinkedList<Employee> employees = new LinkedList<>();
  private ArrayDeque<Employee> top10performers = new ArrayDeque<>();
  private HashMap<Employee, LinkedList<Employee>> mentors;
  private Executive CEO;

  public static void main(String[] args) {
    HashMap<SWEngineer, String> hashmap = new HashMap<>();
//
    SWEngineer SWE1 = new SWEngineer("1", "David", "Gasparyan", "Junior");
    SWEngineer SWE2 = new SWEngineer("2", "Poxos", "Poxosyan", "Middle");
    SWEngineer SWE3 = new SWEngineer("3", "Anna", "Harutyunyan", "Junior");
    SWEngineer SWE4 = new SWEngineer("4", "Mariam", "Petrosyan", "Junior");
    SWEngineer SWE5 = new SWEngineer("5", "Aram", "Minasyan", "Junior");
    SWEngineer SWE6 = new SWEngineer("6", "Grigor", "Grigoryan", "Team Lead");

    hashmap.put(SWE1, "David");
    hashmap.put(SWE1, "David");
    hashmap.put(SWE1, "David");
    hashmap.put(SWE1, "Ando");
    hashmap.put(SWE2, "Poxos");
    hashmap.put(SWE3, "Petros");
    hashmap.put(SWE4, "Ani");

//    Iterator<HashMap.Entry<SWEngineer, String>> iterator = hashmap.iterator();
//
//    while (iterator.hasNext()) {
//      HashMap.Entry<SWEngineer, String>item = iterator.next();
//      System.out.println(item.getKey() + ", " + item.getValue());
//    }

    hashmap.keySet().print();

//    HashTable<Integer, String> table = new HashTable<>();

  }

  public boolean hireEmployee(Employee employee, Manager manager) {
    if (CEO.confirmHiring(employee)) {
      // Change implementation a bit to addLast. Method name change
      employees.addLast(employee);
      manager.setDirectReports(employee);
    }

    return false;
  }

  // Change implementation a bit to addFirst. Method name change.
  // Now CEO becomes first member of a main.company
  public void setCEO(Executive CEO) {
    this.CEO = CEO;
    employees.addFirst(CEO);
  }

  public void addTopPerformer(Employee e) {
    top10performers.pushBack(e);

    if (top10performers.size() > 10) {
      Employee firstEmployee = top10performers.front();
      Employee smallestPerformer = firstEmployee;

      Iterator<Employee> iterator = top10performers.iterator();

      while (iterator.hasNext()) {
        Employee currentEmployee = iterator.next();
        System.out.println(currentEmployee);

        if (currentEmployee.getPerformanceScore() < smallestPerformer.getPerformanceScore()) {
          smallestPerformer = currentEmployee;
        }
      }

      top10performers.swap(firstEmployee, smallestPerformer);

      top10performers.popFront();
    }
  }

  public boolean enableMentorship(Employee mentor, Employee mentee) {
    LinkedList<Employee> mentees = mentor.getMentees();

    if (mentees.size() < 3) {
      mentees.addLast(mentee);
      mentors.put(mentor, mentees);

      return true;
    }

    return false;
  }

  public Employee getMentor(Employee mentee) {
    Iterator<HashMap.Entry<Employee, LinkedList<Employee>>> hashMapIterator = mentors.iterator();

    while(hashMapIterator.hasNext()) {
      HashMap.Entry<Employee, LinkedList<Employee>> entry = hashMapIterator.next();

      Employee mentor = entry.getKey();
      LinkedList<Employee> mentees = entry.getValue();


      Iterator<Employee> employeeListIterator = mentees.iterator();

      while (employeeListIterator.hasNext()) {
        Employee employee = employeeListIterator.next();

        if(employee == mentee || employee.equals(mentee)) {
          return mentor;
        }
      }
    }

    return null;
  }

//  public ArrayList<Employee> getTeam(Manager manager) {
//    ArrayList<Employee> team = new ArrayList<Employee>();
//
//    for (Employee employee: manager.getDirectReports()) {
//
//      if (employee instanceof Manager currentManager) {
//        team.addAll(getTeam(currentManager));
//      } else {
//        team.add(employee);
//      }
//    }
//
//    return team;
//  }
}
