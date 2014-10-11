package edu.cmu.deiis.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceAccessException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunking;
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.chunk.NBestChunker;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.ScoredObject;

import edu.cmu.deiis.sentence.GeneEntitybyLingpipe;

public class LingPipeAnnotator extends JCasAnnotator_ImplBase {

  private NBestChunker chunker;

  /*
   * The initialize function for Lingpipe annotator
   * para: UimaContenxt
   * @see org.apache.uima.analysis_component.AnalysisComponent_ImplBase#initialize(org.apache.uima.UimaContext)
   */
  @Override
  public void initialize(UimaContext aUimaContext) {
    try {
      chunker = (NBestChunker) AbstractExternalizable.readResourceObject((String) aUimaContext
              .getConfigParameterValue("LingpipeModel"));
      /*
       * chunker = (ConfidenceChunker) AbstractExternalizable.readResourceObject(
       * LingPipeAnnotator.class, (String)
       * aUimaContext.getConfigParameterValue("LingPipeNERModel"));
       */
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /*
   * The process function for Lingpipe annotator
   * para: JCas
   * @see org.apache.uima.analysis_component.JCasAnnotator_ImplBase#process(org.apache.uima.jcas.JCas)
   */
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
    String text = aJCas.getSofaDataString();
    // get top 1 chunking 
    ScoredObject<Chunking> chunkres = chunker.nBest(text.toCharArray(), 0, text.length(), 1).next();
    double conf = chunkres.score();
    for(Chunk chunk : chunkres.getObject().chunkSet())
    {
      // adapt indices to non whitespace
      int begin = text.substring(0, chunk.start()).replaceAll("\\s", "").length(); 
      int end = -1+text.substring(0, chunk.end()).replaceAll("\\s", "").length(); 
      // add mention to CAS
      GeneEntitybyLingpipe mention = new GeneEntitybyLingpipe(aJCas, begin, end);
      mention.setEntityText(text.substring(chunk.start(), chunk.end()));
      mention.setCasProcessorId(this.getClass().getName());
      mention.setConfidence(conf);
      mention.addToIndexes();
    }
  }

}
