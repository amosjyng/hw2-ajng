import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

/**
 * Jackass that recognizes genes inside a sentence's text
 * @author Amos Ng
 *
 */
public class JaCasAnnotator extends JCasAnnotator_ImplBase {
  /**
   * The chunker that, given a gene model file, chunks the sentence into chunks of genes
   */
  Chunker chunker;

  /**
   * Load up the gene model file
   */
  @Override
  public void initialize(org.apache.uima.UimaContext aContext)
          throws org.apache.uima.resource.ResourceInitializationException {
    try {
      chunker = (Chunker) AbstractExternalizable.readObject(new File(URLDecoder.decode(getClass().getResource((String) aContext.getConfigParameterValue("GenesFilename")).getFile(), "UTF-8")));
    } catch (Exception e) {
      e.printStackTrace();
    }

    super.initialize(aContext);
  }

  /**
   * Find the number of spaces in a string until a given index
   * @param str The string whose spaces are to be counted
   * @param until Until where in the string you count spaces
   * @return Number of spaces in a string until the given index
   */
  private static int countSpaces(String str, int until) {
    return StringUtils.countMatches(str.substring(0, until), " ");
  }

  /**
   * Find all gene names in the sentence contained in the given CAS
   */
  @Override
  public void process(JCas arg0) throws AnalysisEngineProcessException {
    String docText = arg0.getDocumentText();
    for (Chunk chunk : chunker.chunk(docText).chunkSet()) {
      NamedEntityAnnotation nea = new NamedEntityAnnotation(arg0);
      nea.setBegin(chunk.start() - countSpaces(docText, chunk.start()));
      nea.setEnd(chunk.end() - countSpaces(docText, chunk.end()) - 1);
      nea.setNamedEntity(docText.substring(chunk.start(), chunk.end()));
      nea.addToIndexes();
    }
  }

}
