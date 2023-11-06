package lk.ijse.aquarium.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    public  boolean save(T dto) throws SQLException, ClassNotFoundException ;

    public boolean update(T dto) throws SQLException, ClassNotFoundException ;

    public  T search(String id) throws SQLException, ClassNotFoundException ;

    public  boolean Delete( String id) throws SQLException, ClassNotFoundException ;

    public String getID() throws SQLException, ClassNotFoundException ;

    public ArrayList<T> getData() throws SQLException, ClassNotFoundException ;
}
