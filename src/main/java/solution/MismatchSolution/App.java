package solution.MismatchSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String args[]){
        //url
        String xml = "data/dblp/dblp2.xml"; 
        //获取前 K 个结果
        int K = 10;
        //阈值 τ
        double τ = 0.6;
        
        /*
        //1、解析
        String dtd = "data/dblp/dblp.dtd";
        String dtdxml = "data/dblp/dtd.xml";
        PreParse parser = new PreParse(xml, dtd, dtdxml);
        parser.parse();
        */
        
        /* 1\ String[] query = {"Mark", "F.", "D.", "1990"};
         * 2\ String[] query = {"Wang", "Lee", "Leonid", "1998"};
         * 3\ String[] query = {"Brown", "Robert", "Active", "1999"};
         * 4\ String[] query = {"Brown", "Robert", "Active", "Roger"};
         * 5\ String[] query = {"Javed", "Helena", "Implementation"};
         * 6\ String[] query = {"Mark", "Joe", "Frank", "2001"};
         * 7\ String[] query = {"E.", "L.", "D.", "Data", "Structured"};
         * 8\ String[] query = {"Gray", "Bruce", "Index", "Report"};
         * 9\ String[] query = {"Jim", "Mike", "Object", "school of"};
         * 10\ String[] query = {"A.", "B.", "C.", "Morris", "Logic"};
         */
        
        //2、关键字查询
        Date date1 = new Date();
        System.out.println("1. Search: ");
        String[] query = {"Brown", "Robert", "Active", "1999"};
        List<Map> results = Searcher.search(query, xml, K);
        System.out.println("query: ");
        System.out.println(Arrays.toString(query));
        System.out.println("results: ");
        for (Map result : results) {
        	System.out.println("vlca: " + result.get("vlca") + ", nodes: " + Arrays.toString((String[])result.get("nodes")));
		}
        Date date2 = new Date();
        System.out.println("search time: " + (date2.getTime() - date1.getTime()) + "ms");
        
        //3、解决查询的失配问题
        System.out.println("--------------------------------------------------");
        System.out.println("2. Resolve MisMatch Problem: ");
        Resolver resolver = new Resolver(query, results, τ);
        ArrayList<HashMap> suggestedQueries = resolver.resolve();
        resolver.close();
        /*
        if(suggestedQueries != null) {
            int num = suggestedQueries.size();
            System.out.println("suggested queries number: " + num);
            System.out.println("top-5 suggested queries: ");
            num = num < 5 ? num : 5;
            for (int i = 0; i < num; i++) {
            	System.out.println(suggestedQueries.get(i));
    		}
        }
        */
        System.out.println("resolve time: " + ((new Date()).getTime() - date2.getTime()) + "ms");
    }
} 
