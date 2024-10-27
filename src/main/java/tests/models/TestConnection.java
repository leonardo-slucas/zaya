package tests.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    private String path = "localhost";
    private Integer port = 3306;
    private String driverName = "mysql";
    private String schema = "zaya";
    private String username = "root";
    private String password = "mauFJcuf5dhRMQrjj";

    private String generateFullUrl() {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("jdbc:");
        urlBuilder.append(this.driverName.toLowerCase()).append("://").append(this.path).append(":").append(this.port)
                .append("/").append(this.schema)
                .append("?useTimezone=true&serverTimezone=GMT-03&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false");
        return urlBuilder.toString();
    }

    public Connection getConnection() {
        Connection con;
        try {
            con = DriverManager.getConnection(generateFullUrl(), this.username, this.password);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
