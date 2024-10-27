package zaya.repository;

import zaya.models.KmeansData;
import zaya.utils.KmeansUtils;
import zaya.utils.PrintUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KmeansRepository {
    private final String TABLE_NAME = "kmeans_data";
    private Connection connection;

    public KmeansRepository(Connection connection) {
        this.connection = connection;
    }

    public List<KmeansData> getKmeansData(Map<String, Integer> productMap) throws SQLException {
        Long startExecution = System.currentTimeMillis();
        List<KmeansData> kmeansDataList = new ArrayList<>();

        PrintUtils.print("Getting pending kmeans list.");

        String query = "SELECT revenue, UPPER(product) as product FROM " + TABLE_NAME;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString("product") != null && productMap.containsKey(rs.getString("product"))) {
                    kmeansDataList.add(new KmeansData(
                            KmeansUtils.parseToBigDecimal(rs.getString("revenue")).doubleValue(),
                            KmeansUtils.parseToBigDecimal(rs.getString("revenue")),
                            productMap.get(rs.getString("product")).doubleValue(),
                            rs.getString("product")));
                }
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

    public List<String> getProductList() throws SQLException {
        Long startExecution = System.currentTimeMillis();
        List<String> productList = new ArrayList<>();

        PrintUtils.print("Getting product list.");

        String query = "SELECT DISTINCT(UPPER(product)) as product FROM " + TABLE_NAME;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                productList.add(rs.getString("product"));
            }
        } finally {
            if (Boolean.FALSE.equals(ps == null)) {
                ps.close();
            }
            if (Boolean.FALSE.equals(rs == null)) {
                rs.close();
            }
        }

        PrintUtils.duration("Getting product list", startExecution);
        return productList;
    }
}
