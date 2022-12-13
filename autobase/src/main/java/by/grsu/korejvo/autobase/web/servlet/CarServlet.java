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
import by.grsu.korejvo.autobase.db.dao.impl.CarDaoImpl;
import by.grsu.korejvo.autobase.db.dao.impl.DriverDaoImpl;
import by.grsu.korejvo.autobase.model.Car;
import by.grsu.korejvo.autobase.model.Driver;
import by.grsu.korejvo.autobase.web.dto.CarDto;
import by.grsu.korejvo.autobase.web.dto.DriverDto;
import by.grsu.korejvo.autobase.web.dto.RunDto;

public class CarServlet extends HttpServlet {
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
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
		List<Car> cars = carDao.getAll(); // get data

		List<CarDto> dtos = cars.stream().map((entity) -> {
			CarDto dto = new CarDto();
			dto.setId(entity.getId());
			dto.setNumber(entity.getNumber());
			dto.setBrand(entity.getBrand());
			dto.setModel(entity.getModel());
			dto.setDriveUnit(entity.getDriveUnit());
			dto.setEngine(entity.getEngine());
			dto.setTransmission(entity.getTransmission());

			Driver driver = driverDao.getById(entity.getDriverId());
			dto.setDriverId(driver.getId());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("car.jsp").forward(req, res);
	}
	
	private List<DriverDto> getAllDriverDtos() {
		return driverDao.getAll().stream().map((entity) -> {
			DriverDto dto = new DriverDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}
	

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String carIdStr = req.getParameter("id");
		CarDto dto = new CarDto();
		if (!Strings.isNullOrEmpty(carIdStr)) {
			Integer carId = Integer.parseInt(carIdStr);
			Car entity = carDao.getById(carId);
			dto.setId(entity.getId());
			dto.setNumber(entity.getNumber());
			dto.setBrand(entity.getBrand());
			dto.setModel(entity.getModel());
			dto.setDriveUnit(entity.getDriveUnit());
			dto.setEngine(entity.getEngine());
			dto.setTransmission(entity.getTransmission());
			dto.setDriverId(entity.getDriverId());
		}
		req.setAttribute("dto", dto);
		req.setAttribute("allDrivers", getAllDriverDtos());
		req.getRequestDispatcher("car-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Car car = new Car();
		String carIdStr = req.getParameter("id");
		String driverIdStr = req.getParameter("driverId");

		car.setNumber(req.getParameter("number"));
		car.setBrand(req.getParameter("brand"));
		car.setModel(req.getParameter("model"));
		car.setDriveUnit(req.getParameter("driveUnit"));
		car.setEngine(req.getParameter("engine"));
		car.setTransmission(req.getParameter("transmission"));
		car.setDriverId(driverIdStr == null ? null : Integer.parseInt(driverIdStr));
		res.sendRedirect("/car");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		carDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
