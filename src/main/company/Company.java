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
    SWEngineer SWE1 = new SWEngineer();
    SWEngineer SWE2 = new SWEngineer();
    SWEngineer SWE3 = new SWEngineer();
    SWEngineer SWE4 = new SWEngineer();
    SWEngineer SWE5 = new SWEngineer();
    SWEngineer SWE6 = new SWEngineer();

    hashmap.put(SWE1, "David");
    hashmap.put(SWE2, "Poxos");
    hashmap.put(SWE3, "Petros");
    hashmap.put(SWE4, "Ani");
    hashmap.put(SWE5, "Janna");

//    hashmap.print();
//
    Iterator<HashMap.Entry<SWEngineer, String>> iterator = hashmap.iterator();

    while (iterator.hasNext()) {
      HashMap.Entry<SWEngineer, String>item = iterator.next();
      System.out.println(item.getKey() + ", " + item.getValue());
    }

//    ArrayDeque<Integer> integers = new ArrayDeque<>();
//
//    for (int i = 0; i < 10; i ++) {
//      integers.pushBack(i);
//    }

//    integers.print();

//    Executive CEO = new Executive();
//
//    FinanceManager financeManager = new FinanceManager();
//
//    SWManager SWM1 = new SWManager();
//    SWManager SWM2 = new SWManager();
//
//    SWEngineer SWE1 = new SWEngineer();
//    SWEngineer SWE2 = new SWEngineer();
//    SWEngineer SWE3 = new SWEngineer();
//    SWEngineer SWE4 = new SWEngineer();
//    SWEngineer SWE5 = new SWEngineer();
//    SWEngineer SWE6 = new SWEngineer();
//
//    Accountant Acc1 = new Accountant();
//    Accountant Acc2 = new Accountant();
//
//    Company company = new Company();
//
//    // Set the CEO of the main.company
//    company.setCEO(CEO);
//
//    // Hire all employees
//    company.hireEmployee(financeManager, CEO);
//    company.hireEmployee(SWM1, CEO);
//
//    // Set second software manager to report to the first software manager for testing purposes
//    company.hireEmployee(SWM2, SWM1);
//
//    // Theoretically let the first software manager to be the senior manager
//    company.hireEmployee(SWE1, SWM1);
//    company.hireEmployee(SWE2, SWM1);
//    company.hireEmployee(SWE3, SWM1);
//    company.hireEmployee(SWE4, SWM1);
//
//    company.hireEmployee(SWE5, SWM2);
//    company.hireEmployee(SWE6, SWM2);
//
//    company.hireEmployee(Acc1, financeManager);
//    company.hireEmployee(Acc2, financeManager);
//
//    SWEngineer SWE7 = new SWEngineer();
//    SWEngineer SWE8 = new SWEngineer();
//    SWEngineer SWE9 = new SWEngineer();
//    SWEngineer SWE10 = new SWEngineer();
//
//    SWM1.setPerformanceScore(3);
//    SWM2.setPerformanceScore(17);
//    SWE1.setPerformanceScore(10);
//    SWE2.setPerformanceScore(7);
//    SWE3.setPerformanceScore(8);
//    SWE4.setPerformanceScore(5);
//    SWE5.setPerformanceScore(6);
//    SWE6.setPerformanceScore(13);
//    Acc1.setPerformanceScore(100);
//    Acc2.setPerformanceScore(20);
//
//    SWE7.setPerformanceScore(95);
//    SWE8.setPerformanceScore(96);
//    SWE9.setPerformanceScore(97);
//    SWE10.setPerformanceScore(98);
//
////    main.company.employees.print();
//
//    company.addTopPerformer(SWM1);
//    company.addTopPerformer(SWM2);
//    company.addTopPerformer(SWE1);
//    company.addTopPerformer(SWE2);
//    company.addTopPerformer(SWE3);
//    company.addTopPerformer(SWE4);
//    company.addTopPerformer(SWE5);
//    company.addTopPerformer(SWE6);
//    company.addTopPerformer(Acc1);
//    company.addTopPerformer(Acc2);
//    company.addTopPerformer(SWE7);
//    company.addTopPerformer(SWE8);
//    company.addTopPerformer(SWE9);
//    main.company.addTopPerformer(SWE10);
//    main.company.addTopPerformer(SWE10);
//    main.company.addTopPerformer(SWE10);

//    System.out.println(main.company.top10performers);
//    main.company.top10performers.print();

//    System.out.println(main.company.top10performers);
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
