package dy.component.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class TestImportSelector implements ImportSelector {

    /**
     *
     * @param annotationMetadata        可以获取到当前@Import注解标注类的所有注解信息
     * @return                          返回的string数组，其中包含所有要导入的类的权限定性类名
     */
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        String[] strings = new String[1];
        strings[0] = "dy.entities.Dog";
        return strings;
    }
}
