package controller;

import common.Library;
import java.util.ArrayList;
import model.Worker;
import model.History;
import repository.IWorkerRepository;
import repository.WorkerRepository;

import view.Menu;

public class WorkerManager extends Menu<String>{
    static String[] mc = {"Add a Worker", "Increase salary for worker", "Decrease for worker", "Show adjusted salary worker information", "Exit"};
    
    private Library library;
    private IWorkerRepository workerRepository;
    
    ArrayList<Worker> workers = new ArrayList<>();
    ArrayList<History> historys = new ArrayList<>();
    
    public WorkerManager() {
        super("===================== USER MANAGEMENT SYSTEM =====================", mc);
        library = new Library();
        workerRepository = new WorkerRepository();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                workerRepository.addWorker(workers);
                break;
            case 2:
                workerRepository.changeSalary(workers, historys, 1);
                break;
            case 3:
                workerRepository.changeSalary(workers, historys, 2);
                break;
            case 4:
                workerRepository.printListHistory(historys);
                break;
            case 5:
                System.out.println("Exit the program successfully!");
                System.exit(0);
                break;
        }
    }   
    
}