package kr.co.greenart.server;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/", "/index", "/server/status"})
public class ServerStatusServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LocalDateTime serverTime = LocalDateTime.now();
		
		DBStatusService service = new DBStatusServiceImpl();
		LocalDateTime dbTime = service.selectDbTime();
		
		req.setAttribute("serverTime", serverTime);
		req.setAttribute("dbTime", dbTime);
		req.getRequestDispatcher("/WEB-INF/views/serverStatus.jsp").forward(req, resp);
	}
}