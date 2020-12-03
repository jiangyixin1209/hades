package top.jiangyixin.hades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author jiangyixin
 */
@Controller
public class GenerateController {

	@PostMapping("/list")
	public String list() {
		return "";
	}

	@PostMapping("/code")
	public String code() {
		return "";
	}

}
