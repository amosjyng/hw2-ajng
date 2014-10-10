package com.amosjyng;

import java.io.File;
import java.net.URLDecoder;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;
import com.amosjyng.types.NamedEntityAnnotation;
import com.amosjyng.types.ShittySentenceID;

/**
 * Jackass that recognizes genes inside a sentence's text using lingpipe's genetag model
 * 
 * @author Amos Ng
 *
 */
public class JaCasAnnotator extends JCasAnnotator_ImplBase {
  /**
   * The importance of this annotator
   */
  private static double importance = 1.0;

  /**
   * The chunker that, given a gene model file, chunks the sentence into chunks of genes
   */
  ConfidenceChunker chunker;

  /**
   * Load up the gene model file
   */
  @Override
  public void initialize(org.apache.uima.UimaContext aContext)
          throws org.apache.uima.resource.ResourceInitializationException {
    try {
      chunker = (ConfidenceChunker) AbstractExternalizable.readResourceObject(getClass(),
              (String) aContext.getConfigParameterValue("genesFilename"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    super.initialize(aContext);
  }

  /**
   * Find the number of spaces in a string until a given index
   * 
   * @param str
   *          The string whose spaces are to be counted
   * @param until
   *          Until where in the string you count spaces
   * @return Number of spaces in a string until the given index
   */
  public static int countSpaces(String str, int until) {
    return StringUtils.countMatches(str.substring(0, until), " ");
  }

  /**
   * Find all gene names in the sentence contained in the given CAS
   */
  @Override
  public void process(JCas arg0) throws AnalysisEngineProcessException {
    String docText = arg0.getDocumentText();
    Iterator<Chunk> chunky = chunker.nBestChunks(docText.toCharArray(), 0, docText.length(), 10);
    while (chunky.hasNext()) {
      Chunk chunk = chunky.next();
      NamedEntityAnnotation nea = new NamedEntityAnnotation(arg0);
      nea.setBegin(chunk.start() - countSpaces(docText, chunk.start()));
      nea.setEnd(chunk.end() - countSpaces(docText, chunk.end()) - 1);
      nea.setNamedEntity(docText.substring(chunk.start(), chunk.end()));
      nea.setCasProcessorId("jackass");
      nea.setConfidence(importance * Math.pow(2, chunk.score()));
      nea.addToIndexes();
    }
    System.err.println("id "
            + ((ShittySentenceID) arg0.getJFSIndexRepository()
                    .getAllIndexedFS(ShittySentenceID.type).get()).getID() + " parag you jackass!");
  }

}
