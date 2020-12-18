package top.jiangyixin.hades;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.StringWriter;

/**
 * TODO
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 上午9:59
 */
@SpringBootTest
public class TestVelocity {
	
	@Test
	public void testGenerate() {
    String daoXml =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n"
            + "\n"
            + "<mapper namespace=\"${package}.${moduleName}.dao.${className}Dao\">\n"
            + "    <resultMap type=\"${package}.${moduleName}.entity.${className}Entity\" id=\"${classname}Map\">\n"
            + "        #foreach($column in $columns)\n"
            + "            <result property=\"${column.attrname}\" column=\"${column.columnName}\"/>\n"
            + "        #end\n"
            + "    </resultMap>\n"
            + "</mapper>";
		VelocityContext context = new VelocityContext();
		StringWriter sw = new StringWriter();
		context.put("package", "hades");
		Velocity.evaluate(context, sw, "template.log", daoXml);
        System.out.println(sw.toString());
	}
}
