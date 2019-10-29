package dy.component.filters;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class TestFilter implements TypeFilter {

    /**
     *
     * @param metadataReader            读取当前正在扫描的类的信息
     * @param metadataReaderFactory     可以读取到当前其他类的信息
     * @return                          返回true即为是否匹配
     * @throws IOException
     */
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        // 获取类上的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取类本身的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();    // 获取权限定性类名

        if(className.endsWith("TestFilterService")){
            return true;
        }

        return false;
    }
}
