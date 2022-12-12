package by.grsu.korejvo.autobase.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.korejvo.autobase.db.dao.IDao;
import by.grsu.korejvo.autobase.db.dao.impl.DriverDaoImpl;
import by.grsu.korejvo.autobase.model.Driver;
import by.grsu.korejvo.autobase.web.ValidationUtils;

public class DriverServletBackup extends HttpServlet {
	private static final IDao<Integer, Driver> driverDao = DriverDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String paramId = req.getParameter("id");

		// validation
		if (!ValidationUtils.isInteger(paramId)) {
			res.sendError(400); // send HTTP status 400 and close response
			return;
		}

		Integer driverId = Integer.parseInt(paramId); // read request parameter
		Driver driverById = driverDao.getById(driverId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (driverById == null) {
			pw.println("no driver by id=" + driverId);
		} else {
			pw.println(driverById.toString());
		}

		pw.println("</body></html>");
		pw.close();// closing the stream
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();// get the stream to write the data
		pw.println("<html><body>");
		try {
			String paramName = req.getParameter("name");
			String paramPhoneNumber = req.getParameter("phoneNumber");
			String paramExp = req.getParameter("exp");
			String paramStatement = req.getParameter("statement");

			Driver driverEntity = new Driver();
			driverEntity.setName(paramName);
			driverEntity.setPhoneNumber(paramPhoneNumber);
			driverEntity.setExp(paramExp);
			driverEntity.setStatement(paramStatement);
			driverDao.insert(driverEntity);
			pw.println("Saved:" + driverEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}