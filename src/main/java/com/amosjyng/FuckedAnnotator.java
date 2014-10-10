package com.amosjyng;

import java.io.File;
import java.net.URLDecoder;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.util.AbstractExternalizable;
import com.amosjyng.types.NamedEntityAnnotation;
import com.amosjyng.types.ShittySentenceID;

/**
 * Annotator that uses lingpipe's genia model to annotate stuff in a fucked up way
 * 
 * @author Amos Ng
 *
 */
public class FuckedAnnotator extends JCasAnnotator_ImplBase {
  /**
   * The importance of this annotator
   */
  private static double importance = 0.9;

  /**
   * The chunker that, given a gene model file, chunks the sentence into chunks of genes
   */
  Chunker chunker;

  /**
   * Load up the genia model file
   */
  @Override
  public void initialize(org.apache.uima.UimaContext aContext)
          throws org.apache.uima.resource.ResourceInitializationException {
    try {
      System.err.println((String) aContext.getConfigParameterValue("genesFilename"));
      chunker = (Chunker) AbstractExternalizable.readResourceObject(getClass(),
              (String) aContext.getConfigParameterValue("genesFilename"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    super.initialize(aContext);
  }

  /**
   * Annotate shits in the sentence and add annotations to JCas, and stores confidence value in the
   * annotation as well
   */
  @Override
  public void process(JCas arg0) throws AnalysisEngineProcessException {
    String docText = arg0.getDocumentText();
    for (Chunk chunk : chunker.chunk(docText).chunkSet()) {
      NamedEntityAnnotation nea = new NamedEntityAnnotation(arg0);
      nea.setBegin(chunk.start() - JaCasAnnotator.countSpaces(docText, chunk.start()));
      nea.setEnd(chunk.end() - JaCasAnnotator.countSpaces(docText, chunk.end()) - 1);
      nea.setNamedEntity(docText.substring(chunk.start(), chunk.end()));
      nea.setCasProcessorId("fucker");
      nea.setConfidence(importance * Math.pow(2, chunk.score()));
      nea.addToIndexes();
    }
    System.err.println("fucked id "
            + ((ShittySentenceID) arg0.getJFSIndexRepository()
                    .getAllIndexedFS(ShittySentenceID.type).get()).getID() + " parag dude!");
  }

}
