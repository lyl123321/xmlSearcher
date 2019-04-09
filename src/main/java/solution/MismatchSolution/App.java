package solution.MismatchSolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App { 
    public static void main(String args[]){
        //查询 Q
        String[] Q = {"Paul", "1994", "0169", "System"};
        //查询结果 R
        List<Map> R = new ArrayList<Map>();
        
        Map r1 = new HashMap();
        r1.put("vlca", "0");
        String[] M1 = {"0.2.0", "0.3.6", "0.4.0", "0.2.2"};
        r1.put("nodes", M1);
        /*
        Map r2 = new HashMap();
        r2.put("vlca", "0");
        String[] M2 = {"0.1.2", "0.1.6", "0.1.8.0", "0.2.1"};
        r2.put("nodes", M2);
        */
        R.add(r1);
        //R.add(r2);
        //阈值
        double τ = 0.9;
        
        /*解析
        String xml = "data/dblp/dblp2.xml"; 
        String dtd = "data/dblp/dblp.dtd";
        String dtdxml = "data/dblp/dtd.xml";
        
        String xml = "data/reed/reed.xml"; 
        String dtd = "data/reed/reed.dtd";
        String dtdxml = "data/reed/dtd.xml";
        PreParse parser = new PreParse(Q, xml, dtd, dtdxml);
        parser.parse();
        */
        //关键字查询
        System.out.println("Search: ");
        String[] query = {"Paul", "1994", "0169", "System"};
        Searcher searcher = new Searcher(query);
        List<Map> results = searcher.search();
        for (Map result : results) {
        	System.out.println(result);
		}
        searcher.close();
        
        //解决查询的失配问题
        System.out.println("--------------------------------------------------");
        System.out.println("MisMatch: ");
        Resolver resolver = new Resolver(Q, R, τ);
        ArrayList<HashMap> suggestedQueries = resolver.resolve();
        for (HashMap sugQuery : suggestedQueries) {
        	System.out.println(sugQuery);
		}
        resolver.close();
    }
} 
