package org.checkerframework.checker.confidential;

import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.framework.qual.StubFiles;
import org.checkerframework.framework.source.SuppressWarningsPrefix;

/**
 * A type-checker plug-in for the Confidential type system qualifier that finds (and verifies the
 * absence of) information leakage bugs.
 *
 * <p>It verifies that no confidential values are passed to user-facing methods.
 *
 * @checker_framework.manual #confidential-checker Confidential Checker
 */
@StubFiles({
  "Log4jLogger.astub",
  "Log.astub",
  "Slf4jLogger.astub",
  "Handler.astub",
  "JavaLogger.astub"
})
@SuppressWarningsPrefix({"confidential"})
public class ConfidentialChecker extends BaseTypeChecker {}