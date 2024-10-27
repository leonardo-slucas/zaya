package zaya.repository;

import zaya.models.KmeansData;
import zaya.utils.KmeansUtils;
import zaya.utils.PrintUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KmeansRepository {
    private final String TABLE_NAME = "kmeans_data";
    private Connection connection;

    public KmeansRepository(Connection connection) {
        this.connection = connection;
    }

    public List<KmeansData> getKmeansData() throws SQLException {
        Long startExecution = System.currentTimeMillis();
        List<KmeansData> kmeansDataList = new ArrayList<>();

        PrintUtils.print("Getting pending kmeans list.");

        String query = "SELECT * FROM " + TABLE_NAME;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                kmeansDataList.add(new KmeansData(
                        KmeansUtils.convertTextToNumeric(rs.getString("revenue")),
                        KmeansUtils.parseToBigDecimal(rs.getString("revenue")),
                        KmeansUtils.convertTextToNumeric(rs.getString("product")),
                        rs.getString("product")
                ));
            }
        } finally {
            if (Boolean.FALSE.equals(ps == null)) {
                ps.close();
            }
            if (Boolean.FALSE.equals(rs == null)) {
                rs.close();
            }
        }

        PrintUtils.duration("Getting kmeans", startExecution);
        return kmeansDataList;
    }
}
