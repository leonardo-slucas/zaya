package tests.models;

import br.com.mitra.actionJar.ActionContext;
import br.com.mitra.actionJar.CubeData;
import br.com.mitra.actionJar.DimensionData;
import br.com.mitra.actionJar.SourceData;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ActionContextTest {

    ActionContext actionContext;

    public ActionContextTest() {
        this.actionContext = new ActionContext() {
            @Override
            public List<SourceData> getSourceContent() {
                List<SourceData> list = new ArrayList<>();
                SourceData pedidoSourceData = new SourceData() {
                    @Override
                    public Object getField(String columnName) {
                        if (columnName.equals("debug"))
                            return "true";
                        if (columnName.equals("IDEXECUCAO"))
                            return 6.00;
                        return null;
                    }
                };
                list.add(pedidoSourceData);
                return list;
            }

            @Override
            public CubeData newCubeData(Integer cubeId) throws Exception {
                return null;
            }

            @Override
            public CubeData newCubeData(String cubeName) throws Exception {
                return null;
            }

            @Override
            public DimensionData newDimensionData(Integer dimensionId) throws Exception {
                return null;
            }

            @Override
            public DimensionData newDimensionData(String dimensionName) throws Exception {
                return null;
            }

            @Override
            public Connection getConnection() {
                return new TestConnection().getConnection();
            }
        };
    }

    public ActionContext getActionContext() {
        return actionContext;
    }
}
