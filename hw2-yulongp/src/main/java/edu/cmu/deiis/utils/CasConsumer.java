package edu.cmu.deiis.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceProcessException;

import edu.cmu.deiis.sentence.GeneEntity;
import edu.cmu.deiis.sentence.Sentence;

public class CasConsumer extends CasConsumer_ImplBase {

  private BufferedWriter outputBW;

  /**
   * the implementation of initialize in CAS consumer
   */
  public void initialize() {
    try {
      File file = new File((String) getConfigParameterValue("OutputFile"));
      file.createNewFile();
      outputBW = new BufferedWriter(new FileWriter(file));

    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void processCas(CAS aCAS) throws ResourceProcessException {
    // TODO Auto-generated method stub
    JCas jcas = null;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }

    String sentenceId = "";
    FSIterator<TOP> sentenceIterator = jcas.getJFSIndexRepository().getAllIndexedFS(Sentence.type);
    if (sentenceIterator.hasNext()) {
      Sentence sentence = (Sentence) sentenceIterator.next();
      sentenceId = sentence.getSentenceId();
    }

    FSIndex geneIndex = jcas.getAnnotationIndex(GeneEntity.type);
    // for (Annotation geneEntity : jcas.getAnnotationIndex(GeneEntity.type)) {
    Iterator geneIter = geneIndex.iterator();
    Set<String> geneSet = new HashSet<String>();
    Set<String> filter = new HashSet<String>();
    while (geneIter.hasNext()) {
      GeneEntity entity = (GeneEntity) geneIter.next();
      // System.out.println(entity.getCasProcessorId());
      if (entity.getCasProcessorId() != null
              && entity.getCasProcessorId().equals("edu.cmu.deiis.utils.GeneAnnotator")) {
        String outputString = sentenceId + "|" + ((Integer) entity.getBegin()).toString() + " "
                + ((Integer) entity.getEnd()).toString() + "|" + entity.getEntityText() + "\n";
        String prefix = sentenceId + "|" + ((Integer) entity.getBegin()).toString();
        if (!filter.contains(prefix)) {
          try {
            outputBW.write(outputString);
            outputBW.flush();
          } catch (IOException e) {
            e.printStackTrace();
          }
          filter.add(prefix);
        }
      }
    }

    /*for (String s : geneSet) {
      try {
        outputBW.write(s);
        outputBW.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }*/
  }

  public void destroy() {
    try {
      outputBW.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
