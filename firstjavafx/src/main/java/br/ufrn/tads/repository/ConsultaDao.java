package br.ufrn.tads.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.tads.model.Consulta;

public class ConsultaDao implements Dao<Consulta> {
    @Override
    public Consulta findById(Long id) {
    	Consulta consulta = null;
        String sql = "select * from consulta where id = ?"; // ? is a parameters for the prepared statement
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null; //stores the query result

        try {
            conn = DBconnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            // sending the parameter to sql execution
            preparedStatement.setInt(1, id.intValue()); // id is an object, not primitive (intValue required)
            resultSet = preparedStatement.executeQuery();
            // iterates the resultSet and stores in the object the column values from the database
            while (resultSet.next()){
            	consulta = new Consulta();
            	consulta.setId(resultSet.getLong("id")); // "id" is the column at postgres
            	consulta.setFkPaciente(resultSet.getLong("fkPaciente"));
            	consulta.setFkMedico(resultSet.getLong("fkMedico"));
            	consulta.setData(resultSet.getDate("data"));
            	consulta.setQueixa(resultSet.getString("queixa"));
            	consulta.setDescricao(resultSet.getString("descricao"));
            	consulta.setRelatosClinicos(resultSet.getString("relatosClinicos"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return consulta;
    }

    @Override
    public List<Consulta> findAll() { //listAll (if the database is huge, consider the use of pagination)
        List<Consulta> consultas = new ArrayList<Consulta>();
        String sql = "select * from consulta"; 
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null; //stores the query result

        try {
            conn = DBconnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            // iterates the resultSet and stores in the object the column values from the database
            while (resultSet.next()){
            	Consulta consulta = new Consulta();
            	consulta.setId(resultSet.getLong("id")); // "id" is the column at postgres
            	consulta.setFkPaciente(resultSet.getLong("fkPaciente"));
            	consulta.setFkMedico(resultSet.getLong("fkMedico"));
            	consulta.setData(resultSet.getDate("data"));
            	consulta.setQueixa(resultSet.getString("queixa"));
            	consulta.setDescricao(resultSet.getString("descricao"));
            	consulta.setRelatosClinicos(resultSet.getString("relatosClinicos"));

                consultas.add(consulta); //add the object filled with database data to products list
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return consultas;
    }

    @Override
    public boolean save(Consulta consulta) {
        String sql = "insert into consulta (fkPaciente, fkMedico, data, queixa, descricao, relatosClinicos)" + " values (?, ?, ?, ?, ?, ?)"; 
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        
        try {
            conn = DBconnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
         
            preparedStatement.setLong(1, consulta.getFkPaciente());
            preparedStatement.setLong(2, consulta.getFkMedico());
            preparedStatement.setDate(3, (java.sql.Date) consulta.getData());
            preparedStatement.setString(4, consulta.getQueixa());
            preparedStatement.setString(5, consulta.getDescricao());
            preparedStatement.setString(6, consulta.getRelatosClinicos());
            
            preparedStatement.execute(); //it is not a query. It is an insert command
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
                return true;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Consulta consulta, String[] params) {
        // if you use params, use parse methods (parseFloat, parseLong etc.)
        String sql = "update consulta set fkPaciente = ?, fkMedico = ?, data = ?, queixa = ?, descricao = ?, relatosClinicos = ? where id = ?"; 
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        
        try {
            conn = DBconnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);    
            preparedStatement.setLong(1, consulta.getFkPaciente());
            preparedStatement.setLong(2, consulta.getFkMedico());
            preparedStatement.setDate(3, (java.sql.Date) consulta.getData());
            preparedStatement.setString(4, consulta.getQueixa());
            preparedStatement.setString(5, consulta.getDescricao());
            preparedStatement.setString(6, consulta.getRelatosClinicos());
            preparedStatement.setLong(7, consulta.getId());
            
            preparedStatement.execute(); //it is not a query. It is an insert command
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
                return true;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "delete from consulta where id = ?"; 
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        
        try {
            conn = DBconnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute(); //it is not a query. It is an insert command
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
                return true;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
