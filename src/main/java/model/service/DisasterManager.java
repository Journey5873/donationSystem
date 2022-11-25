package model.service;

import java.sql.SQLException;

import model.DisasterArticle;
import model.dao.DisasterDAO;

public class DisasterManager {
	private static DisasterManager disasterMan = new DisasterManager();
	private DisasterDAO disasterDao;

	private DisasterManager() {
		try {
			disasterDao = new DisasterDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DisasterManager getInstance() {
		return disasterMan;
	}

	public int create(DisasterArticle disaster) throws SQLException {
		return disasterDao.create(disaster);
	}

	public DisasterArticle findDisasterArticle(int article_id) throws SQLException {
		return disasterDao.findDisasterArticle(article_id);
	}

	public int update(DisasterArticle disaster) throws SQLException {
		return disasterDao.update(disaster);
	}

	public int delete(DisasterArticle disaster) throws SQLException {
		return disasterDao.delete(disaster);
	}
}