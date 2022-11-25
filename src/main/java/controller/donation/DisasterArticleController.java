package controller.donation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.Controller;
import controller.user.RegisterUserController;
import controller.user.UserSessionUtils;
import model.DisasterArticle;
import model.service.DisasterManager;

public class DisasterArticleController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Logger log = (Logger) LoggerFactory.getLogger(RegisterUserController.class);

		if (request.getMethod().equals("GET")) {
			return "/donationForm/disasterForm.jsp";
		}

		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);

//      String category = "disaster";

		DisasterArticle disaster = new DisasterArticle(0, request.getParameter("title"), "disaster",
				request.getParameter("deadline"), request.getParameter("bank_name"), request.getParameter("acc_holder"),
				request.getParameter("acc_num"), "Y", request.getParameter("due_date"),
				request.getParameter("use_plan"), request.getParameter("other_text"), null, null, "N", 0, userId,
				request.getParameter("type"), request.getParameter("name"), request.getParameter("area"), 0,
				request.getParameter("situation"));

		log.debug("DisasterDonation Controller : {}", disaster);

		try {
			DisasterManager manager = DisasterManager.getInstance();

			int articleId = manager.create(disaster);

			disaster.setArticleId(articleId);

			request.setAttribute("article", disaster);

			return "/donationList/disasterArticle.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("exception", e);

			request.setAttribute("disaster", disaster);

			return "/donationForm/disasterForm.jsp";
		}
	}
}