package controller;

import view.WorkerInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkerController implements ActionListener {
    private WorkerInterfaz view;

    public WorkerController(WorkerInterfaz view) {
        this.view = view;
        this.view.btnAdd.addActionListener(this);
        this.view.btnSearch.addActionListener(this);
        this.view.btnModify.addActionListener(this);
        this.view.btnDelete.addActionListener(this);
        this.view.btnLogout.addActionListener(this);
    }

    private void addWorker() {
        updateWorkersTable();
    }

    private void searchWorker() {
        updateWorkersTable();
    }

    private void modifyWorker() {
            updateWorkersTable();
        }
    

    private void deleteWorker() {
            updateWorkersTable();
        }

    private void logout() {
    }
    
    private void updateWorkersTable() {
    }
    
      @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnAdd) {
            addWorker();
        } else if (e.getSource() == view.btnSearch) {
            searchWorker();
        } else if (e.getSource() == view.btnModify) {
            modifyWorker();
        } else if (e.getSource() == view.btnDelete) {
            deleteWorker();
        } else if (e.getSource() == view.btnLogout) {
            logout();
        }
    }
    
}
