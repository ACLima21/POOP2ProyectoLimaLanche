package controller;

import com.mongodb.client.MongoCollection;
import view.CheckFinishStepInterfaz;
import model.Rooms;
import model.Client;
import model.connectionMongoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

public class CheckFinishStepController implements ActionListener {

    protected Rooms rooms;
    protected Client client;
    protected connectionMongoDB mongo;
    protected CheckFinishStepInterfaz checkFinishStepInterfaz;

    public CheckFinishStepController(Rooms rooms, Client client) {
        this.rooms = rooms;
        this.client = client;
        this.checkFinishStepInterfaz.btnConfirmReservation.addActionListener(this);
        this.checkFinishStepInterfaz.btnSearch.addActionListener(this);
        this.checkFinishStepInterfaz.btnModify.addActionListener(this);
        this.checkFinishStepInterfaz.btnDelete.addActionListener(this);
        this.checkFinishStepInterfaz.btnLogout.addActionListener(this);
        mongo.createConnection();
    }

    public void iniciarView() {
        checkFinishStepInterfaz.setVisible(true);
        loadDataForReservation();
    }

    // Metodo para cargar los datos de MongoDB en la Tabla
    public void loadDataForReservation() {
        DefaultTableModel tableModel = (DefaultTableModel) checkFinishStepInterfaz.tbReservationResume.getModel();
        tableModel.setRowCount(0);//Limpia todas las filas de la tabla

        checkFinishStepInterfaz.lbTitleForReservation.setText("Resumen de la reserva - " + client.getUsername());
//RoomType,Capacity,PricePerNight, DateRangeReservations, DateRangeReservations, costo total
        Object[] row = {
            doc.get("RoomType"),
            doc.get("Capacity"),
            doc.get("CreditNumber"),
            doc.get("Career"),
            doc.get("Prom"),
            doc.get("Total")
        };
        tableModel.addRow(row);

        checkFinishStepInterfaz.tbReservationResume.setModel(tableModel);
        System.out.println("Se cargó la tabla");
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
        if (e.getSource() == checkFinishStepInterfaz.btnConfirmReservation) {
            addWorker();
        } else if (e.getSource() == checkFinishStepInterfaz.btnSearch) {
            searchWorker();
        } else if (e.getSource() == checkFinishStepInterfaz.btnModify) {
            modifyWorker();
        } else if (e.getSource() == checkFinishStepInterfaz.btnDelete) {
            deleteWorker();
        } else if (e.getSource() == checkFinishStepInterfaz.btnLogout) {
            logout();
        }
    }

}
