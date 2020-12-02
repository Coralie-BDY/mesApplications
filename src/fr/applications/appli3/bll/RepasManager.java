package fr.applications.appli3.bll;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import fr.applications.appli3.BusinessException;
import fr.applications.appli3.bo.Aliment;
import fr.applications.appli3.bo.Repas;
import fr.applications.appli3.dal.DAOFactory;
import fr.applications.appli3.dal.RepasDAO;

public class RepasManager {
	private RepasDAO repasDAO;
	
	public RepasManager() {
		this.repasDAO = DAOFactory.getRepasDAO();	
	}
	public Repas addLunch(LocalDate date, LocalTime hour, List<String> alimentsList) throws BusinessException {	
		BusinessException businessException = new BusinessException();
		this.validateDateHour(date, hour, businessException);
		this.validateAlimentsList(alimentsList, businessException);
		
		Repas lunch = null;
		
		if (!businessException.hasErrors()) {
			lunch = new Repas();
			lunch.setDate(date);
			lunch.setHeure(hour);
			for(String aliment: alimentsList) {
				lunch.getAlimentsList().add(new Aliment(aliment));
			}
			this.repasDAO.insert(lunch);
		} else {
			throw businessException;
		}
		return lunch;
	}

	private void validateDateHour(LocalDate date, LocalTime hour, BusinessException businessException) {
		if (date==null || date.isAfter(LocalDate.now()) ||(date.isEqual(LocalDate.now()) && hour.isAfter(LocalTime.now())))
		{
			businessException.addError(ResultCodeBLL.REPAS_DATE_ERROR);
		}
	}

	private void validateAlimentsList(List<String> alimentsList, BusinessException businessException) {
		if(alimentsList==null || alimentsList.size()==0) {
			businessException.addError(ResultCodeBLL.REPAS_ALIMENTS_ERROR);
		} else {
			for (String aliment : alimentsList) {
				if(aliment.length()>50) {
					businessException.addError(ResultCodeBLL.REPAS_ALIMENTS_ERROR);
					break;
				}
			}
		}
		
	}

	public List<Repas> allLunchesSelected() throws BusinessException {
		return this.repasDAO.select();
	}

	public List<Repas> oneDayLunches(LocalDate dateFiltre) throws BusinessException {
		return this.repasDAO.select(dateFiltre);
	}
	
	
	
}

	


