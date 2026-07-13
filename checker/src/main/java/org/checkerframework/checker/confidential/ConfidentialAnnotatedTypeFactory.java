package org.checkerframework.checker.confidential;

import java.util.Set;
import javax.lang.model.element.AnnotationMirror;
import org.checkerframework.checker.confidential.qual.BottomConfidential;
import org.checkerframework.checker.confidential.qual.Confidential;
import org.checkerframework.checker.confidential.qual.NonConfidential;
import org.checkerframework.checker.confidential.qual.UnknownConfidential;
import org.checkerframework.common.basetype.BaseAnnotatedTypeFactory;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.framework.flow.CFAbstractAnalysis;
import org.checkerframework.framework.flow.CFStore;
import org.checkerframework.framework.flow.CFTransfer;
import org.checkerframework.framework.flow.CFValue;
import org.checkerframework.javacutil.AnnotationBuilder;
import org.checkerframework.javacutil.AnnotationMirrorSet;

/** Annotated type factory for the Confidential Checker. */
public class ConfidentialAnnotatedTypeFactory extends BaseAnnotatedTypeFactory {

  /** The {@code @}{@link NonConfidential} annotation mirror. */
  protected final AnnotationMirror NONCONFIDENTIAL;

  /** The {@code @}{@link Confidential} annotation mirror. */
  protected final AnnotationMirror CONFIDENTIAL;

  /** The {@code @}{@link UnknownConfidential} annotation mirror. */
  protected final AnnotationMirror UNKNOWN_CONFIDENTIAL;

  /** The {@code @}{@link BottomConfidential} annotation mirror. */
  protected final AnnotationMirror BOTTOM_CONFIDENTIAL;

  /** Fully-qualified class name of {@link NonConfidential}. */
  public static final String NONCONFIDENTIAL_NAME =
      "org.checkerframework.checker.confidential.qual.NonConfidential";

  /** Fully-qualified class name of {@link Confidential}. */
  public static final String CONFIDENTIAL_NAME =
      "org.checkerframework.checker.confidential.qual.Confidential";

  /** Fully-qualified class name of {@link UnknownConfidential}. */
  public static final String UNKNOWN_CONFIDENTIAL_NAME =
      "org.checkerframework.checker.confidential.qual.UnknownConfidential";

  /** Fully-qualified class name of {@link BottomConfidential}. */
  public static final String BOTTOM_CONFIDENTIAL_NAME =
      "org.checkerframework.checker.confidential.qual.BottomConfidential";

  /** A singleton set containing the {@code @}{@link NonConfidential} annotation mirror. */
  private final AnnotationMirrorSet setOfNonConfidential;

  /**
   * Creates a {@link ConfidentialAnnotatedTypeFactory}.
   *
   * @param checker the confidential checker
   */
  @SuppressWarnings("this-escape")
  public ConfidentialAnnotatedTypeFactory(BaseTypeChecker checker) {
    super(checker);
    this.NONCONFIDENTIAL = AnnotationBuilder.fromClass(getElementUtils(), NonConfidential.class);
    this.CONFIDENTIAL = AnnotationBuilder.fromClass(getElementUtils(), Confidential.class);
    this.UNKNOWN_CONFIDENTIAL =
        AnnotationBuilder.fromClass(getElementUtils(), UnknownConfidential.class);
    this.BOTTOM_CONFIDENTIAL =
        AnnotationBuilder.fromClass(getElementUtils(), BottomConfidential.class);
    this.setOfNonConfidential = AnnotationMirrorSet.singleton(NONCONFIDENTIAL);
    postInit();
  }

  @Override
  public CFTransfer createFlowTransferFunction(
      CFAbstractAnalysis<CFValue, CFStore, CFTransfer> analysis) {
    return new ConfidentialTransfer(analysis);
  }

  @Override
  protected Set<AnnotationMirror> getEnumConstructorQualifiers() {
    return setOfNonConfidential;
  }
}
