package DAO;

import model.PC;
import java.util.List;

public interface PCDAO {
    boolean insert(PC pc);

    boolean update(PC pc);

    boolean delete(int id);

    List<PC> findAll();

    PC findById(int id);

    List<PC> findAvailable();
}