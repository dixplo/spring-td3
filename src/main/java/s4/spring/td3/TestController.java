package s4.spring.td3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;

@Controller
public class TestController {

	@Autowired
	private VueJS vue;
	
	@RequestMapping("/test/{message}")
	public String index(@PathVariable String message,ModelMap model) {
		vue.addData("message", "Hello world!");
		model.put("vue", vue);
		vue.addMethod("increment", "this.message=parseInt(this.message)+10;");
		return "index";
	}
}
