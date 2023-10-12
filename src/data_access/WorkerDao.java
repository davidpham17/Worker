package data_access;

import common.Library;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import model.History;
import model.Worker;

public class WorkerDao {
    private static WorkerDao instance = null;
    
    public static WorkerDao Instance() {
        if (instance == null){
            synchronized (WorkerDao.class) {
                if (instance == null){
                    instance = new WorkerDao();
                }
            }
        }
        return instance;
    }
    
    public void addWorker(ArrayList<Worker> workers) {
        System.out.print("Enter code: ");
        String id = Library.inputString();
        if (!Library.checkIdExist(workers, id)) {
            System.err.println("The code (id) already exists in the DB.");
            return;
        }
        else {
            System.out.print("Enter name: ");
            String name = Library.inputString();
            System.out.print("Enter age: ");
            int age = Library.inputIntLimit(18, 50);
            System.out.print("Enter salary: ");
            int salary = Library.checkInputSalary();
            System.out.print("Enter work location: ");
            String workLocation = Library.inputString();
            workers.add(new Worker(id, name, age, salary, workLocation));
            System.err.println("Add success.");
        }
    }

    //allow user increase salary for user
    public void changeSalary(ArrayList<Worker> workers, ArrayList<History> historys, int status) {
        if (workers.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.print("Enter code: ");
        String id = Library.inputString();
        Worker worker = getWorkerByCode(workers, id);
        if (worker == null) {
            System.err.println("Not exist worker.");
        } else {
            int salaryCurrent = worker.getSalary();
            int salaryUpdate;
            //check user want to update salary
            if (status == 1) {
                System.out.print("Enter salary: ");
                //loop until user input salary update > salary current
                while (true) {     
                    salaryUpdate = Library.checkInputSalary();
                    //check user input salary update > salary current
                    if (salaryUpdate <= salaryCurrent) {
                        System.err.println("Must be greater than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                historys.add(new History("UP", getCurrentDate(), worker.getId(),
                        worker.getName(), worker.getAge(), salaryUpdate,
                        worker.getWorkLocation()));
            } else {
                System.out.print("Enter salary: ");
                //loop until user input salary update < salary current
                while (true) {
                    salaryUpdate = Library.checkInputSalary();
                    //check user input salary update < salary current
                    if (salaryUpdate >= salaryCurrent) {
                        System.err.println("Must be smaller than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                historys.add(new History("DOWN", getCurrentDate(), worker.getId(),
                        worker.getName(), worker.getAge(), salaryUpdate,
                        worker.getWorkLocation()));
            }
            worker.setSalary(salaryUpdate);
            System.err.println("Update success");
        }
    }

    //allow user print history
    public void printListHistory(ArrayList<History> historys) {
        if (historys.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.printf("%-5s%-15s%-5s%-10s%-10s%-20s\n", "Code", "Name", "Age",
                "Salary", "Status", "Date");
        Collections.sort(historys);
        //print history from first to last list
        for (History history : historys) {
            printHistory(history);
        }
    }

    //get worker by code
    public Worker getWorkerByCode(ArrayList<Worker> workers, String id) {
        for (Worker worker : workers) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return worker;
            }
        }
        return null;
    }

    //get current date 
    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    //print history
    public void printHistory(History history) {
        System.out.printf("%-5s%-15s%-5d%-10d%-10s%-20s\n", history.getId(),
                history.getName(), history.getAge(), history.getSalary(),
                history.getStatus(), history.getDate());
    }
}
