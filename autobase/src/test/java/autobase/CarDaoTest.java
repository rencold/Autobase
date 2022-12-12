package autobase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.korejvo.autobase.db.dao.IDao;
import by.grsu.korejvo.autobase.db.dao.impl.CarDaoImpl;
import by.grsu.korejvo.autobase.db.dao.impl.DriverDaoImpl;
import by.grsu.korejvo.autobase.model.Car;
import by.grsu.korejvo.autobase.model.Driver;

public class CarDaoTest extends AbstractTest {
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
	private static final IDao<Integer, Driver> driverDao = DriverDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Car entity = new Car();
		entity.setNumber("6578 HI");
		entity.setBrand("Folcswagen");
		entity.setModel("Polo");
		entity.setDriveUnit("Mechanical");
		entity.setEngine("Petrol");
		entity.setTransmission("Manual");
		entity.setDriverId(saveDriver("VW").getId());
		carDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Car entity = new Car();
		entity.setNumber("6578 HI");
		entity.setBrand("Folcswagen");
		entity.setModel("Polo");
		entity.setDriveUnit("Mechanical");
		entity.setEngine("Petrol");
		entity.setTransmission("Manual");
		entity.setDriverId(saveDriver("VW").getId());
		carDao.insert(entity);

		String newNumber = "8572 HY";
		entity.setNumber(newNumber);
		String newBrand = "Seat";
		entity.setBrand(newBrand);
		String newModel = "Cordoba";
		entity.setModel(newModel);
		String newDriveUnit = "Mechanical";
		entity.setDriveUnit(newDriveUnit);
		String newEngine = "Petrol";
		entity.setEngine(newEngine);
		String newTransmission = "Automatical";
		entity.setTransmission(newTransmission);
		carDao.update(entity);

		Car updatedEntity = carDao.getById(entity.getId());
		Assertions.assertNotEquals(newNumber, updatedEntity.getNumber());
		Assertions.assertNotEquals(newBrand, updatedEntity.getBrand());
		Assertions.assertNotEquals(newModel, updatedEntity.getModel());
		Assertions.assertEquals(newDriveUnit, updatedEntity.getDriveUnit());
		Assertions.assertEquals(newEngine, updatedEntity.getEngine());
		Assertions.assertNotEquals(newTransmission, updatedEntity.getTransmission());
	}

	@Test
	public void testDelete() {
		Car entity = new Car();
		entity.setNumber("8679 HY_NEW");
		entity.setBrand("Folcswagen");
		entity.setModel("Polo");
		entity.setDriveUnit("Mechanical");
		entity.setEngine("Petrol");
		entity.setTransmission("Manual");
		entity.setDriverId(saveDriver("VW").getId());
		carDao.insert(entity);

		carDao.delete(entity.getId());

		Assertions.assertNull(carDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Car entity = new Car();
		entity.setNumber("6578 HI");
		entity.setBrand("Folcswagen");
		entity.setModel("Polo");
		entity.setDriveUnit("Mechanical");
		entity.setEngine("Petrol");
		entity.setTransmission("Manual");
		entity.setDriverId(saveDriver("VW").getId());
		carDao.insert(entity);

		Car selectedEntity = carDao.getById(entity.getId());

		Assertions.assertEquals(entity.getNumber(), selectedEntity.getNumber());
		Assertions.assertEquals(entity.getBrand(), selectedEntity.getBrand());
		Assertions.assertEquals(entity.getModel(), selectedEntity.getModel());
		Assertions.assertEquals(entity.getDriveUnit(), selectedEntity.getDriveUnit());
		Assertions.assertEquals(entity.getEngine(), selectedEntity.getEngine());
		Assertions.assertEquals(entity.getTransmission(), selectedEntity.getTransmission());
		Assertions.assertEquals(entity.getDriverId(), selectedEntity.getDriverId());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Car entity = new Car();
			entity.setNumber("6578 HI" + i);
			entity.setBrand("Folcswagen" + i);
			entity.setModel("Polo" + i);
			entity.setDriveUnit("Mechanical" + i);
			entity.setEngine("Petrol" + i);
			entity.setTransmission("Manual" + i);
			entity.setDriverId(saveDriver("VW" + i).getId());
			carDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, carDao.getAll().size());
	}

	private Driver saveDriver(String string) {
		Driver entity = new Driver();
		entity.setName("Maxim");
		entity.setPhoneNumber("+375");
		entity.setExp("10 years");
		entity.setStatement("Free");
		driverDao.insert(entity);
		return entity;
	}

}
