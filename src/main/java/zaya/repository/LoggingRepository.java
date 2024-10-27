package zaya.repository;

import zaya.models.Logging;
import zaya.utils.PrintUtils;
import zaya.utils.SQLUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LoggingRepository {

    private final String TABLE_NAME = "TSILOG";
    private Connection connection;

    public LoggingRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(Logging logging) throws SQLException {
        Long start = System.currentTimeMillis();
        PrintUtils.print("Start saving logging in " + TABLE_NAME);
        
        ArrayList<String> insertFields = new ArrayList<>(Arrays.asList(
                "CODIGO",
                "DESCRICAO",
                "REQENTIDADE",
                "RESPENTIDADE",
                "TIPOENTIDADE",
                "IDENTIDADE",
                "DHALTER"));

        String insertQuery = SQLUtils.buildSimpleInsertQuery(TABLE_NAME, insertFields);
        PrintUtils.print("Insert statement: " + insertQuery);
        PreparedStatement insertStatement = null;
        try {
            insertStatement = connection.prepareStatement(insertQuery.toString());
            int index = 1;

            insertStatement.setString(index, logging.getCode());
            insertStatement.setString(++index, logging.getDescription());
            insertStatement.setString(++index, logging.getEntityRequest());
            insertStatement.setString(++index, logging.getEntityResponse());
            insertStatement.setString(++index, logging.getEntityType());
            insertStatement.setInt(++index, logging.getEntityId());
            insertStatement.setTimestamp(++index, logging.getUpdatedAt());
            insertStatement.execute();
        } finally {
            if (Boolean.TRUE.equals(insertStatement != null)) {
                try {
                    insertStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PrintUtils.duration("Saved logging in " + TABLE_NAME, start);
    }
}
