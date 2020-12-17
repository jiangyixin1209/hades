package top.jiangyixin.hades.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.jiangyixin.hades.common.PageResult;
import top.jiangyixin.hades.common.Query;
import top.jiangyixin.hades.common.Result;
import top.jiangyixin.hades.service.GeneratorService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author jiangyixin
 */
@Controller
@RequestMapping("/generator")
public class GeneratorController {
	
	private final GeneratorService generatorService;
	
	@Autowired
	public GeneratorController(GeneratorService generatorService) {
		this.generatorService = generatorService;
	}
	
	@PostMapping("/list")
	public Result list(@RequestBody Map<String, Object> params) {
		PageResult pageResult = generatorService.queryTableList(new Query(params));
		return Result.ok().add("page", pageResult);
	}

	@PostMapping("/code")
	public void code(String tables, HttpServletResponse response) throws IOException {
		byte[] data = generatorService.generateCode(tables.split(","));
		response.reset();
		response.addHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data, response.getOutputStream());
	}

}
