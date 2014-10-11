
/* First created by JCasGen Sat Oct 11 00:25:03 EDT 2014 */
package edu.cmu.deiis.sentence;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Sat Oct 11 00:25:03 EDT 2014
 * @generated */
public class GeneEntitybyPOS_Type extends GeneEntity_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (GeneEntitybyPOS_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = GeneEntitybyPOS_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new GeneEntitybyPOS(addr, GeneEntitybyPOS_Type.this);
  			   GeneEntitybyPOS_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new GeneEntitybyPOS(addr, GeneEntitybyPOS_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = GeneEntitybyPOS.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.deiis.sentence.GeneEntitybyPOS");
 
  /** @generated */
  final Feature casFeat_entityText;
  /** @generated */
  final int     casFeatCode_entityText;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getEntityText(int addr) {
        if (featOkTst && casFeat_entityText == null)
      jcas.throwFeatMissing("entityText", "edu.cmu.deiis.sentence.GeneEntitybyPOS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_entityText);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEntityText(int addr, String v) {
        if (featOkTst && casFeat_entityText == null)
      jcas.throwFeatMissing("entityText", "edu.cmu.deiis.sentence.GeneEntitybyPOS");
    ll_cas.ll_setStringValue(addr, casFeatCode_entityText, v);}
    
  
 
  /** @generated */
  final Feature casFeat_casProcessId;
  /** @generated */
  final int     casFeatCode_casProcessId;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getCasProcessId(int addr) {
        if (featOkTst && casFeat_casProcessId == null)
      jcas.throwFeatMissing("casProcessId", "edu.cmu.deiis.sentence.GeneEntitybyPOS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_casProcessId);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCasProcessId(int addr, String v) {
        if (featOkTst && casFeat_casProcessId == null)
      jcas.throwFeatMissing("casProcessId", "edu.cmu.deiis.sentence.GeneEntitybyPOS");
    ll_cas.ll_setStringValue(addr, casFeatCode_casProcessId, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public GeneEntitybyPOS_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_entityText = jcas.getRequiredFeatureDE(casType, "entityText", "uima.cas.String", featOkTst);
    casFeatCode_entityText  = (null == casFeat_entityText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_entityText).getCode();

 
    casFeat_casProcessId = jcas.getRequiredFeatureDE(casType, "casProcessId", "uima.cas.String", featOkTst);
    casFeatCode_casProcessId  = (null == casFeat_casProcessId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_casProcessId).getCode();

  }
}



    