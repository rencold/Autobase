package autobase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.korejvo.autobase.db.dao.IDao;
import by.grsu.korejvo.autobase.db.dao.impl.CarDaoImpl;
import by.grsu.korejvo.autobase.db.dao.impl.DriverDaoImpl;
import by.grsu.korejvo.autobase.db.dao.impl.RequestDaoImpl;
import by.grsu.korejvo.autobase.db.dao.impl.RunDaoImpl;
import by.grsu.korejvo.autobase.model.Car;
import by.grsu.korejvo.autobase.model.Driver;
import by.grsu.korejvo.autobase.model.Request;
import by.grsu.korejvo.autobase.model.Run;

public class RequestDaoTest extends AbstractTest {

	private static final IDao<Integer, Request> requestDao = RequestDaoImpl.INSTANCE;
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
	private static final IDao<Integer, Run> runDao = RunDaoImpl.INSTANCE;
	private static final IDao<Integer, Driver> driverDao = DriverDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Request entity = new Request();
		entity.setRunId(saveRun("VW").getId());
		entity.setCarId(saveCar("alex", "reno").getId());
		entity.setCustName("Neman");
		entity.setPhoneNumber("+375");
		entity.setStatement("Neman");
		requestDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Request entity = new Request();
		entity.setRunId(saveRun("VW").getId());
		entity.setCarId(saveCar("alex", "reno").getId());
		entity.setCustName("Neman");
		entity.setPhoneNumber("+375");
		entity.setStatement("await");
		requestDao.insert(entity);

		String newCustName = "Steclo";
		entity.setCustName(newCustName);
		String newPhoneNumber = "+7";
		entity.setPhoneNumber(newPhoneNumber);
		String newStatement = "done";
		entity.setStatement(newStatement);
		requestDao.update(entity);

		Request updatedEntity = requestDao.getById(entity.getId());
		Assertions.assertNotEquals(newCustName, updatedEntity.getCustName());
		Assertions.assertNotEquals(newPhoneNumber, updatedEntity.getPhoneNumber());
		Assertions.assertNotEquals(newStatement, updatedEntity.getStatement());
	}

	@Test
	public void testDelete() {
		Request entity = new Request();
		entity.setRunId(saveRun("VW").getId());
		entity.setCarId(saveCar("alex", "reno").getId());
		entity.setCustName("Neman");
		entity.setPhoneNumber("+375");
		entity.setStatement("Neman");
		requestDao.insert(entity);

		requestDao.delete(entity.getId());

		Assertions.assertNull(requestDao.getById(entity.getId()));

	}

	@Test
	public void testGetById() {
		Request entity = new Request();
		entity.setRunId(saveRun("VW").getId());
		entity.setCarId(saveCar("alex", "reno").getId());
		entity.setCustName("Neman");
		entity.setPhoneNumber("+375");
		entity.setStatement("Neman");
		requestDao.insert(entity);

		Request selectedEntity = requestDao.getById(entity.getId());

		Assertions.assertEquals(entity.getCustName(), selectedEntity.getCustName());
		Assertions.assertEquals(entity.getRunId(), selectedEntity.getRunId());
		Assertions.assertEquals(entity.getCarId(), selectedEntity.getCarId());
		Assertions.assertEquals(entity.getPhoneNumber(), selectedEntity.getPhoneNumber());
		Assertions.assertEquals(entity.getStatement(), selectedEntity.getStatement());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Request entity = new Request();
			entity.setRunId(saveRun("VW" + i).getId());
			entity.setCarId(saveCar("alex" + i, "reno" + i).getId());
			entity.setCustName("Neman" + i);
			entity.setPhoneNumber("+375" + i);
			entity.setStatement("Neman" + i);
			requestDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, requestDao.getAll().size());
	}

	private Run saveRun(String string) {
		Run entity = new Run();
		entity.setLocationFrom("Lida");
		entity.setLocationTo("Mogilev");
		entity.setDistance(567.8);
		runDao.insert(entity);
		return entity;
	}

	private Car saveCar(String driver, String car) {
		Driver driverEntity = new Driver();
		driverEntity.setName(driver);
		driverEntity.setPhoneNumber(driver);
		driverEntity.setExp(driver);
		driverEntity.setStatement(driver);
		driverDao.insert(driverEntity);

		Car carEntity = new Car();
		carEntity.setDriverId(driverEntity.getId());
		carEntity.setNumber(car);
		carEntity.setBrand(car);
		carEntity.setModel(car);
		carEntity.setDriveUnit(car);
		carEntity.setEngine(car);
		carEntity.setTransmission(car);

		carDao.insert(carEntity);
		return carEntity;
	}

}
