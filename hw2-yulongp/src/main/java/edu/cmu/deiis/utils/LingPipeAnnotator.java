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

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
    /*String text = aJCas.getDocumentText();
    // System.out.println("Lingpipe NER: " + text);
    if (text == null) {
      System.out.println("This is null string...");
    }
    String lines = text.replaceAll("\\s+", "");
    char[] cs = text.toCharArray();
    Iterator<Chunk> it = chunker.nBestChunks(cs, 0, cs.length, 6);
    for (int n = 0; it.hasNext(); ++n) {
      Chunk chunk = it.next();
      double conf = Math.pow(2.0, chunk.score());
      int start = chunk.start();
      int end = chunk.end();
      String phrase = text.substring(start, end);
      String tmp = phrase.replaceAll("\\s+", "");
      List<Integer> posList = new ArrayList<Integer>();
      int idx = 0;
      while ((idx = lines.indexOf(tmp, idx)) != -1) {
        posList.add(idx);
        idx += tmp.length();
      }
      for (Integer id : posList) {
        start = id;
        end = start + tmp.length() - 1;
        GeneEntitybyLingpipe entity = new GeneEntitybyLingpipe(aJCas, start, end);
        entity.setEntityText(phrase);
        entity.setConfidence(conf);
        entity.setCasProcessId(this.getClass().getName());
        // entity.setCasProcessorId(this.getClass().getName());
        entity.addToIndexes();
      }
    }*/
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
