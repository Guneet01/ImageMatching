package com.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import net.semanticmetadata.lire.DocumentBuilder;
import net.semanticmetadata.lire.DocumentBuilderFactory;
import net.semanticmetadata.lire.imageanalysis.FCTH;
import org.apache.lucene.document.Document;

public class SimilaritySearch {

    static class Database {

        public String fileName;
        public double[] fcthFeatureVector;
        public double distanceToSearchImage;
    }

    static class ImageComparator implements Comparator<Database> {

        public int compare(Database object1, Database object2) {
            if (object1.distanceToSearchImage < object2.distanceToSearchImage) {
                return -1;
            } else if (object1.distanceToSearchImage > object2.distanceToSearchImage) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static double[] getFCTHFeatureVector(String fullFilePath) throws FileNotFoundException, IOException {

        DocumentBuilder builder = DocumentBuilderFactory.getFCTHDocumentBuilder();
        FileInputStream istream = new FileInputStream(fullFilePath);
        Document doc = builder.createDocument(istream, fullFilePath);
        istream.close();

        FCTH fcthDescriptor = new FCTH();
        fcthDescriptor.setByteArrayRepresentation(doc.getFields().get(0).getBinaryValue());

        return fcthDescriptor.getDoubleHistogram();

    }



    public static void main(String[] args) throws FileNotFoundException, IOException {

        if (args.length != 2) {
            
   
            return;
            
        }
        
        String imageDatabase = args[0];
        String searchImage = args[1];

        //double[] searchImageFeature = getFCTHFeatureVector(searchImage);
        
        //System.out.println(Arrays.toString(FeatureVector));

       // System.out.println("Search image FCTH vector: " + Arrays.toString(searchImageFeatureVector));

        ArrayList<Database> database = new ArrayList();

        File directory = new File(imageDatabase);

        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".jpeg");
            }
        };

        String[] fileNames = directory.list(filter);

        for (String fileName : fileNames) {

            double[] fcthFeatureVector = getFCTHFeatureVector(imageDatabase + "\\" + fileName);
        
            Database Database = new Database();

            Database.fileName = fileName;
            Database.fcthFeatureVector = fcthFeatureVector;
           

            database.add(Database);

        }

        Collections.sort(database, new ImageComparator());

        for (Database result : database) {
        	int counter=1;
        	//Most similar 3 images
        	while(counter<3)
        	{
        		 System.out.println("Distance " + Double.toString(result.distanceToSearchImage) + ": " + result.fileName);
        		 counter++;
        	}
           

        }

    }
}