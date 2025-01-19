package controller;

import view.CheckFinishStep;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkerController implements ActionListener {
    private CheckFinishStep view;

    public WorkerController(CheckFinishStep view) {
        this.view = view;
        this.view.btnConfirmReservation.addActionListener(this);
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
        if (e.getSource() == view.btnConfirmReservation) {
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
