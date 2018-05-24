package com.qdh.RecommenderSystem;

import net.librec.conf.Configuration;
import net.librec.data.DataContext;
import net.librec.data.DataModel;
import net.librec.data.convertor.TextDataConvertor;
import net.librec.data.model.TextDataModel;
import net.librec.data.splitter.GivenNDataSplitter;
import net.librec.data.splitter.RatioDataSplitter;
import net.librec.math.algorithm.Randoms;
import net.librec.math.structure.DenseMatrix;
import net.librec.math.structure.SparseMatrix;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.cf.ranking.FISMrmseRecommender;


public class FISMrmseAlo {


    public static void main(String... args) throws Exception {

        Configuration configuration = new Configuration();
        configuration.set("rec.recommender.class", "fismrmse");
        configuration.set("rec.iteration.learnrate", "0.01");
        configuration.set("rec.iterator.maximum", "3");
        configuration.set("rec.recommender.isranking", "true");
        configuration.set("rec.fismrmse.rho", "1");
        configuration.set("rec.fismrmse.alpha", "1.5");
        configuration.set("dfs.data.dir", "C:/Users/xiujiezhang/Downloads/librec-librec-src-v2.0/librec-librec-src-v2.0/data/movielens/");
        configuration.set("rec.recommender.verbose","true");
        configuration.set("data.splitter.givenn", "item");
        configuration.set("data.splitter.givenn.n", "1");
        configuration.set("data.column.format","UIR");
        configuration.set("inputDataPath",configuration.get("dfs.data.dir")+"/ratings.txt");
        configuration.set("data.input.path","ml-100k");

        configuration.set("rec.eval.enable","true");
        configuration.set("rec.eval.class", "hitrate");
        configuration.set("rec.recommender.ranking.topn","10");
        configuration.set("dfs.result.dir","C:/Users/xiujiezhang/Downloads/librec-librec-src-v2.0/librec-librec-src-v2.0/data/movielens/result");
        TextDataModel textDataModel = new TextDataModel(configuration);
        textDataModel.buildDataModel();
        RecommenderContext recommenderContext = new RecommenderContext(configuration,textDataModel);
        FISMrmseRecommender fisMrmseRecommender = new FISMrmseRecommender();
        fisMrmseRecommender.recommend(recommenderContext);



    }
}
