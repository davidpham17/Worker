package repository;

import data_access.WorkerDao;
import java.util.ArrayList;
import model.History;
import model.Worker;

public class WorkerRepository implements IWorkerRepository{
    private ArrayList<Worker> workers;

    public WorkerRepository() {
        this.workers = new ArrayList<>();
    }
    

    @Override
    public void addWorker(ArrayList<Worker> workers) {
        WorkerDao.Instance().addWorker(workers);
    }

    @Override
    public void changeSalary(ArrayList<Worker> workers, ArrayList<History> historys, int status) {
        WorkerDao.Instance().changeSalary(workers, historys, status);
    }

    @Override
    public void printListHistory(ArrayList<History> historys) {
        WorkerDao.Instance().printListHistory(historys);
    }

    @Override
    public Worker getWorkerByCode(ArrayList<Worker> workers, String id) {
        return WorkerDao.Instance().getWorkerByCode(workers, id);
    }

    @Override
    public String getCurrentDate() {
        return WorkerDao.Instance().getCurrentDate();
    }

    @Override
    public void printHistory(History history) {
        WorkerDao.Instance().printHistory(history);
    }
    
}
