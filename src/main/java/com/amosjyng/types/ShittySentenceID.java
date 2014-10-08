

/* First created by JCasGen Tue Oct 07 20:01:17 EDT 2014 */
package com.amosjyng.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 07 20:16:02 EDT 2014
 * XML source: C:/Users/Amos Ng/workspace/hw2-ajng/src/main/resources/descriptors/amos_types.xml
 * @generated */
public class ShittySentenceID extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ShittySentenceID.class);
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
  protected ShittySentenceID() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ShittySentenceID(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ShittySentenceID(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ShittySentenceID(JCas jcas, int begin, int end) {
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
  //* Feature: ID

  /** getter for ID - gets 
   * @generated
   * @return value of the feature 
   */
  public String getID() {
    if (ShittySentenceID_Type.featOkTst && ((ShittySentenceID_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "com.amosjyng.types.ShittySentenceID");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ShittySentenceID_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setID(String v) {
    if (ShittySentenceID_Type.featOkTst && ((ShittySentenceID_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "com.amosjyng.types.ShittySentenceID");
    jcasType.ll_cas.ll_setStringValue(addr, ((ShittySentenceID_Type)jcasType).casFeatCode_ID, v);}    
  }

    