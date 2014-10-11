

/* First created by JCasGen Sat Oct 11 00:25:03 EDT 2014 */
package edu.cmu.deiis.sentence;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Sat Oct 11 00:25:03 EDT 2014
 * XML source: /home/paul/workspace/hw2-yulongp/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class GeneEntitybyLingpipe extends GeneEntity {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GeneEntitybyLingpipe.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected GeneEntitybyLingpipe() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public GeneEntitybyLingpipe(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public GeneEntitybyLingpipe(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public GeneEntitybyLingpipe(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: entityText

  /** getter for entityText - gets 
   * @generated
   * @return value of the feature 
   */
  public String getEntityText() {
    if (GeneEntitybyLingpipe_Type.featOkTst && ((GeneEntitybyLingpipe_Type)jcasType).casFeat_entityText == null)
      jcasType.jcas.throwFeatMissing("entityText", "edu.cmu.deiis.sentence.GeneEntitybyLingpipe");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneEntitybyLingpipe_Type)jcasType).casFeatCode_entityText);}
    
  /** setter for entityText - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEntityText(String v) {
    if (GeneEntitybyLingpipe_Type.featOkTst && ((GeneEntitybyLingpipe_Type)jcasType).casFeat_entityText == null)
      jcasType.jcas.throwFeatMissing("entityText", "edu.cmu.deiis.sentence.GeneEntitybyLingpipe");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneEntitybyLingpipe_Type)jcasType).casFeatCode_entityText, v);}    
   
    
  //*--------------*
  //* Feature: casProcessId

  /** getter for casProcessId - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCasProcessId() {
    if (GeneEntitybyLingpipe_Type.featOkTst && ((GeneEntitybyLingpipe_Type)jcasType).casFeat_casProcessId == null)
      jcasType.jcas.throwFeatMissing("casProcessId", "edu.cmu.deiis.sentence.GeneEntitybyLingpipe");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneEntitybyLingpipe_Type)jcasType).casFeatCode_casProcessId);}
    
  /** setter for casProcessId - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCasProcessId(String v) {
    if (GeneEntitybyLingpipe_Type.featOkTst && ((GeneEntitybyLingpipe_Type)jcasType).casFeat_casProcessId == null)
      jcasType.jcas.throwFeatMissing("casProcessId", "edu.cmu.deiis.sentence.GeneEntitybyLingpipe");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneEntitybyLingpipe_Type)jcasType).casFeatCode_casProcessId, v);}    
  }

    