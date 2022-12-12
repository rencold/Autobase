package autobase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.korejvo.autobase.db.dao.IDao;
import by.grsu.korejvo.autobase.db.dao.impl.RunDaoImpl;
import by.grsu.korejvo.autobase.model.Run;

public class RunDaoTest extends AbstractTest {
	private static final IDao<Integer, Run> dao = RunDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Run entity = new Run();
		entity.setLocationFrom("To");
		entity.setLocationTo("From");
		entity.setDistance(567.3);
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Run entity = new Run();
		entity.setLocationFrom("From");
		entity.setLocationTo("To");
		entity.setDistance(567.3);
		dao.insert(entity);

		String newLocationFrom = "From_NEW";
		entity.setLocationFrom(newLocationFrom);
		String newLocationTo = "To_NEW";
		entity.setLocationTo(newLocationTo);
		Double newDistance = 567.4;
		entity.setDistance(newDistance);
		dao.update(entity);

		Run updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newLocationFrom, updatedEntity.getLocationFrom());
		Assertions.assertEquals(newLocationTo, updatedEntity.getLocationTo());
		Assertions.assertEquals(newDistance, updatedEntity.getDistance());
	}

	@Test
	public void testDelete() {
		Run entity = new Run();
		entity.setLocationFrom("To");
		entity.setLocationTo("From");
		entity.setDistance(567.3);
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Run entity = new Run();
		entity.setLocationFrom("To");
		entity.setLocationTo("From");
		entity.setDistance(567.8);
		dao.insert(entity);

		Run selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getLocationFrom(), selectedEntity.getLocationFrom());
		Assertions.assertEquals(entity.getLocationTo(), selectedEntity.getLocationTo());
		Assertions.assertEquals(entity.getDistance(), selectedEntity.getDistance());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Run entity = new Run();
			entity.setLocationFrom("To" + i);
			entity.setLocationTo("From" + i);
			entity.setDistance(567.3 + i);
			dao.insert(entity);
		}
		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
