package edu.cmu.deiis.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.sentence.GeneEntity;
import edu.cmu.deiis.sentence.GeneEntitybyLingpipe;
import edu.cmu.deiis.sentence.GeneEntitybyPOS;

public class GeneAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
    FSIndex genelpIndex = aJCas.getAnnotationIndex(GeneEntitybyLingpipe.type);
    FSIndex geneabIndex = aJCas.getAnnotationIndex(GeneEntitybyPOS.type);
    List<GeneEntitybyLingpipe> lingpipeList = new ArrayList<GeneEntitybyLingpipe>();
    List<GeneEntitybyPOS> abnerList = new ArrayList<GeneEntitybyPOS>();

    Iterator genelpIter = genelpIndex.iterator();
    while (genelpIter.hasNext()) {
      GeneEntitybyLingpipe ge = (GeneEntitybyLingpipe) genelpIter.next();
      // System.out.println("Lingpipe: " + ge.getBegin() + " " + ge.getEnd() + " " +
      // ge.getEntityText());
      lingpipeList.add(ge);
    }

    Iterator geneabIter = geneabIndex.iterator();
    while (geneabIter.hasNext()) {
      GeneEntitybyPOS ge = (GeneEntitybyPOS) geneabIter.next();
      // System.out.println("POS: " + ge.getBegin() + " " + ge.getEnd() + " " + ge.getEntityText());
      abnerList.add(ge);
    }

    // System.out.println(lingpipeList.size() + " " + abnerList.size());

    if (lingpipeList.size() != 0 && abnerList.size() != 0) {
      for (GeneEntitybyLingpipe ge1 : lingpipeList) {
        for (GeneEntitybyPOS ge2 : abnerList) {
          // System.out.println("no overlapping: " + ge1.getEntityText() + " - " +
          // ge2.getEntityText());
          if (ge1.getEntityText().equals(ge2.getEntityText()) && ge1.getBegin() == ge2.getBegin()
                  && ge1.getEnd() == ge2.getEnd()) {
            System.out.println(ge1.getEntityText() + ":" + ge1.getConfidence() + " - "
                    + ge2.getEntityText() + ":" + ge2.getConfidence());
            double conf = ge1.getConfidence() * 0.5 + ge2.getConfidence() * 0.5;
            // if (conf > 0.65) {
            GeneEntity ge = new GeneEntity(aJCas, ge1.getBegin(), ge1.getEnd());
            ge.setBegin(ge1.getBegin());
            ge.setEnd(ge1.getEnd());
            ge.setCasProcessorId(this.getClass().getName());
            ge.setConfidence(1);
            ge.setEntityText(ge1.getEntityText());
            ge.addToIndexes();
            // }
          }
        }
      }
    }
  }

}
