package zaya.tasks;

import br.com.mitra.actionJar.ActionContext;
import br.com.mitra.util.MitraDevTools;
import zaya.service.KmeansService;
import zaya.utils.PrintUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Kmeans implements MitraDevTools {

	@Override
	public void doExecute(ActionContext actionContext) {
		Long executionStart = System.currentTimeMillis();
		KmeansService kmeansService = new KmeansService(actionContext);
		
		PrintUtils.print("Starting kmeans algorithm.");
		try {
			kmeansService.execute();
		} catch (ParseException | IOException | SQLException | RuntimeException e) {
			e.printStackTrace();
			PrintUtils.printError("Finished kmeans algorithm with error: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				actionContext.getConnection().close();
				PrintUtils.print("Connection closed.");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		PrintUtils.duration("Finished kmeans algorithm.", executionStart);
	}
}
