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
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;

import edu.cmu.deiis.sentence.GeneEntitybyLingpipe;

public class LingPipeAnnotator extends JCasAnnotator_ImplBase {

  private ConfidenceChunker chunker;

  @Override
  public void initialize(UimaContext aUimaContext) {
    try {
      chunker = (ConfidenceChunker) AbstractExternalizable.readObject(new File(aUimaContext
      .getResourceFilePath("LingPipeNERModel")));
      /*chunker = (ConfidenceChunker) AbstractExternalizable.readResourceObject(
              LingPipeAnnotator.class,
              (String) aUimaContext.getConfigParameterValue("LingPipeNERModel"));*/
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ResourceAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
    String text = aJCas.getDocumentText();
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
    }
  }

}
