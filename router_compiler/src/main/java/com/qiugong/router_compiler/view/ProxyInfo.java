package com.qiugong.router_compiler.view;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @author qzx 20/2/27.
 */
class ProxyInfo {

    private static final String PROXY = "ViewInject";

    // 所在包名
    private String packageName;
    // 生成的类名
    private String proxyClassName;
    // 外部类
    private TypeElement typeElement;

    // 保存类里面的所有注解
    List<Element> mElementList = new ArrayList<>();

    ProxyInfo(Elements mElementUtils, TypeElement fullClazz) {
        PackageElement packageElement = mElementUtils.getPackageOf(fullClazz);
        String packageName = packageElement.getQualifiedName().toString();
        String className = getClassName(fullClazz, packageName);

        this.packageName = packageName;
        this.proxyClassName = className + "$$" + PROXY;
        this.typeElement = fullClazz;
    }

    /**
     * 生成代码
     */
    String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("// Generated code. Do not modify!\n");
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import com.qiugong.router_core.*;\n");
        builder.append('\n');

        builder.append("public class ")
                .append(proxyClassName)
                .append(" implements " + ProxyInfo.PROXY + "<")
                .append(typeElement.getQualifiedName())
                .append(">")
                .append(" {\n");

        generateMethods(builder);

        builder.append('\n');
        builder.append("}\n");
        return builder.toString();
    }


    /**
     * 生成根据注解去生成代码
     */
    private void generateMethods(StringBuilder builder) {
        builder.append("@Override\n ");
        builder.append("public void inject(")
                .append(typeElement.getQualifiedName())
                .append(" source) {\n");

        for (Element element : mElementList) {
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();
            builder.append("source.")
                    .append(name)
                    .append(" = ")
                    .append("(")
                    .append(type)
                    .append(")(((android.app.Activity)source).findViewById( ").append("R.id.").append(name).append("));\n");
        }

        builder.append("  }\n");
    }

    String getProxyClassFullName() {
        return packageName + "." + proxyClassName;
    }

    TypeElement getTypeElement() {
        return typeElement;
    }

    private String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen).replace('.', '$');
    }
}
