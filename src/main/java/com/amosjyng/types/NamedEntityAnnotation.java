

/* First created by JCasGen Tue Oct 07 20:14:26 EDT 2014 */
package com.amosjyng.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import edu.cmu.deiis.types.Annotation;


/** 
 * Updated by JCasGen Tue Oct 07 20:16:02 EDT 2014
 * XML source: C:/Users/Amos Ng/workspace/hw2-ajng/src/main/resources/descriptors/amos_types.xml
 * @generated */
public class NamedEntityAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NamedEntityAnnotation.class);
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
  protected NamedEntityAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NamedEntityAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NamedEntityAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NamedEntityAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: NamedEntity

  /** getter for NamedEntity - gets 
   * @generated
   * @return value of the feature 
   */
  public String getNamedEntity() {
    if (NamedEntityAnnotation_Type.featOkTst && ((NamedEntityAnnotation_Type)jcasType).casFeat_NamedEntity == null)
      jcasType.jcas.throwFeatMissing("NamedEntity", "com.amosjyng.types.NamedEntityAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntityAnnotation_Type)jcasType).casFeatCode_NamedEntity);}
    
  /** setter for NamedEntity - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNamedEntity(String v) {
    if (NamedEntityAnnotation_Type.featOkTst && ((NamedEntityAnnotation_Type)jcasType).casFeat_NamedEntity == null)
      jcasType.jcas.throwFeatMissing("NamedEntity", "com.amosjyng.types.NamedEntityAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntityAnnotation_Type)jcasType).casFeatCode_NamedEntity, v);}    
  }

    