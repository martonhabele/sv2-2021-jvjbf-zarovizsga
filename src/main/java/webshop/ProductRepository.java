package webshop;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;

public class ProductRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject("select * from products where id = ?;", (rs, rowNum) -> new Product(rs.getLong("id"),
                rs.getString("product_name"),
                rs.getInt("price"),
                rs.getInt("stock")),
                id);
    }

    public long insertProduct(String productName, int price, int stock) {
        long id;
        try (Connection c = dataSource.getConnection(); PreparedStatement statement = c.prepareStatement("insert into products (product_name, price, stock) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, productName);
            statement.setLong(2, price);
            statement.setLong(3, stock);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                id = resultSet.getLong(1);
            } else {
                throw new SQLException("No key has generated");
            }
        } catch (SQLException throwables) {
            throw new IllegalStateException("Cannot save: " + productName, throwables);
        }
        return id;
    }

    public void updateProductStock(long id, int amount) {
        jdbcTemplate.update("update products set stock = stock - ? where id = ?", amount, id);
    }
}