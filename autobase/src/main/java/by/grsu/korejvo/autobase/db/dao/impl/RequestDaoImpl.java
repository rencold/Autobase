package by.grsu.korejvo.autobase.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.korejvo.autobase.db.dao.AbstractDao;
import by.grsu.korejvo.autobase.db.dao.IDao;
import by.grsu.korejvo.autobase.model.Request;
import by.grsu.korejvo.autobase.web.dto.TableStateDto;

public class RequestDaoImpl extends AbstractDao implements IDao<Integer, Request> {

	public static final RequestDaoImpl INSTANCE = new RequestDaoImpl();

	private RequestDaoImpl() {
		super();
	}

	@Override
	public void insert(Request entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"insert into request(custName, phoneNumber, runId, carId, statement) values(?,?,?,?,?)");
			pstmt.setString(1, entity.getCustName());
			pstmt.setString(2, entity.getPhoneNumber());
			pstmt.setInt(3, entity.getRunId());
			pstmt.setInt(4, entity.getCarId());
			pstmt.setString(5, entity.getStatement());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "Request"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert request entity", e);
		}

	}

	@Override
	public void update(Request entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"update request set custName=?, phoneNumber=?, runId=?, carId=?, statement=? where id=?");
			pstmt.setString(1, entity.getCustName());
			pstmt.setString(2, entity.getPhoneNumber());
			pstmt.setInt(3, entity.getRunId());
			pstmt.setInt(4, entity.getCarId());
			pstmt.setString(5, entity.getStatement());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update request entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from request where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete request entity", e);
		}
	}

	@Override
	public Request getById(Integer id) {
		Request entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from request where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get request entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Request> getAll() {
		List<Request> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from request");
			while (rs.next()) {
				Request entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select request entities", e);
		}
		return entitiesList;
	}

	private Request rowToEntity(ResultSet rs) throws SQLException {
		Request entity = new Request();
		entity.setId(rs.getInt("id"));
		entity.setCustName(rs.getString("custName"));
		entity.setPhoneNumber(rs.getString("phoneNumber"));
		entity.setRunId(rs.getInt("runId"));
		entity.setCarId(rs.getInt("carId"));
		entity.setStatement(rs.getString("statement"));
		return entity;
	}

	@Override
	public List<Request> find(TableStateDto tableStateDto) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public int count() {
		throw new RuntimeException("not implemented");
	}
}
