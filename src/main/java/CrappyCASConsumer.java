import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Scanner;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

/**
 * Consumes CAS to write each recognized gene to an output file
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
    try {
      bw = new BufferedWriter(new FileWriter((String) getConfigParameterValue("outputFilename")));

    } catch (IOException e) {
      e.printStackTrace();
    }
    super.initialize();
  }

  /**
   * Write out all the recognized genes, along with string locations, and sentence ID
   * @param arg0 CAS containing all the recognized genes for a certain sentence
   */
  @Override
  public void processCas(CAS arg0) throws ResourceProcessException {
    try {
      JCas jacass = arg0.getJCas();
      String id = ((ShittySentenceID) jacass.getJFSIndexRepository().getAllIndexedFS(ShittySentenceID.type).get()).getID(); // only one
      FSIterator<TOP> it = jacass.getJFSIndexRepository().getAllIndexedFS(NamedEntityAnnotation.type);
      while (it.hasNext()) {
        NamedEntityAnnotation nea = ((NamedEntityAnnotation) it.get());
        bw.write(id + "|" + nea.getBegin() + " " + nea.getEnd() + "|" + nea.getNamedEntity() + "\n");
        it.next();
      }
      bw.flush();
    } catch (CASException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
