package zaya.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.ml.clustering.CentroidCluster;

import zaya.models.KmeansData;

public class KmeansUtils {
    public static double convertTextToNumeric(String text) {
        return text.chars().sum();
    }

    public static int calculateClusterAverages(List<CentroidCluster<KmeansData>> clusters) {
        BigDecimal maxClusterMeanRevenue = BigDecimal.ZERO;
        int maxClusterMean = 0;

        for (int i = 0; i < clusters.size(); i++) {
            CentroidCluster<KmeansData> cluster = clusters.get(i);
            List<KmeansData> kmeans = cluster.getPoints();
            BigDecimal sumRevenue = BigDecimal.ZERO;
            BigDecimal minRevenue = new BigDecimal(999999999);
            BigDecimal maxRevenue = BigDecimal.ZERO;

            int kmeansCount = kmeans.size();
            if (kmeansCount == 0) continue;
            
            for (KmeansData kmeansData : kmeans) {
                BigDecimal currentRevenue = kmeansData.getRevenue();
                sumRevenue = sumRevenue.add(currentRevenue);

                if(currentRevenue.compareTo(minRevenue) < 0) {
                    minRevenue = currentRevenue;
                }

                if(currentRevenue.compareTo(maxRevenue) > 0) {
                    maxRevenue = currentRevenue;
                }
            }

            BigDecimal meanRevenue = sumRevenue.divide(new BigDecimal(kmeansCount), 10, RoundingMode.HALF_UP);

            if((sumRevenue.divide(new BigDecimal(kmeansCount), 10, RoundingMode.HALF_UP)).compareTo(BigDecimal.ZERO) > 0) {
                System.out.println();
                System.out.println("Números do Cluster " + i + ":");
                System.out.printf("Receita: Total: %d :: Média: %.2f :: Mínimo: %.2f :: Máximo: %.2f", kmeansCount, meanRevenue, minRevenue, maxRevenue);
                System.out.println();
                System.out.println();
            }

            if(meanRevenue.compareTo(maxClusterMeanRevenue) > 0) {
                maxClusterMeanRevenue = meanRevenue;
                maxClusterMean = i;
            }
        }

        // System.out.println();
        // System.out.printf("Cluster com maior média de receitas: %d Média %.2f",  maxClusterMean, maxClusterMeanRevenue);
        // System.out.println();
        // System.out.println();
        // System.out.println();

        return maxClusterMean;
    }

    public static BigDecimal parseToBigDecimal(String value) {
        if (value == null || value.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        String sanitizedValue = value.replaceAll("[^0-9.,]", "").trim();

        try {
            if(isBigDecimal(sanitizedValue)) {
                return new BigDecimal(sanitizedValue);
            }

            return BigDecimal.ZERO;
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public static boolean isBigDecimal(String value) {
        try {
            new BigDecimal(value); 
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void addItemIfNotExists(ArrayList<String> list, String item) {
        if (!list.contains(item)) {
            list.add(item);
        }
    }

    public static Map<String, Integer> buildProductMap(List<String> productList) {
        Map<String, Integer> productMap = new HashMap<>();

        for (int i = 0; i < productList.size(); i++) {
            productMap.put(productList.get(i), i);
        }

        return productMap;
    }
}
