package top.jiangyixin.hades.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jiangyixn
 */
public class HadesExceptionHandler implements HandlerExceptionResolver {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
		Result r = new Result();
		try{
			httpServletResponse.setContentType("application/json;charset=utf-8");
			httpServletResponse.setCharacterEncoding("utf-8");
			if (e instanceof HadesException) {
				r.put("code", ((HadesException) e).getCode());
				r.put("message", e.getMessage());
			} else {
				r = Result.error();
			}
			httpServletResponse.getWriter().print(r);
		} catch (Exception e1) {
			logger.error("HadesExceptionHandler 异常处理失败", e);
		}
		return new ModelAndView();
	}
}
