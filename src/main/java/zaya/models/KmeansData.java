package zaya.models;

import java.math.BigDecimal;

import org.apache.commons.math3.ml.clustering.Clusterable;

public class KmeansData implements Clusterable {
    double[] points;
    int cluster;
    BigDecimal revenue;
    String product;

    public KmeansData(double kmeansRevenue, BigDecimal revenue, double kmeansProduct, String product) {
        // this.points = new double[] { kmeansRevenue, kmeansProduct };
        this.points = new double[] { kmeansRevenue };
        this.revenue = revenue;
        this.product = product;
    }

    @Override
    public double[] getPoint() {
        return points;
    }

    public int getCluster() {
        return this.cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public BigDecimal getRevenue() {
        return this.revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
