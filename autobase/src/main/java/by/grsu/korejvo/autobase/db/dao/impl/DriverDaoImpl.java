package by.grsu.korejvo.autobase.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.korejvo.autobase.db.dao.AbstractDao;
import by.grsu.korejvo.autobase.db.dao.IDao;
import by.grsu.korejvo.autobase.model.Driver;
import by.grsu.korejvo.autobase.web.dto.TableStateDto;

public class DriverDaoImpl extends AbstractDao implements IDao<Integer, Driver> {

	public static final DriverDaoImpl INSTANCE = new DriverDaoImpl();

	private DriverDaoImpl() {
		super();
	}

	@Override
	public void insert(Driver entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into driver(name, phoneNumber, exp, statement) values(?,?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getPhoneNumber());
			pstmt.setString(3, entity.getExp());
			pstmt.setString(4, entity.getStatement());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "driver"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert driver entity", e);
		}

	}

	@Override
	public void update(Driver entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update driver set name=?, phoneNumber=?, exp=?, statement=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getPhoneNumber());
			pstmt.setString(3, entity.getExp());
			pstmt.setString(4, entity.getStatement());
			pstmt.setInt(5, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update driver entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from driver where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete driver entity", e);
		}
	}

	@Override
	public Driver getById(Integer id) {
		Driver entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from driver where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get driver entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Driver> getAll() {
		List<Driver> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from driver");
			while (rs.next()) {
				Driver entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select driver entities", e);
		}
		return entitiesList;
	}

	private Driver rowToEntity(ResultSet rs) throws SQLException {
		Driver entity = new Driver();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setPhoneNumber(rs.getString("phoneNumber"));
		entity.setExp(rs.getString("exp"));
		entity.setStatement(rs.getString("statement"));
		return entity;

	}

	@Override
	public List<Driver> find(TableStateDto tableStateDto) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public int count() {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from driver");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get drivers count", e);
		}
	}
}
