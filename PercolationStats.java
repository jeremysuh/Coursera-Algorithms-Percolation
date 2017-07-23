import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats {
    
        private int attempt = 0;
        private double results[];
    
    public PercolationStats(int n, int trials){
        
        int blockCount = 0;
        attempt = trials;
        results = new double[trials];

        Percolation p = new Percolation(n);
            
            for (int x = 0; x < trials; x++){
            for (int i = 0; i < n * n * 5; i++){

           int a = StdRandom.uniform(1, n + 1);
           int b = StdRandom.uniform(1, n + 1);
        
            if (!p.isOpen(a, b)){              
                p.open(a, b);  
                if (!p.percolates()) blockCount++;
            }
      }
 
      double t = (double)(blockCount) / (n * n);
      results[x] = t;
  
      p = new Percolation(n);
      blockCount = 0;
        
            } 
    }
    
   public double mean(){
       return StdStats.mean(results);
    }    
   
   public double stddev(){
        return StdStats.stddev(results); 
    }    
   
   public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(attempt);
    }   
   
   public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(attempt);
    }                  
        
}