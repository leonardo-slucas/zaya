package zaya.utils;

import com.google.gson.JsonElement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;

public class SQLUtils {

    public static String buildSimpleInsertQuery(String tableName, ArrayList<String> fields) {
        StringBuilder insertQuerySB = new StringBuilder("INSERT INTO ");
        insertQuerySB.append(tableName);
        insertQuerySB.append(" (");
        fields.forEach(field -> insertQuerySB.append(field.concat(",")));
        insertQuerySB.replace(insertQuerySB.length() - 1, insertQuerySB.length(), "");
        insertQuerySB.append(") VALUES(");
        fields.forEach(field -> insertQuerySB.append("?,"));
        insertQuerySB.replace(insertQuerySB.length() - 1, insertQuerySB.length(), ")");
        return insertQuerySB.toString();
    }

    public static String buildSimpleUpdateQuery(String tableName, ArrayList<String> setFields,
            ArrayList<String> whereFields) {
        StringBuilder udpateQuerySB = new StringBuilder("UPDATE ");
        udpateQuerySB.append(tableName);
        udpateQuerySB.append(" SET ");
        setFields.forEach(field -> udpateQuerySB.append(field.concat("=?,")));
        udpateQuerySB.replace(udpateQuerySB.length() - 1, udpateQuerySB.length(), "");
        udpateQuerySB.append(" WHERE ");
        whereFields.forEach(field -> udpateQuerySB.append(field.concat("=? AND ")));
        udpateQuerySB.replace(udpateQuerySB.length() - 4, udpateQuerySB.length(), "");
        return udpateQuerySB.toString();
    }

    public static void setIntegerOrNull(PreparedStatement ps, int index, JsonElement jsonElement) throws SQLException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            ps.setNull(index, Types.INTEGER);
        } else {
            ps.setInt(index, jsonElement.getAsInt());
        }
    }

    public static void setBigDecimalOrNull(PreparedStatement ps, int index, JsonElement jsonElement)
            throws SQLException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            ps.setNull(index, Types.DECIMAL);
        } else {
            ps.setBigDecimal(index, jsonElement.getAsBigDecimal());
        }
    }

    public static void setStringOrNull(PreparedStatement ps, int index, JsonElement jsonElement) throws SQLException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            ps.setNull(index, Types.VARCHAR);
        } else {
            ps.setString(index, jsonElement.getAsString());
        }
    }

    public static void setTimeStampOrNull(PreparedStatement ps, int index, JsonElement jsonElement)
            throws SQLException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            ps.setNull(index, Types.TIMESTAMP);
        } else {
            ps.setTimestamp(index, DateUtils.getStringAsTimeStamp(jsonElement.getAsString()));
        }
    }

    public static void setDateOrNull(PreparedStatement ps, int index, JsonElement jsonElement)
            throws SQLException, ParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            ps.setNull(index, Types.DATE);
        } else {
            ps.setDate(index, DateUtils.getStringAsDate(jsonElement.getAsString()));
        }
    }

    public static void setBooleanOrNull(PreparedStatement ps, int index, JsonElement jsonElement)
            throws SQLException, ParseException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            ps.setNull(index, Types.BOOLEAN);
        } else {
            ps.setBoolean(index, jsonElement.getAsBoolean());
        }
    }
}
