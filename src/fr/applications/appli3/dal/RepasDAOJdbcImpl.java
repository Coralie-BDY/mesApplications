package fr.applications.appli3.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.applications.appli3.BusinessException;
import fr.applications.appli3.bo.Aliment;
import fr.applications.appli3.bo.Repas;

public class RepasDAOJdbcImpl implements RepasDAO {

	private static final String INSERT_REPAS="insert into repas(date_repas, heure_repas) values(?,?)";
	private static final String INSERT_ALIMENT="insert into aliments(nom, id_repas) values(?,?)";
	private static final String SELECT_ALL=" SELECT " + 
											"	r.id as id_repas," + 
											"	r.date_repas," + 
											"	r.heure_repas," + 
											"	a.id as id_aliment," + 
											"	a.nom" + 
											" FROM" + 
											"	REPAS r" + 
											"	INNER JOIN ALIMENTS a ON r.id=a.id_repas" +
											"	ORDER BY r.date_repas desc, r.heure_repas desc";
	private static final String SELECT_UNE_DATE=" SELECT " + 
											"	r.id as id_repas," + 
											"	r.date_repas," + 
											"	r.heure_repas," + 
											"	a.id as id_aliment," + 
											"	a.nom" + 
											" FROM" + 
											"	REPAS r" + 
											"	INNER JOIN ALIMENTS a ON r.id=a.id_repas" +
											" WHERE r.date_repas=?"+
											"	ORDER BY r.date_repas desc, r.heure_repas desc";
	@Override
	public void insert(Repas lunch) throws BusinessException {
		if (lunch == null) {
			BusinessException businessException = new BusinessException();
			businessException.addError(CodeResultDAL.INSERT_OBJECT_NULL);
			throw businessException;
		} try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_REPAS, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setDate(1, java.sql.Date.valueOf(lunch.getDate()));
				pstmt.setTime(2, java.sql.Time.valueOf(lunch.getHour()));
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					lunch.setId(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				pstmt = cnx.prepareStatement(INSERT_ALIMENT, PreparedStatement.RETURN_GENERATED_KEYS);
				for (Aliment aliment : lunch.getAlimentsList()) {
					pstmt.setString(1, aliment.getName());
					pstmt.setInt(2, lunch.getId());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if(rs.next()) {
						aliment.setId(rs.getInt(1));
					}
					rs.close();
				}
				pstmt.close();
				cnx.commit();
			} catch(Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.addError(CodeResultDAL.INSERT_OBJECT_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<Repas> select() throws BusinessException {
		List<Repas> lunchList = new ArrayList<Repas>();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			Repas dailylunch = new Repas();
			while(rs.next()) {
				if(rs.getInt("id_repas")!=dailylunch.getId()) {
					dailylunch = repasBuilder(rs);
					lunchList.add(dailylunch);
				}
				Aliment aliment = alimentBuilder(rs);
				dailylunch.getAlimentsList().add(aliment);
			}
		} catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.addError(CodeResultDAL.READ_LUNCH_ECHEC);
			throw businessException;
		}
		return lunchList;
	}

	@Override
	public List<Repas> select(LocalDate dateSearched) throws BusinessException {
		List<Repas> lunchList = new ArrayList<Repas>();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UNE_DATE);
			pstmt.setDate(1, java.sql.Date.valueOf(dateSearched));
			ResultSet rs = pstmt.executeQuery();
			Repas dailyLunch = new Repas();
			while(rs.next()) {
				if(rs.getInt("id_repas")!= dailyLunch.getId()) {
					dailyLunch = repasBuilder(rs);
					lunchList.add(dailyLunch);
				}
				Aliment aliment = alimentBuilder(rs);
				dailyLunch.getAlimentsList().add(aliment);
			}
		} catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.addError(CodeResultDAL.READ_LUNCH_ECHEC);
			throw businessException;
		}
		return lunchList;
	}

	
	@Override
	public List<Repas> select(int page) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Aliment alimentBuilder(ResultSet rs) throws SQLException {
		Aliment aliment = new Aliment(rs.getInt("id_aliment"), rs.getString("nom"));
		return aliment;
	}

	private Repas repasBuilder(ResultSet rs) throws SQLException {
		Repas repasCourant;
		repasCourant=new Repas();
		repasCourant.setId(rs.getInt("id_repas"));
		repasCourant.setDate(rs.getDate("date_repas").toLocalDate());
		repasCourant.setHeure(rs.getTime("heure_repas").toLocalTime());
		return repasCourant;
	}


}
