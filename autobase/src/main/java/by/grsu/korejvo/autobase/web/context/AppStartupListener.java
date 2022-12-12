package by.grsu.korejvo.autobase.web.context;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.grsu.korejvo.autobase.db.dao.AbstractDao;
import by.grsu.korejvo.autobase.db.dao.IDao;
import by.grsu.korejvo.autobase.db.dao.impl.CarDaoImpl;
import by.grsu.korejvo.autobase.db.dao.impl.DriverDaoImpl;
import by.grsu.korejvo.autobase.db.dao.impl.RequestDaoImpl;
import by.grsu.korejvo.autobase.db.dao.impl.RunDaoImpl;
import by.grsu.korejvo.autobase.model.Car;
import by.grsu.korejvo.autobase.model.Driver;
import by.grsu.korejvo.autobase.model.Request;
import by.grsu.korejvo.autobase.model.Run;

public class AppStartupListener implements ServletContextListener {
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
	private static final IDao<Integer, Driver> driverDao = DriverDaoImpl.INSTANCE;
	private static final IDao<Integer, Request> requestDao = RequestDaoImpl.INSTANCE;
	private static final IDao<Integer, Run> runDao = RunDaoImpl.INSTANCE;

	private static final String DB_NAME = "autobase";

	private void initDb() throws SQLException {
		AbstractDao.init(DB_NAME);
		if (!AbstractDao.isDbExist()) {
			System.out.println(String.format("DB '%s' doesn't exist and will be created", DB_NAME));
			AbstractDao.createDbSchema();
			loadInitialData();
		} else {
			System.out.println(String.format("DB '%s' exists", DB_NAME));
		}
	}

	private void loadInitialData() {

		Run runEntity = new Run();
		runEntity.setLocationFrom("Hrodno");
		runEntity.setLocationTo("Minsc");
		runEntity.setDistance(567.8);
		runDao.insert(runEntity);
		System.out.println("created: " + runEntity);
		System.out.println("initial data created");

		Driver driverEntity = new Driver();
		driverEntity.setName("Alex");
		driverEntity.setPhoneNumber("+3752978804563");
		driverEntity.setExp("4 years");
		driverEntity.setStatement("free");
		driverDao.insert(driverEntity);
		System.out.println("created: " + driverEntity);

		Car carEntity = new Car();
		carEntity.setNumber("6578 HI");
		carEntity.setBrand("Folcswagen");
		carEntity.setModel("Polo");
		carEntity.setDriveUnit("Mechanical");
		carEntity.setEngine("Petrol");
		carEntity.setTransmission("Manual");
		carEntity.setDriverId(driverEntity.getId());
		carDao.insert(carEntity);
		System.out.println("created: " + carEntity);

		Request requestEntity = new Request();
		requestEntity.setCustName("Neman");
		requestEntity.setPhoneNumber("+375");
		requestEntity.setRunId(runEntity.getId());
		requestEntity.setCarId(carEntity.getId());
		requestEntity.setStatement("New");
		requestDao.insert(requestEntity);
		System.out.println("created: " + requestEntity);

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		try {
			initDb();
		} catch (SQLException e) {
			throw new RuntimeException("can't init DB", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}
}