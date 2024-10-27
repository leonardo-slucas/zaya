package zaya.service;

import br.com.mitra.actionJar.ActionContext;
import zaya.models.KmeansData;
import zaya.utils.KmeansUtils;
import zaya.utils.PrintUtils;
import zaya.repository.KmeansRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
        int k = 15;
        int maxIterations = 100;
        ArrayList<String> productList = new ArrayList<>();
        List<KmeansData> kmeansDataList = kmeansRepository.getKmeansData();

        PrintUtils.setDebug(actionContext);

        List<CentroidCluster<KmeansData>> clusters = kMeansClustering(kmeansDataList, k, maxIterations);

        int maxClusterMean = KmeansUtils.calculateClusterAverages(clusters);

        for (int i = 0; i < kmeansDataList.size(); i++) {
            KmeansData kmeans = kmeansDataList.get(i);
            if (kmeans.getCluster() == maxClusterMean) {
                // PrintUtils.print("Kmeans: " + i + " :: Cluster: " + kmeans.getCluster() + " :: Receita " + kmeans.getRevenue() + " :: Produto: " + kmeans.getProduct());

                KmeansUtils.addItemIfNotExists(productList, kmeans.getProduct());
            }
        }

        System.out.println("Produtos: ");
        for (String item : productList) {
            System.out.println(item);
        }
    }

    public static List<CentroidCluster<KmeansData>> kMeansClustering(List<KmeansData> kmeansDataList, int k,
            int maxIterations) {
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
