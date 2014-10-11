

/* First created by JCasGen Sat Oct 11 00:25:02 EDT 2014 */
package edu.cmu.deiis.sentence;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import edu.cmu.deiis.types.Annotation;


/** 
 * Updated by JCasGen Sat Oct 11 00:25:02 EDT 2014
 * XML source: /home/paul/workspace/hw2-yulongp/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class GeneEntity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GeneEntity.class);
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
  protected GeneEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public GeneEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public GeneEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public GeneEntity(JCas jcas, int begin, int end) {
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
    if (GeneEntity_Type.featOkTst && ((GeneEntity_Type)jcasType).casFeat_entityText == null)
      jcasType.jcas.throwFeatMissing("entityText", "edu.cmu.deiis.sentence.GeneEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneEntity_Type)jcasType).casFeatCode_entityText);}
    
  /** setter for entityText - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEntityText(String v) {
    if (GeneEntity_Type.featOkTst && ((GeneEntity_Type)jcasType).casFeat_entityText == null)
      jcasType.jcas.throwFeatMissing("entityText", "edu.cmu.deiis.sentence.GeneEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneEntity_Type)jcasType).casFeatCode_entityText, v);}    
  }

    