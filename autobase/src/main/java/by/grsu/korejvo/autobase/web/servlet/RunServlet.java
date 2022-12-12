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
import by.grsu.korejvo.autobase.db.dao.impl.RunDaoImpl;
import by.grsu.korejvo.autobase.model.Run;
import by.grsu.korejvo.autobase.web.dto.RunDto;

public class RunServlet extends HttpServlet {
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

		int totalRuns = runDao.count(); // get count of ALL items

		List<Run> runs = runDao.getAll();

		List<RunDto> dtos = runs.stream().map((entity) -> {
			RunDto dto = new RunDto();

			dto.setId(entity.getId());
			dto.setLocationFrom(entity.getLocationFrom());
			dto.setLocationTo(entity.getLocationTo());
			dto.setDistance(entity.getDistance());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("run.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String runIdStr = req.getParameter("id");
		RunDto dto = new RunDto();
		if (!Strings.isNullOrEmpty(runIdStr)) {

			Integer runId = Integer.parseInt(runIdStr);
			Run entity = runDao.getById(runId);
			dto.setId(entity.getId());
			dto.setLocationFrom(entity.getLocationFrom());
			dto.setLocationTo(entity.getLocationTo());
			dto.setDistance(entity.getDistance());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("run-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Run run = new Run();
		String runIdStr = req.getParameter("id");

		run.setLocationFrom(req.getParameter("locationFrom"));
		run.setLocationTo(req.getParameter("locationTo"));
		run.setDistance(Double.parseDouble(("distance")));
		res.sendRedirect("/run");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		runDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}