package main;

import lib.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestRTree
{
	  public static void main(String[] args) throws IOException
	  {
	    /**
	     * M : Maximum number of Key
	     * m : Minimum number of Key (<= M/2)
	     * d : Dimention of data
	     */
	    RTree<Integer> tree 	= new RTree<Integer>(4, 2, 2);
	    
	    
	    /**
	     * Start Read File CSV
	     */
	    String csvFile      	= "file/randomPoints.csv";
	    BufferedReader br   	= null;
	    String line         	= "";
	    
	    
	    /**
	     * Sparator Data = pemisah antar data yang dismpan didalam csv
	     */
	    String cvsSplitBy   	= ",";
	    
	    
	    /**
	     * Mendefinisikan Path Result
	     */
	    String path_file_rst 	= "result";
	    
	    
	    /**
	     * Index Entry = urutan data disimpan
	     */
	    int Entry           	= 0;
	    try {
	        br = new BufferedReader(new FileReader(csvFile));
	        while ((line = br.readLine()) != null) 
	        {       /**
	        		  *use comma as separator
	                  */
	                String[] data = line.split(cvsSplitBy);
	                tree.insert(new float[]{  Float.parseFloat(data[0]), Float.parseFloat(data[1])  },Entry);
	                Entry++;
	                /**
	                  * end Read Data
	                  */
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	                try { br.close(); } 
	                catch (IOException e) { e.printStackTrace(); }
	        }
	    }
	    
	    
	    /**
	     * Menghitung Syline Query
	     */
	    
	    tree.Skyline();
	    
	    
	    
	    /**
	     * Menampilkan Hasil Hasil ditampilkan dalam bentuk HTML
	       Keterangan Tentang Hasil dijelaskan pada file PPT
	     */
	    String html = tree.visualize_();
	    System.err.println("Writing to " + path_file_rst);
	    try (OutputStream os = new FileOutputStream(path_file_rst + "/rtree.html")) {
	        os.write(html.getBytes());
	        os.flush();
	    }
	  }
  
}
