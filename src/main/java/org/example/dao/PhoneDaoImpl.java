package org.example.dao;

import org.example.connection.DatabaseConnection;
import org.example.entity.Phone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDaoImpl implements PhoneDao {

    @Override
    public List<Phone> findAll() {
        return executeQuery("SELECT * FROM phone");
    }

    @Override
    public Phone findById(int id) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM phone WHERE id=?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Phone> findByBrandContaining(String text) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM phone WHERE brand LIKE ? OR model LIKE ?")) {

            String q = "%" + text + "%";
            ps.setString(1, q);
            ps.setString(2, q);

            ResultSet rs = ps.executeQuery();
            return getList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPhone(String brand, String model, double price, String releaseDate) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO phone(brand, model, price, release_date) VALUES(?,?,?,?)")) {

            ps.setString(1, brand);
            ps.setString(2, model);
            ps.setDouble(3, price);
            ps.setDate(4, Date.valueOf(releaseDate));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePhone(int id, String brand, String model, double price) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE phone SET brand=?, model=?, price=? WHERE id=?")) {

            ps.setString(1, brand);
            ps.setString(2, model);
            ps.setDouble(3, price);
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM phone WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ---------- helpers (same style as your PersonDaoImpl) ----------

    private List<Phone> executeQuery(String sql) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return getList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Phone> getList(ResultSet rs) throws SQLException {
        List<Phone> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Phone map(ResultSet rs) throws SQLException {
        Phone phone = new Phone(
                rs.getInt("id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getDouble("price")
        );
        Date date = rs.getDate("release_date");
        if (date != null) phone.setReleaseDate(date.toLocalDate());
        return phone;
    }

    @Override
    public List<Phone> findAllOrderByPrice() {
        return executeQuery("SELECT * FROM phone ORDER BY price");
    }

    @Override
    public List<Phone> findAllOrderByPriceDesc() {
        return executeQuery("SELECT * FROM phone ORDER BY price DESC");
    }

    @Override
    public List<Phone> findAllOrderByBrand() {
        return executeQuery("SELECT * FROM phone ORDER BY brand");
    }

    @Override
    public List<Phone> findAllOrderByBrandDesc() {
        return executeQuery("SELECT * FROM phone ORDER BY brand DESC");
    }

    @Override
    public List<Phone> findAllOrderByModel() {
        return executeQuery("SELECT * FROM phone ORDER BY model");
    }

    @Override
    public List<Phone> findAllOrderByModelDesc() {
        return executeQuery("SELECT * FROM phone ORDER BY model DESC");
    }

    @Override
    public List<Phone> findAllOrderByReleaseDate() {
        return executeQuery("SELECT * FROM phone ORDER BY release_date");
    }

    @Override
    public List<Phone> findByBrand(String brand) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM phone WHERE brand=?")) {

            ps.setString(1, brand);
            ResultSet rs = ps.executeQuery();
            return getList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Phone> findByModel(String model) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM phone WHERE model=?")) {

            ps.setString(1, model);
            ResultSet rs = ps.executeQuery();
            return getList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
