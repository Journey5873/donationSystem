package controller.donation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.DisasterArticle;
import model.service.DisasterManager;

public class UpdateDisasterArticleController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateAnimalArticleController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (request.getMethod().equals("GET")) {
			String articleIdStr = request.getParameter("articleId");
			int articleId = Integer.parseInt(articleIdStr);

			DisasterManager manager = DisasterManager.getInstance();

			DisasterArticle article = manager.findDisasterArticle(articleId);

			request.setAttribute("article", article);

			log.debug("UpdateForm article : {}", article);

			return "/donationForm/disasterUpdateForm.jsp";
		}

		String updateId = request.getParameter("userId");
		String articleIdStr = request.getParameter("articleId");
		int articleId = Integer.parseInt(articleIdStr);

		DisasterArticle disaster = new DisasterArticle(articleId, request.getParameter("title"), "disaster",
				request.getParameter("deadline"), request.getParameter("bank_name"), request.getParameter("acc_holder"),
				request.getParameter("acc_num"), "Y", request.getParameter("due_date"),
				request.getParameter("use_plan"), request.getParameter("other_text"), null, null, "N", 0, updateId,
				request.getParameter("type"), request.getParameter("name"), request.getParameter("are"),
				Integer.parseInt(request.getParameter("damage_amount")), request.getParameter("situation"));

		log.debug("UpdateDisasterArticle Controller : {}", disaster);
		
		DisasterManager manager = DisasterManager.getInstance();
		
		manager.update(disaster);

		log.debug("UpdateAnimalArticle Controller After update : {}", disaster);

		DisasterArticle article = manager.findDisasterArticle(articleId);
		
		request.setAttribute("article", article);

		return "/donationList/disasterArticle.jsp";
	}

}