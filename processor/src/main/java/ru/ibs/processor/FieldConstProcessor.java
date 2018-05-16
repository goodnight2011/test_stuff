package ru.ibs.processor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SupportedAnnotationTypes("ru.ibs.processor.FieldConst")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class FieldConstProcessor extends AbstractProcessor {

    private Filer filer;
    private Elements elemUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        if (processingEnv != null) {
            filer = processingEnv.getFiler();
            elemUtils = processingEnv.getElementUtils();
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element elem : roundEnv.getElementsAnnotatedWith(FieldConst.class)) {
            if (elem.getKind() == ElementKind.CLASS) {
                TypeElement classElem = (TypeElement) elem;
                List<? extends Element> subElements = classElem.getEnclosedElements();
                List<String> fieldsNames = new ArrayList<>();
                for (Element subElem : subElements) {
                    if (subElem != null && subElem.getKind() == ElementKind.FIELD) {
                        fieldsNames.add(subElem.getSimpleName().toString());
                    }
                }

                try {
                    JavaFileObject jfo = filer.createSourceFile(classElem.getQualifiedName() + "Fields");
                    if (jfo != null) {
                        try (Writer writer = jfo.openWriter()) {
                            writer.write("package " + elemUtils.getPackageOf(classElem) + ";\n\n");
                            writer.write("public class " + ((TypeElement) elem).getSimpleName() + "Fields{\n");
                            for (String fldName : fieldsNames) {
                                writer.write("public static final String " + generateContName(fldName) + " = \"" + fldName + "\";\n");
                            }
                            writer.write("}");
                            writer.flush();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }

    private static String generateContName(String fieldName){
        return IntStream.range(0, fieldName.length()).
                mapToObj(cnt -> fieldName.charAt(cnt)).
                map(ch -> ch >= 'A' && ch <='Z' ? "_"+String.valueOf(ch) : String.valueOf(ch)).
                map(String::toUpperCase).collect(Collectors.joining());
    }
}
