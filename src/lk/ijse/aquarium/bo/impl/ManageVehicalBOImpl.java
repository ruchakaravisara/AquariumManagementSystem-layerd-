package lk.ijse.aquarium.bo.impl;

import lk.ijse.aquarium.bo.custom.ManageVehicalBO;
import lk.ijse.aquarium.dao.DAOFactory;
import lk.ijse.aquarium.dao.custom.VehicalDAO;
import lk.ijse.aquarium.dto.SupplierPaymentDTO;
import lk.ijse.aquarium.dto.VehicalDTO;
import lk.ijse.aquarium.entity.SupplierPayment;
import lk.ijse.aquarium.entity.Vehical;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageVehicalBOImpl implements ManageVehicalBO {
        VehicalDAO vehicalDAO= (VehicalDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICAL);
        public boolean saveAllVehical(VehicalDTO dto) throws SQLException, ClassNotFoundException {
                return vehicalDAO.save(dto);
        }
        public boolean updateAllVehical(VehicalDTO dto) throws SQLException, ClassNotFoundException {
                return vehicalDAO.update(dto);
        }
        public VehicalDTO searchAllVehical(String id) throws SQLException, ClassNotFoundException {
                return vehicalDAO.search(id);

        }
        public boolean deleteAllVehical(String id) throws SQLException, ClassNotFoundException {
                return vehicalDAO.Delete(id);
        }
        public ArrayList<VehicalDTO> loadAllVehical() throws SQLException, ClassNotFoundException {
              return vehicalDAO.getData();

        }
        public String getVehicalId() throws SQLException, ClassNotFoundException {
                return vehicalDAO.getID();
        }
        }
