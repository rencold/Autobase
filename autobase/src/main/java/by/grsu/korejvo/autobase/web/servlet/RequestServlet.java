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
import by.grsu.korejvo.autobase.db.dao.impl.RequestDaoImpl;
import by.grsu.korejvo.autobase.db.dao.impl.RunDaoImpl;
import by.grsu.korejvo.autobase.model.Car;
import by.grsu.korejvo.autobase.model.Request;
import by.grsu.korejvo.autobase.model.Run;
import by.grsu.korejvo.autobase.web.dto.CarDto;
import by.grsu.korejvo.autobase.web.dto.RequestDto;
import by.grsu.korejvo.autobase.web.dto.RunDto;

public class RequestServlet extends HttpServlet {
	private static final IDao<Integer, Request> requestDao = RequestDaoImpl.INSTANCE;
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
	private static final IDao<Integer, Run> runDao = RunDaoImpl.INSTANCE;

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
		List<Request> requests = requestDao.getAll();

		List<RequestDto> dtos = requests.stream().map((entity) -> {
			RequestDto dto = new RequestDto();
			dto.setId(entity.getId());
			dto.setCustName(entity.getCustName());
			dto.setPhoneNumber(entity.getPhoneNumber());
			dto.setStatement(entity.getStatement());

			Car car = carDao.getById(entity.getCarId());
			dto.setCarNumber(car.getNumber());

			Run run = runDao.getById(entity.getRunId());
			dto.setRunId(run.getId());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("request.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String requestIdStr = req.getParameter("id");
		RequestDto dto = new RequestDto();
		if (!Strings.isNullOrEmpty(requestIdStr)) {
			// object edit
			Integer requestId = Integer.parseInt(requestIdStr);
			Request entity = requestDao.getById(requestId);
			dto.setId(entity.getId());
			dto.setCustName(entity.getCustName());
			dto.setPhoneNumber(entity.getPhoneNumber());
			dto.setRunId(entity.getRunId());
			dto.setCarId(entity.getCarId());
			dto.setStatement(entity.getStatement());
		}
		req.setAttribute("dto", dto);
		req.setAttribute("allCars", getAllCarsDtos());
		req.setAttribute("allRuns", getAllRunDtos());
		req.getRequestDispatcher("request-edit.jsp").forward(req, res);
	}

	private List<CarDto> getAllCarsDtos() {
		return carDao.getAll().stream().map((entity) -> {
			CarDto dto = new CarDto();
			dto.setId(entity.getId());
			dto.setNumber(entity.getNumber());
			return dto;
		}).collect(Collectors.toList());
	}

	private List<RunDto> getAllRunDtos() {
		return runDao.getAll().stream().map((entity) -> {
			RunDto dto = new RunDto();
			dto.setId(entity.getId());
			dto.setLocationFrom(entity.getLocationFrom());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Request request = new Request();
		String requestIdStr = req.getParameter("id");
		String carIdStr = req.getParameter("carId");
		String runIdStr = req.getParameter("runId");

		request.setCustName(req.getParameter("custName"));
		request.setPhoneNumber(req.getParameter("phoneNumber"));
		request.setRunId(runIdStr == null ? null : Integer.parseInt(runIdStr));
		request.setCarId(carIdStr == null ? null : Integer.parseInt(carIdStr));
		request.setStatement(req.getParameter("statement"));
		if (Strings.isNullOrEmpty(requestIdStr)) {
			requestDao.insert(request);
			} else {
			request.setId(Integer.parseInt(requestIdStr));
			requestDao.update(request);
			}

		res.sendRedirect("/request");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		requestDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
