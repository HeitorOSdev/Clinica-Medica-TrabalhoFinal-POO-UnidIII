package br.ufrn.tads.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.tads.model.Medico;

public class MedicoDao implements Dao<Medico> {
	
    @Override
    public Medico findById(Long id) {
        Medico medico = null;
        String sql = "select * from medico where id = ?"; // ? is a parameters for the prepared statement
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
                medico = new Medico();
                medico.setId(resultSet.getLong("id")); // "id" is the column at postgres
                medico.setCrm(resultSet.getString("crm"));
                medico.setNome(resultSet.getString("nome"));
                medico.setEspecialidade(resultSet.getString("especialidade"));
                medico.setTelefone(resultSet.getString("telefone"));
                medico.setEmail(resultSet.getString("email"));
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
        return medico;
    }

    @Override
    public List<Medico> findAll() { //listAll (if the database is huge, consider the use of pagination)
        List<Medico> medicos = new ArrayList<Medico>();
        String sql = "select * from medico"; 
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
                Medico medico = new Medico();
                medico.setId(resultSet.getLong("id")); // "id" is the column at postgres
                medico.setCrm(resultSet.getString("crm"));
                medico.setNome(resultSet.getString("nome"));
                medico.setEspecialidade(resultSet.getString("especialidade"));
                medico.setTelefone(resultSet.getString("telefone"));
                medico.setEmail(resultSet.getString("email"));

                medicos.add(medico); //add the object filled with database data to products list
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
        return medicos;
    }

    @Override
    public boolean save(Medico medico) {
        String sql = "insert into medico (nome, crm, especialidade, telefone, email)" + " values (?, ?, ?, ?, ?)"; 
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        
        try {
            conn = DBconnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setString(2, medico.getCrm());
            preparedStatement.setString(3, medico.getEspecialidade());
            preparedStatement.setString(4, medico.getTelefone());
            preparedStatement.setString(5, medico.getEmail());
            
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
    public boolean update(Medico medico, String[] params) {
        // if you use params, use parse methods (parseFloat, parseLong etc.)
    	
        String sql = "update medico set nome = ?, crm = ?, especialidade = ?, telefone = ?, email = ? where id = ?"; 
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        
        try {
            conn = DBconnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setString(2, medico.getCrm());
            preparedStatement.setString(3, medico.getEspecialidade());
            preparedStatement.setString(4, medico.getTelefone());
            preparedStatement.setString(5, medico.getEmail());
            preparedStatement.setLong(6, medico.getId());
            
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
        String sql = "delete from medico where id = ?"; 
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
