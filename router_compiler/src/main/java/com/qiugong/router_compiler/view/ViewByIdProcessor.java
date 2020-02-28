package com.qiugong.router_compiler.view;

import com.google.auto.service.AutoService;
import com.qiugong.router_annotation.ViewById;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * @author qzx 20/2/27.
 */
@AutoService(Processor.class)
public class ViewByIdProcessor extends AbstractProcessor {

    private Filer mFileUtils;
    private Elements mElementUtils;
    private Map<String, ProxyInfo> mProxyMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFileUtils = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(ViewById.class.getCanonicalName());
        return annotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        log("process....");
        mProxyMap.clear();

        //获取被注解的元素
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ViewById.class);
        for (Element field : elements) {
            if (field.getKind() != ElementKind.FIELD) {
                return false;
            }

            // 获取到这个变量的外部类
            TypeElement fullClazz = (TypeElement) field.getEnclosingElement();
            // 获取外部类的类名
            String fullClazzName = fullClazz.getQualifiedName().toString();
            log("field = " + field.toString() + " fullClazzName = " + fullClazzName);

            // 以外部类为单位保存
            ProxyInfo proxyInfo = mProxyMap.get(fullClazzName);
            if (proxyInfo == null) {
                proxyInfo = new ProxyInfo(mElementUtils, fullClazz);
                mProxyMap.put(fullClazzName, proxyInfo);
            }

            // 把这个注解保存到proxyInfo里面，用于实现功能
            proxyInfo.mElementList.add(field);
        }

        //生成类
        for (String key : mProxyMap.keySet()) {
            ProxyInfo proxyInfo = mProxyMap.get(key);
            try {
                //创建一个新的源文件，并返回一个对象以允许写入它
                JavaFileObject jfo = mFileUtils.createSourceFile(
                        proxyInfo.getProxyClassFullName(),
                        proxyInfo.getTypeElement());

                log("javaFile = " + jfo);
                Writer writer = jfo.openWriter();
                writer.write(proxyInfo.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }

        return true;
    }

    private void log(String string) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, string);
    }
}
