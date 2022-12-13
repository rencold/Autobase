package by.grsu.korejvo.autobase.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.korejvo.autobase.db.dao.IDao;
import by.grsu.korejvo.autobase.db.dao.impl.DriverDaoImpl;
import by.grsu.korejvo.autobase.model.Driver;
import by.grsu.korejvo.autobase.web.dto.DriverDto;

public class DriverServlet extends HttpServlet {
	private static final IDao<Integer, Driver> driverDao = DriverDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<Driver> drivers = driverDao.getAll();

		List<DriverDto> dtos = drivers.stream().map((entity) -> {
			DriverDto dto = new DriverDto();

			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setPhoneNumber(entity.getPhoneNumber());
			dto.setExp(entity.getExp());
			dto.setStatement(entity.getStatement());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("driver.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String driverIdStr = req.getParameter("id");
		DriverDto dto = new DriverDto();
		if (!Strings.isNullOrEmpty(driverIdStr)) {

			Integer driverId = Integer.parseInt(driverIdStr);
			Driver entity = driverDao.getById(driverId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setPhoneNumber(entity.getPhoneNumber());
			dto.setExp(entity.getExp());
			dto.setStatement(entity.getStatement());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("driver-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Driver driver = new Driver();
		String driverIdStr = req.getParameter("id");

		driver.setName(req.getParameter("name"));
		driver.setPhoneNumber(req.getParameter("phoneNumber"));
		driver.setExp(req.getParameter("exp"));
		driver.setStatement(req.getParameter("statement"));
		if (Strings.isNullOrEmpty(driverIdStr)) {
			driverDao.insert(driver);
			} else {
			driver.setId(Integer.parseInt(driverIdStr));
			driverDao.update(driver);
			}
		res.sendRedirect("/driver");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		driverDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}