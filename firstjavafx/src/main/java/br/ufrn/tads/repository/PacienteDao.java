package br.ufrn.tads.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import br.ufrn.tads.model.Paciente;

public class PacienteDao implements Dao<Paciente> {
    @Override
    public Paciente findById(Long id) {
    	Paciente paciente = null;
        String sql = "select * from paciente where id = ?"; // ? is a parameters for the prepared statement
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
            	paciente = new Paciente();
            	paciente.setId(resultSet.getLong("id")); // "id" is the column at postgres
            	paciente.setNome (resultSet.getString("nome"));
            	paciente.setCpf(resultSet.getString("cpf"));
            	paciente.setDataNascimento(resultSet.getDate("dataNascimento")); ;
            	paciente.setTelefone(resultSet.getString("telefone"));
            	paciente.setEmail(resultSet.getString("email"));
            	paciente.setGenero(resultSet.getString("genero"));        	
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
        return paciente;
    }

    @Override
    public List<Paciente> findAll() { //listAll (if the database is huge, consider the use of pagination)
        List<Paciente> pacientes = new ArrayList<Paciente>();
        String sql = "select * from paciente"; 
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
            	Paciente paciente = new Paciente();
            	paciente = new Paciente();
            	paciente.setId(resultSet.getLong("id")); // "id" is the column at postgres
            	paciente.setNome (resultSet.getString("nome"));
            	paciente.setCpf(resultSet.getString("cpf"));
            	paciente.setDataNascimento(resultSet.getDate("dataNascimento")); ;
            	paciente.setTelefone(resultSet.getString("telefone"));
            	paciente.setEmail(resultSet.getString("email"));
            	paciente.setGenero(resultSet.getString("genero")); 

                pacientes.add(paciente); //add the object filled with database data to products list
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
        return pacientes;
    }

    @Override
    public boolean save(Paciente paciente) {
        String sql = "insert into paciente (nome, cpf, dataNascimento, telefone, email, genero)" + " values (?, ?, ?, ?, ?, ?)"; 
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        
        try {
        	conn = DBconnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getCpf());
            preparedStatement.setDate(3, (java.sql.Date) paciente.getDataNascimento());
            preparedStatement.setString(4, paciente.getTelefone());
            preparedStatement.setString(5, paciente.getEmail());
            preparedStatement.setString(6, paciente.getGenero());
            
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
    public boolean update(Paciente paciente, String[] params) {
        // if you use params, use parse methods (parseFloat, parseLong etc.)
        String sql = "update product set nome = ?, cpf = ?, dataNascimento = ?, telefone = ?, email = ?, genero = ?  where id = ?"; 
        Connection conn = null;
        // prepares a query
        PreparedStatement preparedStatement = null;
        
        try {
        	conn = DBconnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getCpf());
            preparedStatement.setDate(3, (java.sql.Date) paciente.getDataNascimento());
            preparedStatement.setString(4, paciente.getTelefone());
            preparedStatement.setString(5, paciente.getEmail());
            preparedStatement.setString(6, paciente.getGenero());
            preparedStatement.setLong(7, paciente.getId());
            
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
        String sql = "delete from paciente where id = ?"; 
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
