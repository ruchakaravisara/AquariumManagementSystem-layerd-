package lk.ijse.aquarium.bo.impl;

import lk.ijse.aquarium.bo.custom.ManageDilivaryBO;
import lk.ijse.aquarium.dao.DAOFactory;
import lk.ijse.aquarium.dao.custom.DilivaryDAO;
import lk.ijse.aquarium.dto.CustomerPaymentDTO;
import lk.ijse.aquarium.dto.DilivaryDTO;
import lk.ijse.aquarium.entity.CustomerPayment;
import lk.ijse.aquarium.entity.Dilivary;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageDilivaryBOImpl implements ManageDilivaryBO {
    DilivaryDAO dilivaryDAO= (DilivaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DILIVARY);


    public boolean saveAllDilivary(DilivaryDTO dto) throws SQLException, ClassNotFoundException {
        return dilivaryDAO.save(dto);
    }
    public boolean updateAllDilivary(DilivaryDTO dto) throws SQLException, ClassNotFoundException {
        return dilivaryDAO.update(dto);
    }
    public DilivaryDTO searchAllDilivary(String id) throws SQLException, ClassNotFoundException {
        return dilivaryDAO.search(id);

    }
    public boolean deleteAllDilivary(String id) throws SQLException, ClassNotFoundException {
        return dilivaryDAO.Delete(id);
    }
    public ArrayList<DilivaryDTO> loadAllDilivary() throws SQLException, ClassNotFoundException {
       return dilivaryDAO.getData();



        // return dilivaryDAO.getData();
    }
    public String getDilivaryId() throws SQLException, ClassNotFoundException {
        return dilivaryDAO.getID();
    }
}
