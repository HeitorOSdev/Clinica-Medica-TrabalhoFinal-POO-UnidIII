package br.ufrn.tads.service;

import java.util.List;

import br.ufrn.tads.model.Paciente;
import br.ufrn.tads.repository.PacienteDao;

public class PacienteService {
    
    private PacienteDao pacienteDao;

    public PacienteService() {
        pacienteDao = new PacienteDao();
    }
   
    public List<Paciente> getPacientes() {
        return pacienteDao.findAll();
    }

    public boolean save(Paciente paciente) {
        return pacienteDao.save(paciente);
    }

    public boolean update(Paciente paciente, String[] params) {
        return pacienteDao.update(paciente, params);
    }

    public boolean delete(Long id) {
        return pacienteDao.delete(id);
    }
}