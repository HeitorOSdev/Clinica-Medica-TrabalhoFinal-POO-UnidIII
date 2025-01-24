package br.ufrn.tads.service;

import java.util.List;

import br.ufrn.tads.model.Medico;
import br.ufrn.tads.repository.MedicoDao;

public class MedicoService {
    
    private MedicoDao medicoDao;

    public MedicoService() {
    	medicoDao = new MedicoDao();
    }
   
//    public void createTable() {
//        return medicoDao.createTable();
//    }
    
    
    public List<Medico> getMedicos() {
        return medicoDao.findAll();
    }

    public boolean save(Medico medico) {
        return medicoDao.save(medico);
    }

    public boolean update(Medico medico, String[] params) {
        return medicoDao.update(medico, params);
    }

    public boolean delete(Long id) {
        return medicoDao.delete(id);
    }
}