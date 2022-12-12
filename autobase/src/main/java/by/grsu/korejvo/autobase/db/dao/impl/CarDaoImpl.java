package by.grsu.korejvo.autobase.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.korejvo.autobase.db.dao.AbstractDao;
import by.grsu.korejvo.autobase.db.dao.IDao;
import by.grsu.korejvo.autobase.model.Car;
import by.grsu.korejvo.autobase.web.dto.TableStateDto;

public class CarDaoImpl extends AbstractDao implements IDao<Integer, Car> {

	public static final CarDaoImpl INSTANCE = new CarDaoImpl();

	private CarDaoImpl() {
		super();
	}

	@Override
	public void insert(Car entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"insert into car(number, brand, model, driveUnit, engine, transmission, driverId) values(?,?,?,?,?,?,?)");
			pstmt.setString(1, entity.getNumber());
			pstmt.setString(2, entity.getBrand());
			pstmt.setString(3, entity.getModel());
			pstmt.setString(4, entity.getDriveUnit());
			pstmt.setString(5, entity.getEngine());
			pstmt.setString(6, entity.getTransmission());
			pstmt.setInt(7, entity.getDriverId());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "Car"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert car entity", e);
		}

	}

	@Override
	public void update(Car entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"update car set number=?, brand=?, model=?, driveUnit=?, engine=?, transmission=?, driverId=? where id=?");
			pstmt.setString(1, entity.getNumber());
			pstmt.setString(2, entity.getBrand());
			pstmt.setString(3, entity.getModel());
			pstmt.setString(4, entity.getDriveUnit());
			pstmt.setString(5, entity.getEngine());
			pstmt.setString(6, entity.getTransmission());
			pstmt.setInt(7, entity.getDriverId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update car entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from car where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete car entity", e);
		}
	}

	@Override
	public Car getById(Integer id) {
		Car entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from car where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get car entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Car> getAll() {
		List<Car> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from car");
			while (rs.next()) {
				Car entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select car entities", e);
		}
		return entitiesList;
	}

	private Car rowToEntity(ResultSet rs) throws SQLException {
		Car entity = new Car();
		entity.setId(rs.getInt("id"));
		entity.setNumber(rs.getString("number"));
		entity.setBrand(rs.getString("brand"));
		entity.setModel(rs.getString("model"));
		entity.setDriveUnit(rs.getString("driveUnit"));
		entity.setEngine(rs.getString("engine"));
		entity.setTransmission(rs.getString("transmission"));
		entity.setDriverId(rs.getInt("driverId"));
		return entity;
	}

	@Override
	public List<Car> find(TableStateDto tableStateDto) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public int count() {
		throw new RuntimeException("not implemented");
	}

}
