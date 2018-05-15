package ru.ibs.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("ru.ibs.processor.FieldConst")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class FieldConstProcessor extends AbstractProcessor{

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element elem : roundEnv.getElementsAnnotatedWith(FieldConst.class)) {
            FieldConst fieldConst= elem.getAnnotation(FieldConst.class);
            String message = "annotation found in " + elem.getSimpleName();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, message);
        }
        return true;
    }
}
