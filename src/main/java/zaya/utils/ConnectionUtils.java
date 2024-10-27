package zaya.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionUtils {

    public static JsonArray extractResultSetRowsAsJson(ResultSet rs) {
        JsonArray result = new JsonArray();
        try {

            while (rs.next()) {
                JsonObject row = new JsonObject();
                getColumnsName(rs.getMetaData()).forEach(cn -> {
                    try {
                        row.addProperty(cn, rs.getObject(cn).toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    private static List<String> getColumnsName(ResultSetMetaData rsmd) throws SQLException {
        List<String> names = new ArrayList<>();
        int columnCount = rsmd.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            names.add(rsmd.getColumnLabel(i));
        }
        return names;
    }

    public static ArrayList<String> extractResultSetRows(ResultSet rs) {
        ArrayList<String> result = new ArrayList<>();
        try {

            while (rs.next()) {
                getColumnsName(rs.getMetaData()).forEach(cn -> {
                    try {
                        result.add((String) rs.getObject(cn));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
}
