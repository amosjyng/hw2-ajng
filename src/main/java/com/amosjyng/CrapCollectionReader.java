package com.amosjyng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Scanner;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;

import com.amosjyng.types.ShittySentenceID;

;

/**
 * Collect sentences from input document and put them into a CAS each
 * 
 * @author Amos Ng
 *
 */
public class CrapCollectionReader extends CollectionReader_ImplBase {
  /**
   * Scanner to read in input file line by line
   */
  private Scanner sc;

  /**
   * Open input file and initialize the Scanner
   */
  @Override
  public void initialize() throws ResourceInitializationException {
    try {
      sc = new Scanner(new BufferedReader(new InputStreamReader(getClass().getClassLoader()
              .getResourceAsStream((String) getConfigParameterValue("inputFilename")), "utf-8")));
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    super.initialize();
  }

  /**
   * Populated next CAS with next sentence
   * 
   * @param arg0
   *          Populate this with sentence ID and sentence text of the next sentence
   */
  @Override
  public void getNext(CAS arg0) throws IOException, CollectionException {
    String[] shits = sc.nextLine().split(" ", 2);
    try {
      JCas jarg0n = arg0.getJCas();
      jarg0n.setDocumentText(shits[1]);
      ShittySentenceID ssid = new ShittySentenceID(jarg0n);
      ssid.setID(shits[0]);
      ssid.addToIndexes();
    } catch (CASException e) {
      e.printStackTrace();
    }
    System.err.println("Created CAS id " + shits[0] + " fucking parag!");
  }

  /**
   * Close input file when collection reader is closed
   */
  @Override
  public void close() throws IOException {
    sc.close();
  }

  /**
   * Return the progress so far. Useless.
   */
  @Override
  public Progress[] getProgress() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * See if there is more to read.
   */
  @Override
  public boolean hasNext() throws IOException, CollectionException {
    return sc.hasNext();
  }

}
