package fr.applications.appli3.dal;

public abstract class DAOFactory {
	public static RepasDAO getRepasDAO() {
		return new RepasDAOJdbcImpl();
	}

}
