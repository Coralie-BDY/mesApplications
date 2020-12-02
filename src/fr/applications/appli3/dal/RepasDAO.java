package fr.applications.appli3.dal;

import java.time.LocalDate;
import java.util.List;

import fr.applications.appli3.BusinessException;
import fr.applications.appli3.bo.Repas;

public interface RepasDAO {
	public void insert(Repas lunch) throws BusinessException;
	public List<Repas> select() throws BusinessException;
	public List<Repas> select(LocalDate searchinDate) throws BusinessException;
	public List<Repas> select(int page) throws BusinessException;
}
