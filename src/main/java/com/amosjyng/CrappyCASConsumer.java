package com.amosjyng;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import com.amosjyng.types.NamedEntityAnnotation;
import com.amosjyng.types.ShittySentenceID;

/**
 * Consumes CAS to write each recognized gene to an output file
 * 
 * @author Amos Ng
 *
 */
public class CrappyCASConsumer extends CasConsumer_ImplBase {
  /**
   * Writes out stuff to output file
   */
  private BufferedWriter bw;

  /**
   * Open output file for writing
   */
  @Override
  public void initialize() throws ResourceInitializationException {
    System.err.println("Consuming shit");
    try {
      bw = new BufferedWriter(new FileWriter(new File(
              (String) getConfigParameterValue("outputFilename"))));
    } catch (IOException e) {
      e.printStackTrace();
    }
    super.initialize();
  }

  /**
   * Write out all the recognized genes, along with string locations, and sentence ID
   * 
   * @param arg0
   *          CAS containing all the recognized genes for a certain sentence
   */
  @Override
  public void processCas(CAS arg0) throws ResourceProcessException {
    try {
      JCas jacass = arg0.getJCas();
      String id = ((ShittySentenceID) jacass.getJFSIndexRepository()
              .getAllIndexedFS(ShittySentenceID.type).get()).getID(); // only one
      FSIterator<TOP> it = jacass.getJFSIndexRepository().getAllIndexedFS(
              NamedEntityAnnotation.type);
      Map<String, Double> nes = new HashMap<String, Double>();
      while (it.hasNext()) {
        NamedEntityAnnotation nea = ((NamedEntityAnnotation) it.get());
        String nesStr = id + "|" + nea.getBegin() + " " + nea.getEnd() + "|" + nea.getNamedEntity() + "\n";
        if (!nes.containsKey(nesStr) || (nea.getConfidence() > nes.get(nesStr))) {
          nes.put(nesStr, nea.getConfidence());
        }
        it.next();
      }
      for (Map.Entry<String, Double> i : nes.entrySet()) {
        if (i.getValue() > 0.5 && i.getKey().length() < 50) {
          bw.write(i.getKey());
        }
        else {
          //System.err.println("failed for " + i.getKey() + " with score " + i.getValue());
        }
      }
      bw.flush();
      System.err.println("flushed the toilet for " + id);
    } catch (CASException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
