package top.jiangyixin.hades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.jiangyixin.hades.Query;

/**
 * @author jiangyixin
 */
@Controller
public class GenerateController {

	@PostMapping("/list")
	public String list(@RequestBody Query query) {
		return "";
	}

	@PostMapping("/code")
	public String code(@RequestBody Query query) {
		String[] tableList = query.getTables().split(",");
		return "";
	}

}
