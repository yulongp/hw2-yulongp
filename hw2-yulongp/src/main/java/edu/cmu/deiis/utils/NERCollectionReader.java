package edu.cmu.deiis.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import edu.cmu.deiis.sentence.Sentence;

public class NERCollectionReader extends CollectionReader_ImplBase {

  private BufferedReader inputBR;

  private String line;

  private int cur_size;

  private int total_size;

  public void initialize() {
    // open the input file
    try {
      File file = new File((String) getConfigParameterValue("InputFile"));
      // System.out.println(getConfigParameterValue("InputFile").toString());
      inputBR = new BufferedReader(new FileReader(file));
      line = "";
      cur_size = 0;
      total_size = (int) file.length();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void getNext(CAS aCAS) throws IOException, CollectionException {
    // TODO Auto-generated method stub
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    }
    line = inputBR.readLine();
    if (line != null) {
      cur_size += line.length();
      Sentence sent = new Sentence(jcas);
      int idx = line.indexOf(" ");
      String id = line.substring(0, idx);
      System.out.println(id);
      String content = line.substring(idx + 1, line.length() - 1);
      jcas.setDocumentText(content);
      sent.setSentenceId(id);
      // s.setContent(content);
      sent.addToIndexes();
    }
  }

  @Override
  public boolean hasNext() throws IOException, CollectionException {
    // TODO Auto-generated method stub
    try {
      return inputBR.ready();
    } catch (IOException e) {

      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Progress[] getProgress() {
    // TODO Auto-generated method stub
    return new Progress[] { new ProgressImpl(cur_size, total_size, Progress.ENTITIES) };
  }

  @Override
  public void close() throws IOException {
    // TODO Auto-generated method stub
    inputBR.close();
  }

}
