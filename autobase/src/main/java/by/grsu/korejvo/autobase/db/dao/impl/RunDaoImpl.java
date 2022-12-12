package by.grsu.korejvo.autobase.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.korejvo.autobase.db.dao.AbstractDao;
import by.grsu.korejvo.autobase.db.dao.IDao;
import by.grsu.korejvo.autobase.model.Run;
import by.grsu.korejvo.autobase.web.dto.TableStateDto;

public class RunDaoImpl extends AbstractDao implements IDao<Integer, Run> {

	public static final RunDaoImpl INSTANCE = new RunDaoImpl();

	private RunDaoImpl() {
		super();
	}

	@Override
	public void insert(Run entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into Run(locationFrom, locationTo, distance) values(?,?,?)");
			pstmt.setString(1, entity.getLocationFrom());
			pstmt.setString(2, entity.getLocationTo());
			pstmt.setDouble(3, entity.getDistance());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "run"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert run entity", e);
		}
	}

	@Override
	public void update(Run entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update Run set locationFrom=?, locationTo=?, distance=?  where id=?");
			pstmt.setString(1, entity.getLocationFrom());
			pstmt.setString(2, entity.getLocationTo());
			pstmt.setDouble(3, entity.getDistance());
			pstmt.setInt(4, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Run entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from Run where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Run entity", e);
		}
	}

	@Override
	public Run getById(Integer id) {
		Run entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from run where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get run entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Run> getAll() {
		List<Run> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from run");
			while (rs.next()) {
				Run entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select run entities", e);
		}
		return entitiesList;
	}

	private Run rowToEntity(ResultSet rs) throws SQLException {
		Run entity = new Run();
		entity.setId(rs.getInt("id"));
		entity.setLocationFrom(rs.getString("locationFrom"));
		entity.setLocationTo(rs.getString("locationTo"));
		entity.setDistance(rs.getDouble("distance"));
		return entity;

	}

	@Override
	public List<Run> find(TableStateDto tableStateDto) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public int count() {
		throw new RuntimeException("not implemented");
	}

}
