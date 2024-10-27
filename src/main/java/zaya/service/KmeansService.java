package zaya.service;

import br.com.mitra.actionJar.ActionContext;
import zaya.models.KmeansData;
import zaya.utils.KmeansUtils;
import zaya.utils.PrintUtils;
import zaya.repository.KmeansRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

public class KmeansService {
    private ActionContext actionContext;
    private KmeansRepository kmeansRepository;

    public KmeansService(ActionContext actionContext) {
        if (actionContext.getConnection() == null) {
            PrintUtils.printError("Finishing execution. Connection from ActionContext is null!");
            throw new RuntimeException("Connection from ActionContext is null!");
        }
       

        this.actionContext = actionContext;
        this.kmeansRepository = new KmeansRepository(actionContext.getConnection());
    }

    public void execute() throws RuntimeException, SQLException, IOException, ParseException {
        int k = 10;
        int maxIterations = 100;
        List<KmeansData> kmeansDataList = buildKmeansDataList();
        PrintUtils.setDebug(actionContext);

        List<CentroidCluster<KmeansData>> clusters = kMeansClustering(kmeansDataList, k, maxIterations);

        int maxClusterMean = KmeansUtils.calculateClusterAverages(clusters);

        for (int i = 0; i < kmeansDataList.size(); i++) {
            KmeansData kmeans = kmeansDataList.get(i);
            if (kmeans.getCluster() == maxClusterMean) {
                PrintUtils.print("Kmeans: " + i + " :: Cluster: " + kmeans.getCluster() + " :: Receita " + kmeans.getRevenue() + " :: Produto: " + kmeans.getProduct());
            }
        }
    }

    private List<KmeansData> buildKmeansDataList() throws RuntimeException, SQLException, IOException, ParseException{
        List<String> productList = kmeansRepository.getProductList();
        Map<String, Integer> productMap = KmeansUtils.buildProductMap(productList);
        List<KmeansData> kmeansDataList = kmeansRepository.getKmeansData(productMap);

        return kmeansDataList;
    } 

    private static List<CentroidCluster<KmeansData>> kMeansClustering(List<KmeansData> kmeansDataList, int k, int maxIterations) {
        KMeansPlusPlusClusterer<KmeansData> clusterer = new KMeansPlusPlusClusterer<>(k, maxIterations);
        List<CentroidCluster<KmeansData>> clusters = clusterer.cluster(kmeansDataList);

        for (int i = 0; i < clusters.size(); i++) {
            for (KmeansData kmeansData : clusters.get(i).getPoints()) {
                kmeansData.setCluster(i);
            }
        }

        return clusters;
    }

}
