package model.service;

import java.sql.SQLException;

import model.DisasterArticle;
import model.DonationImage;
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

	public int update(DisasterArticle disaster) throws SQLException {
		return disasterDao.update(disaster);
	}

	public int create_image(DonationImage image) throws SQLException, ExistingUserException {
		return disasterDao.create_image(image);
	}

	public int remove(int articleid) throws SQLException, UserNotFoundException {
		return disasterDao.remove(articleid);
	}

	public DisasterArticle findDisasterArticle(int articleId) 
            throws SQLException, ArticleNotFoundException {
            DisasterArticle disasterArticle = disasterDao.findDisasterArticle(articleId);
            
            if (disasterArticle == null) {
                throw new ArticleNotFoundException(articleId + "는 존재하지 않는 socialGroup article ID입니다.");
            }
            return disasterArticle;
        }

	public DisasterDAO getArticleDAO() {
		return this.disasterDao;
	}
}