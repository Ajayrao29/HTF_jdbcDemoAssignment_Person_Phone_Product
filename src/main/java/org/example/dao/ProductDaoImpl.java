package org.example.dao;

import org.example.connection.DatabaseConnection;
import org.example.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM product")) {
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Product findById(int id) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Product> findByNameContaining(String text) {
        List<Product> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE name LIKE ? OR category LIKE ?")) {
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void addProduct(String name, String category, double price) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO product(name, category, price) VALUES(?,?,?)")) {
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct(int id, String name, String category, double price) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE product SET name=?, category=?, price=? WHERE id=?")) {
            ps.setString(1, name);
            ps.setString(2, category);
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
             PreparedStatement ps = con.prepareStatement("DELETE FROM product WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Product> executeQuery(String sql) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Product> getList(ResultSet rs) throws SQLException {
        List<Product> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Product map(ResultSet rs) throws SQLException {
        return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getDouble("price"));
    }

    @Override
    public List<Product> findAllOrderByPrice() {
        return executeQuery("SELECT * FROM product ORDER BY price");
    }

    @Override
    public List<Product> findAllOrderByPriceDesc() {
        return executeQuery("SELECT * FROM product ORDER BY price DESC");
    }

    @Override
    public List<Product> findAllOrderByName() {
        return executeQuery("SELECT * FROM product ORDER BY name");
    }

    @Override
    public List<Product> findAllOrderByNameDesc() {
        return executeQuery("SELECT * FROM product ORDER BY name DESC");
    }

    @Override
    public List<Product> findAllOrderByCategory() {
        return executeQuery("SELECT * FROM product ORDER BY category");
    }

    @Override
    public List<Product> findAllOrderByCategoryDesc() {
        return executeQuery("SELECT * FROM product ORDER BY category DESC");
    }

    @Override
    public List<Product> findByName(String name) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE name=?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findByCategory(String category) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE category=?")) {
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
