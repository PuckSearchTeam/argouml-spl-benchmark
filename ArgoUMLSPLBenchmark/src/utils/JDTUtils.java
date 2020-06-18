package utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 * JDTUtils
 * 
 * @author jabier.martinez
 */
public class JDTUtils {

	/**
	 * Get all methods
	 * 
	 * @param a
	 *            compilation unit
	 * @return list of methods
	 */
	public static List<MethodDeclaration> getMethods(CompilationUnit cu) {
		List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
		cu.accept(new ASTVisitor() {
			public boolean visit(MethodDeclaration node) {
				methods.add(node);
				return true;
			}
		});
		return methods;
	}
	
	/**
	 * Get the method that contains (inside) a given position
	 * 
	 * @param methods
	 * @param currentBlockStart
	 * @param currentBlockEnd
	 * @return
	 */
	public static MethodDeclaration getMethodThatContainsAPosition(List<MethodDeclaration> methods,
			int currentBlockStart, int currentBlockEnd) {
		// currentBlock is inside a method
		for (MethodDeclaration method : methods) {
			if (currentBlockStart >= method.getStartPosition()) {
				if (currentBlockEnd <= method.getStartPosition() + method.getLength()) {
					return method;
				}
			}
		}
		return null;
	}
	
	/**
	 * Get the method that is between a given start point and end point in the
	 * source code String
	 * 
	 * @param methods
	 * @param currentBlockStart
	 * @param currentBlockEnd
	 * @return methodDeclaration
	 */
	public static List<MethodDeclaration> getWrappingMethods(List<MethodDeclaration> methods, int currentBlockStart,
			int currentBlockEnd) {
		List<MethodDeclaration> wrappingMethods = new ArrayList<MethodDeclaration>();
		// currentBlock is a wrapper of the method
		for (MethodDeclaration method : methods) {
			if (currentBlockStart < method.getStartPosition()) {
				if (currentBlockEnd > method.getStartPosition() + method.getLength()) {
					wrappingMethods.add(method);
				}
			}
		}
		return wrappingMethods;
	}
	
}
